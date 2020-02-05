package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program {

	public static void main(String[] args) throws SQLException {
		
		Connection conn =  DB.getConnection();
		
		Department dpt = new Department("novo");
		
		DepartmentDao dptDao = new DepartmentDao(conn);
			
		//dptDao.insert(dpt);
		//dptDao.deleteById(11);	
		//dptDao.update(dpt, 5);
		//System.out.println(dptDao.findById(1));
		
		List<Department> list = new ArrayList<>();
		list = dptDao.findAll();	
		list.forEach(System.out::println);
		
		
		DB.closeConnection(conn);
	}

}
