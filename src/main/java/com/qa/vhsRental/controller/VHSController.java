package com.qa.vhsRental.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.catalina.connector.Response;
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

import com.qa.vhsRental.entity.User;
import com.qa.vhsRental.entity.VHS;
import com.qa.vhsRental.exception.UserNotFoundException;
import com.qa.vhsRental.exception.VHSAlreadyExistsException;
import com.qa.vhsRental.exception.VHSNotFoundException;
import com.qa.vhsRental.service.VHSServiceImpl;

@RestController
@RequestMapping("api/v1")
public class VHSController {
	
	@Autowired
	VHSServiceImpl vhsService;

	ResponseEntity<?> responseEntity;
	
	
	@GetMapping("/vhs")
	public ResponseEntity<?> getAllVHS(){

		try {
			List<VHS> vhsList = this.vhsService.getAllVHS();
			responseEntity = new ResponseEntity<>(vhsList,HttpStatus.OK);

		}catch(Exception e) {
			System.out.println("Some internal error has occured, try again.");

		}

		return responseEntity;

	}
	
	@PostMapping("/vhs")
	public ResponseEntity<?> addVHS(@Valid @RequestBody VHS vhs) throws VHSAlreadyExistsException{

		try {
			VHS addedVHS = this.vhsService.addVHS(vhs);
			System.out.println("created new VHS: "+ addedVHS);
			responseEntity = new ResponseEntity<>(vhs, HttpStatus.CREATED);

		}catch(VHSAlreadyExistsException e) {
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity<>("Some internal error has occured..", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return responseEntity;

	}
	
	
	@GetMapping("/vhs/{id}")
	public ResponseEntity<?> getVHSById(@PathVariable("id") int id) throws VHSNotFoundException{

		try {
			VHS vhsFound = this.vhsService.getVHSByID(id);
			responseEntity = new ResponseEntity<>(vhsFound, HttpStatus.OK);	
		}catch(VHSNotFoundException e) {
			throw e;
		}catch(Exception e) {
			responseEntity = new ResponseEntity<>("Some internal error has occured..", HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return responseEntity;
	}
	
	@PutMapping("/vhs")
	public ResponseEntity<?> updateVHS( @RequestBody VHS vhs) throws VHSNotFoundException{

		try {
			VHS vhsFound = this.vhsService.updateVHS(vhs);
			responseEntity = new ResponseEntity<>(vhsFound, HttpStatus.OK);
		}catch(VHSNotFoundException e) {
			throw e;
		}catch(Exception e) {
			responseEntity = new ResponseEntity<>("Some internal error has occured..", HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return responseEntity;
	}

	
	@DeleteMapping("/vhs/{id}")
	public ResponseEntity<?> deleteVHS( @PathVariable("id") int id) throws VHSNotFoundException{

		try {
			boolean status = this.vhsService.deleteVHS(id);
			responseEntity = new ResponseEntity<>("Deleted VHS!", HttpStatus.OK);
		}catch(VHSNotFoundException e) {
			throw e;
		}catch(Exception e) {
			responseEntity = new ResponseEntity<>("Some internal error has occured..", HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return responseEntity;
	}
	
	@GetMapping("/vhs/rented")
	public ResponseEntity<?> numberRentedVHS() throws VHSNotFoundException{
		
		try {
			int numberFound = this.vhsService.numberRentedVHS();
			responseEntity = new ResponseEntity<>(numberFound, HttpStatus.OK);
		}catch(VHSNotFoundException e) {
			throw e;
		}catch(Exception e) {
			responseEntity = new ResponseEntity<>("Some internal error has occured..", HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		return responseEntity;
		
		
	}
	
	@GetMapping("/vhs/rented_list")
	public ResponseEntity<?> rentedVHSList() throws VHSNotFoundException{
		
		try {
			List<VHS> listVHS = this.vhsService.rentedVHSList();
			responseEntity = new ResponseEntity<>(listVHS, HttpStatus.OK);
		}catch(VHSNotFoundException e) {
			throw e;
		}catch(Exception e) {
			responseEntity = new ResponseEntity<>("Some internal error has occured..", HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		
		return responseEntity;
		
		
	}
	
	@GetMapping("/vhs/author/{author}/name/{name}")
	public ResponseEntity<?> getByAuthorAndName(@PathVariable("name") String name,@PathVariable("author") String author){
		
		try {
			VHS listVHS = this.vhsService.getVHSByAuthorAndName(name,author);
			responseEntity = new ResponseEntity<>(listVHS, HttpStatus.OK);
		}catch(Exception e) {
			responseEntity = new ResponseEntity<>("Some internal error has occured..", HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		
		return responseEntity;
		
	}
	
	@GetMapping("/vhs/author/{author}")
	public ResponseEntity<?> getByAuthor(@PathVariable("author") String author ){
		
		try {
			List<VHS> listVHS = this.vhsService.getVHSByAuthor(author);
			responseEntity = new ResponseEntity<>(listVHS, HttpStatus.OK);
		}catch(Exception e) {
			responseEntity = new ResponseEntity<>("Some internal error has occured..", HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		
		return responseEntity;
		
	}
	@GetMapping("/vhs/name/{name}")
	public ResponseEntity<?> getByName(@PathVariable("name") String name ){
		
		try {
			VHS listVHS = this.vhsService.getVHSByName(name);
			responseEntity = new ResponseEntity<>(listVHS, HttpStatus.OK);
		}catch(Exception e) {
			responseEntity = new ResponseEntity<>("Some internal error has occured..", HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		
		return responseEntity;
		
	}
	
	
}


