package com.qa.vhsRental.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.vhsRental.entity.User;
import com.qa.vhsRental.exception.UserAlreadyExistsException;
import com.qa.vhsRental.exception.UserNotFoundException;
import com.qa.vhsRental.exception.VHSNotFoundExeption;
import com.qa.vhsRental.repository.UserRepository;

@Service
public class UserServicesImpl implements UserServices {
	
	
	@Autowired
	UserRepository userRepository;
	

	@Override
	public List<User> getAllUsers() {
		
		return userRepository.findAll();
	}

	@Override
	public User addUser(User user) throws UserAlreadyExistsException {
		Optional<User> userFoundByIdOptional = this.userRepository.findById(user.getId());
		if (userFoundByIdOptional.isPresent())
			throw new UserAlreadyExistsException("USER ALREADY EXISTS, PLEASE CHECK YOUR INFORMATION");
		else
			return this.userRepository.save(user);
	}
	

	@Override
	public User getUserByID(int id) throws UserNotFoundException {
		Optional<User> userFoundByIdOptional = this.userRepository.findById(id);
		if (!userFoundByIdOptional.isPresent())
			throw new UserNotFoundException();
		return userFoundByIdOptional.get();
	}
	

	@Override
	public User updateUser(User user) throws UserNotFoundException {
		Optional<User> userFoundByIdOptional = this.userRepository.findById(user.getId());
		if (!userFoundByIdOptional.isPresent())
			throw new UserNotFoundException();
		else 
			return this.userRepository.save(user);
		
	}
	
	

	@Override
	public User addVHStoUser(int id, String vhs) throws UserNotFoundException {
		Optional<User> userFoundByIdOptional = this.userRepository.findById(id);
		if (!userFoundByIdOptional.isPresent()) {
			throw new UserNotFoundException();
		}else {
			User user = userFoundByIdOptional.get();
			user.setRentedVHS(vhs);
		return this.userRepository.save(user);
		}
	}

	@Override
	public User removeVHSfromUser(int id, String vhs) throws UserNotFoundException, VHSNotFoundExeption {
		User user ;
		Optional<User> userFoundByIdOptional = this.userRepository.findById(id);
		if (!userFoundByIdOptional.isPresent()) {
			throw new UserNotFoundException();
		}else {
			user = userFoundByIdOptional.get();
			if (user.getRentedVHS().equals(vhs)) {
				user.setRentedVHS(null);				
			}else {
				throw new VHSNotFoundExeption();
			}
			
		}
			
		return this.userRepository.save(user);
	}

	@Override
	public Boolean deleteUser(int id) throws UserNotFoundException {
		boolean status = false;
		Optional<User> userFoundByIdOptional = this.userRepository.findById(id);
		if (!userFoundByIdOptional.isPresent()) {
			throw new UserNotFoundException();
		}else {
			this.userRepository.delete(userFoundByIdOptional.get());
			status = true;
		}
	
		return status;
	}


	
	
}




