package com.qa.vhsRental.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qa.vhsRental.entity.VHS;
import com.qa.vhsRental.exception.UserAlreadyExistsException;
import com.qa.vhsRental.exception.VHSAlreadyExistsException;
import com.qa.vhsRental.exception.VHSNotFoundException;

@Service
public interface VHSService {
	
	public List<VHS> getAllVHS();
	public VHS addVHS(VHS vhs) throws VHSAlreadyExistsException, UserAlreadyExistsException;
	public VHS getVHSByID(int id) throws VHSNotFoundException;
	public VHS updateVHS(VHS vhs) throws VHSNotFoundException;
	public Boolean deleteVHS(int id) throws VHSNotFoundException;
    public int numberRentedVHS() throws VHSNotFoundException;
	public List<VHS> rentedVHSList() throws VHSNotFoundException;
	public List<VHS> getVHSByAuthor(String author) throws VHSNotFoundException;
	

}
