package model.intefaces;

import java.util.List;

import model.entities.Seller;

public interface SellerDaoInterface {

	void insert(Seller seller);
	void deleteById(Integer id);
	void update(Seller seller, Integer id);
	List<Seller> findAll();
	Seller findById(Integer id);
	
}
