package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DBException;
import model.entities.Department;
import model.entities.Seller;
import model.intefaces.SellerDaoInterface;

public class SellerDao implements SellerDaoInterface {

	private Connection conn = null;

	public SellerDao(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Seller seller) {

		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement("INSERT INTO seller " + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
										+ "VALUES " + "(?, ?, ?, ?, ?)",
										Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, seller.getName());
			ps.setString(2, seller.getEmail());
			ps.setDate(3, new Date(seller.getBirthDate().getTime()));
			ps.setDouble(4, seller.getBaseSalary());
			ps.setInt(5, seller.getDepartment().getId());

			int rowsAffected = ps.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					seller.setId(id);
				}
				DB.closeResultSet(rs);
			} else {
				throw new DBException("No rows affected.");
			}

		} catch (SQLException e) {
			throw new DBException(e.getMessage());
			
		} finally {
			DB.closeStatement(ps);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement("DELETE FROM seller WHERE Id = ?");
			
			ps.setInt(1, id);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			throw new DBException(e.getMessage());
			
		} finally {
			DB.closeStatement(ps);
		}
	}

	@Override
	public void update(Seller seller, Integer id) {
		
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement("UPDATE seller SET Name = ?, Email = ?, "
									+ "BirthDate = ?, BaseSalary = ?, "
									+ "DepartmentId = ? WHERE Id = ?");

			ps.setString(1, seller.getName());
			ps.setString(2, seller.getEmail());
			ps.setDate(3, new Date(seller.getBirthDate().getTime()));
			ps.setDouble(4, seller.getBaseSalary());
			ps.setInt(5, seller.getId());
			ps.setInt(6, id);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			throw new DBException(e.getMessage());
			
		} finally {
			DB.closeStatement(ps);
		}

	}

	@Override
	public List<Seller> findAll() {
		
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			try {
				ps = conn.prepareStatement(
						"SELECT seller.*,department.Name as DepName "
						+ "FROM seller INNER JOIN department "
						+ "ON seller.DepartmentId = department.Id "
						+ "ORDER BY Name");
				
				rs = ps.executeQuery();
				
				List<Seller> list = new ArrayList<>();
				Map<Integer, Department> map = new HashMap<>();
				
				while (rs.next()) {
					
					Department dep = map.get(rs.getInt("DepartmentId"));
					
					if (dep == null) {
						dep = instantiateDepartment(rs);
						map.put(rs.getInt("DepartmentId"), dep);
					}
					
					Seller obj = instantiateSeller(rs, dep);
					list.add(obj);
				}
				return list;
			}
			catch (SQLException e) {
				throw new DBException(e.getMessage());
			}
			finally {
				DB.closeStatement(ps);
				DB.closeResultSet(rs);
			}
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE seller.Id = ?");
			
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				Department dpt = instantiateDepartment(rs);
				Seller obj = instantiateSeller(rs, dpt);
				return obj;
			}
			return null;
		}
		catch (SQLException e) {
			throw new DBException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}
	
	private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
		Seller obj = new Seller();
		obj.setId(rs.getInt("Id"));
		obj.setName(rs.getString("Name"));
		obj.setEmail(rs.getString("Email"));
		obj.setBaseSalary(rs.getDouble("BaseSalary"));
		obj.setBirthDate(rs.getDate("BirthDate"));
		obj.setDepartment(dep);
		return obj;
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));
		return dep;
	}

}
