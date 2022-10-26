package com.qa.vhsRental.service;

import java.util.List;

import com.qa.vhsRental.entity.User;
import com.qa.vhsRental.entity.VHS;

public interface UserServices {
	
public List<User> getAllUsers();
public User addUser(User user);
public User getUserByID(int id);
public User updateUser(User user);
public User addVHStoUser(VHS vhs);
public User removeVHSfromUser(VHS vhs);
public Boolean deleteUser(User user);
public User getUserByRentedVHS(VHS vhs);


}



