package com.qa.vhsRental.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.vhsRental.dto.UserDto;
import com.qa.vhsRental.entity.User;
import com.qa.vhsRental.entity.VHS;
import com.qa.vhsRental.exception.UserAlreadyExistsException;
import com.qa.vhsRental.exception.UserNotFoundException;
import com.qa.vhsRental.exception.VHSAlreadyExistsException;
import com.qa.vhsRental.exception.VHSNotFoundException;
import com.qa.vhsRental.exception.passwordDoesNotMatchException;
import com.qa.vhsRental.service.UserServiceImpl;

@RestController
@RequestMapping("api/v1")
public class UserController {

	@Autowired
	UserServiceImpl userService;

	ResponseEntity<?> responseEntity;

	@PostMapping("/signup")
	public ResponseEntity<?> signup(@Valid @RequestBody User user) throws UserAlreadyExistsException{
		try {
			this.userService.signup(user);
			System.out.println("successfull registration!!!");
			responseEntity = new ResponseEntity<>(user, HttpStatus.CREATED);
		}catch(UserAlreadyExistsException e) {
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity<>("Some internal error has occured..", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;

	}

	@PostMapping("/login/{id}/{pass}")
	public ResponseEntity<?> login(@PathVariable("id") int id,@PathVariable("pass") String pass) throws passwordDoesNotMatchException, UserNotFoundException{
		try {

			UserDto userLogin = this.userService.login(id, pass);
			responseEntity = new ResponseEntity<>(userLogin,HttpStatus.OK);
		}catch(passwordDoesNotMatchException e) {
			throw e;
		}catch(UserNotFoundException e) {
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity<>("Some internal error has occured..", HttpStatus.INTERNAL_SERVER_ERROR);	
		}
		return responseEntity;
	}

	@GetMapping("/users")
	public ResponseEntity<?> getAllUsers(){

		try {
			List<User> userList = this.userService.getAllUsers();
			responseEntity = new ResponseEntity<>(userList,HttpStatus.OK);

		}catch(Exception e) {
			System.out.println("Some internal error has occured, try again.");

		}

		return responseEntity;

	}


	@PostMapping("/users")
	public ResponseEntity<?> addUser(@Valid @RequestBody User user) throws UserAlreadyExistsException{

		try {
			User addedUser = this.userService.addUser(user);
			System.out.println("created new user: "+ addedUser);
			responseEntity = new ResponseEntity<>(user, HttpStatus.CREATED);

		}catch(UserAlreadyExistsException e) {
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity<>("Some internal error has occured..", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return responseEntity;

	}
	@GetMapping("/users/find_from_address/{address}")
	public ResponseEntity<?> getUserByAddress(@PathVariable("address") String address){

		try {
			List<User> userFound = this.userService.findUserByAddress(address);
			responseEntity = new ResponseEntity<>(userFound, HttpStatus.OK);	
		}catch(Exception e) {
			responseEntity = new ResponseEntity<>("Some internal error has occured..", HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return responseEntity;
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<?> getUserById(@PathVariable("id") int id) throws UserNotFoundException{

		try {
			User userFound = this.userService.getUserByID(id);
			responseEntity = new ResponseEntity<>(userFound, HttpStatus.OK);	
		}catch(UserNotFoundException e) {
			throw e;
		}catch(Exception e) {
			responseEntity = new ResponseEntity<>("Some internal error has occured..", HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return responseEntity;
	}


	@PutMapping("/users")
	public ResponseEntity<?> updateUser(@Valid @RequestBody User user) throws UserNotFoundException{

		try {
			User userFound = this.userService.updateUser(user);
			responseEntity = new ResponseEntity<>(userFound, HttpStatus.OK);
		}catch(UserNotFoundException e) {
			throw e;
		}catch(Exception e) {
			responseEntity = new ResponseEntity<>("Some internal error has occured..", HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();	
		}
		return responseEntity;
	}


	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> deleteUser( @PathVariable("id") int id) throws UserNotFoundException{

		try {
			boolean status = this.userService.deleteUser(id)	;
			responseEntity = new ResponseEntity<>("Deleted user! sorry to see you go..", HttpStatus.OK);
		}catch(UserNotFoundException e) {
			throw e;
		}catch(Exception e) {
			responseEntity = new ResponseEntity<>("Some internal error has occured..", HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return responseEntity;
	}


	@PutMapping("/users/id/{id}/vhs")
	public ResponseEntity<?> rentVHS(@Valid @PathVariable("id") int id,@RequestBody VHS vhs) throws UserNotFoundException, VHSAlreadyExistsException{

		try {
			User userFound = this.userService.addVHStoUser(id,vhs);
			responseEntity = new ResponseEntity<>(userFound, HttpStatus.OK);
		}catch(UserNotFoundException e) {
			throw e;
		}catch(VHSAlreadyExistsException e) {
			throw e;
		}catch(Exception e) {
			responseEntity = new ResponseEntity<>("Some internal error has occured..", HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return responseEntity;
	}



	@DeleteMapping("/users/id/{id}/vhs")
	public ResponseEntity<?> removeUserVHS(@Valid @PathVariable("id") int id) throws UserNotFoundException{

		try {
			User userFound = this.userService.removeVHSfromUser(id);
			responseEntity = new ResponseEntity<>(userFound, HttpStatus.OK);
		}catch(UserNotFoundException e) {
			throw e;
		}catch(Exception e) {
			responseEntity = new ResponseEntity<>("Some internal error has occured..", HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return responseEntity;
	}



	@GetMapping("/users/vhs/{vhs}")
	public ResponseEntity<?> findUserByVhs(@PathVariable("vhs") String vhs) throws VHSNotFoundException{

		try {
			List<User> userFound = this.userService.findUserByVhs(vhs);
			responseEntity = new ResponseEntity<>(userFound, HttpStatus.OK);
		}catch(Exception e) {
			responseEntity = new ResponseEntity<>("Some internal error has occured..", HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return responseEntity;
	}
	@GetMapping("/users/name/{name}/{surname}")
	public ResponseEntity<?> findUserByFullName(@PathVariable("name") String name,@PathVariable("surname") String surname) throws VHSNotFoundException{

		try {
			List<User> userFound = this.userService.findUserByNameAndSurname(name,surname);
			responseEntity = new ResponseEntity<>(userFound, HttpStatus.OK);
		}catch(Exception e) {
			responseEntity = new ResponseEntity<>("Some internal error has occured..", HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return responseEntity;
	}


	@GetMapping("/users/short_detail")
	public ResponseEntity<?> getShortDetailsUsers(){	
		
		try {
			List<UserDto> userDtoList = this.userService.userShortDetails();
			responseEntity = new ResponseEntity<>(userDtoList, HttpStatus.OK);

		} catch (Exception e) {
			responseEntity = new ResponseEntity<>("Some internal error has occured.. Please try again!", HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return responseEntity;
	}

}
