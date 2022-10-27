package com.qa.vhsRental.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.vhsRental.dto.UserDto;
import com.qa.vhsRental.entity.User;
import com.qa.vhsRental.entity.VHS;
import com.qa.vhsRental.exception.UserAlreadyExistsException;
import com.qa.vhsRental.exception.UserNotFoundException;
import com.qa.vhsRental.exception.VHSAlreadyExistsException;
import com.qa.vhsRental.exception.VHSNotFoundException;
import com.qa.vhsRental.exception.passwordDoesNotMatchException;
import com.qa.vhsRental.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {


	@Autowired
	UserRepository userRepository;
	@Autowired
	ModelMapper modelMapper;


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
	public User addVHStoUser(int id, VHS vhs) throws UserNotFoundException, VHSAlreadyExistsException {
		Optional<User> userFoundByIdOptional = this.userRepository.findById(id);
		if (!userFoundByIdOptional.isPresent()) {
			throw new UserNotFoundException();
		}else {
			User user = userFoundByIdOptional.get();
			if (user.getRentedVHS().contains(vhs))
				throw new VHSAlreadyExistsException();
				else {
					vhs.setIsRented(true);
					user.getRentedVHS().add(vhs);
					
					return this.userRepository.save(user);
				}
		}
	}

	@Override
	public User removeVHSfromUser(int id) throws UserNotFoundException {
		User user ;
		Optional<User> userFoundByIdOptional = this.userRepository.findById(id);
		if (!userFoundByIdOptional.isPresent()) {
			throw new UserNotFoundException();
		}else {
			user = userFoundByIdOptional.get();
				user.setRentedVHS(null);			
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

	@Override 
	public UserDto login(int id,String password) throws UserNotFoundException, passwordDoesNotMatchException {
		User user;
		Optional<User> userFoundByIdOptional = this.userRepository.findById(id);
		if (!userFoundByIdOptional.isPresent()) {
			throw new UserNotFoundException();
		}else {
			user = userFoundByIdOptional.get();
			if(user.getId() == id && user.getPassword().equals(password)) {
				System.out.println("successfull Login!");
			}else {
				throw new passwordDoesNotMatchException();
			}
		}
		return mapToUserDto(user);

	}

	@Override 
	public UserDto signup(User user) throws UserAlreadyExistsException  {
		Optional<User> userFoundByIdOptional = this.userRepository.findById(user.getId());
		if(userFoundByIdOptional.isPresent())
			throw new UserAlreadyExistsException("ID already taken");
		else {
			this.userRepository.save(user);
		}
		return mapToUserDto(user);

	}

	@Override
	public List<User> findUserByVhs(String vhs) {
		return  this.userRepository.findUserByVhs(vhs);
	}


	@Override
	public List<UserDto> userShortDetails() {
		return this.userRepository.findAll().stream().map(this::mapToUserDto).collect(Collectors.toList());
	}

	private UserDto mapToUserDto(User user) {
		return this.modelMapper.map(user, UserDto.class);
	}

	@Override
	public List<User> findUserByNameAndSurname(String name, String surname) {
		return this.userRepository.findAll().stream()
										    .filter((user) -> 
										    user.getName().equals(name) && user.getSurname().equals(surname))
										    .collect(Collectors.toList());
	}

	@Override
	public List<User> findUserByAddress(String address) {
		return this.userRepository.findUserByAddress(address);
	}



}



