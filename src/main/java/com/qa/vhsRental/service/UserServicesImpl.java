package com.qa.vhsRental.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.qa.vhsRental.entity.User;
import com.qa.vhsRental.entity.VHS;
import com.qa.vhsRental.repository.UserRepository;

public class UserServicesImpl implements UserServices {
	
	
	@Autowired
	UserRepository userRepository;
	

	@Override
	public List<User> getAllUsers() {
		
		return userRepository.findAll();
	}

	@Override
	public User addUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByID(int id) {
		Optional<User> userFoundByIdOptional = this.userRepository.findById(id);
		return null;
	}
	
	//@Override
	//public Product getProductByID(int id) throws ProductNotFoundException{
	//
//		Optional<Product> productFoundByIdOptional = this.productRepository.findById(id);
//		if (!productFoundByIdOptional.isPresent())
//			throw new ProductNotFoundException();
//		return productFoundByIdOptional.get();
	//}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User addVHStoUser(VHS vhs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User removeVHSfromUser(VHS vhs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByRentedVHS(VHS vhs) {
		// TODO Auto-generated method stub
		return null;
	}

}



//@Override
//public Product getProductByID(int id) throws ProductNotFoundException{
//
//	Optional<Product> productFoundByIdOptional = this.productRepository.findById(id);
//	if (!productFoundByIdOptional.isPresent())
//		throw new ProductNotFoundException();
//	return productFoundByIdOptional.get();
//}
//
//@Override
//public Product addProduct(Product product) throws ProductAlreadyExistsException{
//	
//	Optional<Product> productFoundByIdOptional = this.productRepository.findById(product.getId());	
//	if(productFoundByIdOptional.isPresent()) {
//		throw new ProductAlreadyExistsException();
//	}
//	else {
//		return this.productRepository.save(product);
//	}
//	
//}
//
//@Override
//public Product updateProduct(Product product) throws ProductNotFoundException {
//	
//	Optional<Product> productFoundByIdOptional = this.productRepository.findById(product.getId());	
//	if(!productFoundByIdOptional.isPresent()) {
//		throw new ProductNotFoundException();
//	}
//	else {
//		return this.productRepository.save(product);
//	}
//}
//
//@Override
//public Boolean deleteProduct(int id) throws ProductNotFoundException {
//	
//	boolean status = false;
//	
//	Optional<Product> productFoundByIdOptional = this.productRepository.findById(id);
//	if(!productFoundByIdOptional.isPresent()) {
//		throw new ProductNotFoundException();
//	}
//	else {
//		this.productRepository.delete(productFoundByIdOptional.get());
//		status = true;
//	}
//	
//	return status;
//}
//
//@Override
//public List<Product> getProductsByPrice(double price) {
//	
//	return this.productRepository.findByPrice(price);
//}
//
//@Override
//public double getTotalPriceOfAllProducts() {
//	
//	return this.productRepository.totalPriceOfAllProducts();
//}
//
//@Override
//public List<Product> getProductByNameAndPrice(String name, double price) {
//
//	return this.productRepository.findProductByNameAndPrice(name, price);
//}
//
//@Override
//public List<Product> getProductByName(String name) {
//	
//	return this.productRepository.findByName(name);
//}
//
//@Override
//public Product updateProductDetails(int id, String name, double price) throws ProductNotFoundException{
//	Product updatedProduct = null;
//	
//	Optional<Product> findByIdOptional = this.productRepository.findById(id);
//	if(!findByIdOptional.isPresent()) {
//		throw new ProductNotFoundException();
//	}
//	else {
//		int rows = this.productRepository.updateProductDetails(id, name, price);
//		if (rows > 0) {
//			updatedProduct = this.productRepository.findById(id).get();
//		}
//	}
//	return updatedProduct;
//}
//
//@Override
//public List<ProductDto> getProductDetails() {
//
//	
//	return this.productRepository.findAll().stream().map(this::mapToProductDto).collect(Collectors.toList());
//	
//}
//
//private ProductDto mapToProductDto(Product product) {
//	return this.modelMapper.map(product, ProductDto.class);
//}
//
//

