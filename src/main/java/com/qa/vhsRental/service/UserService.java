package com.qa.vhsRental.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qa.vhsRental.dto.UserDto;
import com.qa.vhsRental.entity.User;
import com.qa.vhsRental.exception.UserAlreadyExistsException;
import com.qa.vhsRental.exception.UserNotFoundException;
import com.qa.vhsRental.exception.VHSAlreadyExistsException;
import com.qa.vhsRental.exception.VHSNotFoundException;
import com.qa.vhsRental.exception.passwordDoesNotMatchException;
@Service
public interface UserService {

	public List<User> getAllUsers();
	public User addUser(User user) throws UserAlreadyExistsException;
	public User getUserByID(int id) throws UserNotFoundException;
	public User updateUser(User user) throws UserNotFoundException;
	public User addVHStoUser(int id, String vhs) throws UserNotFoundException, VHSAlreadyExistsException;
	public Boolean deleteUser(int id) throws UserNotFoundException;
	public User removeVHSfromUser(int id, String vhs) throws UserNotFoundException, VHSNotFoundException;

	public List<User> findUserByVhs(String vhs);
	public List<User> findUserByNameAndSurname(String name,String surname);
	public List<User> findUserByAddress(String address);
 	public List<UserDto> userShortDetails();
	public UserDto login(int id, String password) throws UserNotFoundException, passwordDoesNotMatchException;

	public UserDto signup(User user) throws UserAlreadyExistsException;
	




}



