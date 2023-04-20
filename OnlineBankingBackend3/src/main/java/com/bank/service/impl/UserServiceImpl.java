package com.bank.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bank.entity.User;
import com.bank.exceptions.AccountDoesNotExistsException;
import com.bank.exceptions.UserDoesNotExistsException;
import com.bank.repository.UserRepository;
import com.bank.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository repository;

	@Override
	public User saveUser(User user) {
		return repository.save(user);
	}

	@Override
	public User updateUser(User user) {
		return repository.save(user);
	}

	@Override
	public Boolean deleteUser(Long id) {
		User goUsers = getUserById(id);
		repository.delete(goUsers);
		return true;
	}

	@Override
	public User getUserById(Long id) {
		Optional<User> optional = repository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		throw new UserDoesNotExistsException("The User with id " + id + " does not exists");
	}

	@Override
	public User getUserByAccountNumber(Long accountNumber) {
		Optional<User> byAccountNumber = repository.findByAccountNumber(accountNumber);
		if(byAccountNumber.isPresent()) {
			return byAccountNumber.get();
		}
		throw new AccountDoesNotExistsException("Account Number "+accountNumber+" does not exists");
	}

	@Override
	public List<User> getAllUsers() {
		return repository.findAll();
	}

	
}
