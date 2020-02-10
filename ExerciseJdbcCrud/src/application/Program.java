package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import db.DB;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) throws SQLException {
		
		Connection conn =  DB.getConnection();
		
		Department dpt = new Department(17, "test");
//		
//		DepartmentDao dptDao = new DepartmentDao(conn);
			
//		dptDao.insert(dpt);
//		dptDao.deleteById(11);	
//		dptDao.update(dpt, 5);
//		System.out.println(dptDao.findById(1));
		
//		List<Department> list = new ArrayList<>();
//		list = dptDao.findAll();	
//		list.forEach(System.out::println);
		
		Seller seller = new Seller(17, "Padeiro Joao", "padjo@gmail.com", new Date(), 4500.0, dpt);
		
		SellerDao sellerDao = new SellerDao(conn);
		
		//sellerDao.insert(seller);
		//sellerDao.deleteById(18);
		//sellerDao.update(seller, 20);
		//System.out.println(sellerDao.findById(21));
		System.out.println(sellerDao.findAll());
		
		DB.closeConnection(conn);
	}

}
