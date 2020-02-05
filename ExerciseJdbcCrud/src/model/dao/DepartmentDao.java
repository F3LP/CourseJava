package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DBException;
import model.entities.Department;
import model.intefaces.DepartmentDaoInterface;

public class DepartmentDao implements DepartmentDaoInterface {

	private Connection conn = null;

	public DepartmentDao(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Department dpt) {
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement("INSERT INTO department (Name)" + "VALUES (?)");
			ps.setString(1, dpt.getName());

			ps.executeUpdate();

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
			ps = conn.prepareStatement("DELETE FROM department WHERE id = ?");
			ps.setInt(1, id);
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new DBException(e.getMessage());
			
		} finally {
			DB.closeStatement(ps);
		}
	}

	@Override
	public void update(Department dpt, Integer id) {
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement("UPDATE department SET Name = ? WHERE Id = ? ");
			ps.setString(1, dpt.getName());
			ps.setInt(2, id);
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new DBException(e.getMessage());
			
		} finally {
			DB.closeStatement(ps);
		}
	}

	@Override
	public List<Department> findAll() {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try  {
			ps = conn.prepareStatement("SELECT * FROM department ORDER BY Name");
			rs = ps.executeQuery();
			
			List<Department> list = new ArrayList<>();
			
			while (rs.next()) {
				Department dpt = new Department();
				dpt.setId(rs.getInt("Id"));
				dpt.setName(rs.getString("Name"));
				
				list.add(dpt);
			}			
			
		return list;
		
		} catch (SQLException e) {
			throw new DBException(e.getMessage());
			
		} finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			ps = conn.prepareStatement("SELECT * FROM department WHERE Id = ?");
			ps.setInt(1, id);

			rs = ps.executeQuery();

			while (rs.next()) {

				Department dpt = new Department();
				dpt.setId(rs.getInt("Id"));
				dpt.setName(rs.getString("Name"));

				return dpt;
			}

		} catch (SQLException e) {
			throw new DBException(e.getMessage());
			
		} finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
		return null;
	}
}
