package com.qa.vhsRental.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.vhsRental.entity.VHS;
import com.qa.vhsRental.exception.VHSAlreadyExistsException;
import com.qa.vhsRental.exception.VHSNotFoundException;
import com.qa.vhsRental.repository.VHSRepository;

@Service
public class VHSServiceImpl implements VHSService {

	
	
	@Autowired
	VHSRepository vhsRepository;

	@Override
	public List<VHS> getAllVHS() {
		return this.vhsRepository.findAll();
	}

	@Override
	public VHS addVHS(VHS vhs) throws VHSAlreadyExistsException {
		Optional<VHS> vhsFoundByIdOptional = this.vhsRepository.findById(vhs.getId());
		if (vhsFoundByIdOptional.isPresent())
			throw new VHSAlreadyExistsException();
		else
			return this.vhsRepository.save(vhs);
	}

	@Override
	public VHS getVHSByID(int id) throws VHSNotFoundException {
		Optional<VHS> vhsFoundByIdOptional = this.vhsRepository.findById(id);
		if (vhsFoundByIdOptional.isPresent())
			throw new VHSNotFoundException();
		
		return vhsFoundByIdOptional.get();
	}

	@Override
	public VHS updateVHS(VHS vhs) throws VHSNotFoundException {
		Optional<VHS> vhsFoundByIdOptional = this.vhsRepository.findById(vhs.getId());
		if (!vhsFoundByIdOptional.isPresent())
			throw new VHSNotFoundException();
		else 
			return this.vhsRepository.save(vhs);
		
	}

	@Override
	public Boolean deleteVHS(int id) throws VHSNotFoundException {
		boolean status = false;
		Optional<VHS> vhsFoundByIdOptional = this.vhsRepository.findById(id);
		if (!vhsFoundByIdOptional.isPresent()) {
			throw new VHSNotFoundException();
		}else {
			this.vhsRepository.delete(vhsFoundByIdOptional.get());
			status = true;
		}

		return status;
	}

	@Override
	public int numberRentedVHS() throws VHSNotFoundException {
		int count = 0;
		count = this.vhsRepository.findAll()
								  .stream()
								  .filter((vhs) -> vhs.getIsRented() == true)
								  .collect(Collectors.counting()).intValue();
		return count;
	}
	
	@Override
	public List<VHS> rentedVHSList() throws VHSNotFoundException {
		
		List<VHS> rentedVHS = this.vhsRepository.findAll()
												.stream()
												.filter((vhs) -> vhs.getIsRented() == true)
												.collect(Collectors.toList());
		return rentedVHS;
	}
	
//	@Override
//	public List<VHS> getVHSByAuthor(String author) {
//		List<VHS> listVHSByAuthor = this.vhsRepository.findByAuthor(author);
//		return listVHSByAuthor;
//	}
	
	
	
}
