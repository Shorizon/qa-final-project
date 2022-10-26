package com.qa.vhsRental.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qa.vhsRental.entity.User;
import com.qa.vhsRental.exception.UserAlreadyExistsException;
import com.qa.vhsRental.exception.UserNotFoundException;
import com.qa.vhsRental.exception.VHSNotFoundExeption;
@Service
public interface UserServices {
	
public List<User> getAllUsers();
public User addUser(User user) throws UserAlreadyExistsException;
public User getUserByID(int id) throws UserNotFoundException;
public User updateUser(User user) throws UserNotFoundException;
public User removeVHSfromUser(User user, String vhs) throws UserNotFoundException, VHSNotFoundExeption;
public User addVHStoUser(int id, String vhs) throws UserNotFoundException;
public Boolean deleteUser(int id) throws UserNotFoundException;



}



