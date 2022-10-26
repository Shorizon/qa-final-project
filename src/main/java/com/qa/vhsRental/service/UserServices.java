package com.qa.vhsRental.service;

import java.util.List;

import com.qa.vhsRental.entity.User;
import com.qa.vhsRental.entity.VHS;
import com.qa.vhsRental.exception.UserAlreadyExistsException;
import com.qa.vhsRental.exception.UserNotFoundException;
import com.qa.vhsRental.exception.VHSNotFoundExeption;

public interface UserServices {
	
public List<User> getAllUsers();
public User addUser(User user) throws UserAlreadyExistsException;
public User getUserByID(int id) throws UserNotFoundException;
public User updateUser(User user) throws UserNotFoundException;
public User addVHStoUser(User user, VHS vhs) throws UserNotFoundException;
public User removeVHSfromUser(User user,VHS vhs) throws UserNotFoundException, VHSNotFoundExeption;
public Boolean deleteUser(User user) throws UserNotFoundException;
public List<User> getUserByRentedVHS(VHS vhs);



}


