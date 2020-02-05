package model.intefaces;

import java.util.List;
import model.entities.Department;

public interface DepartmentDaoInterface {

	void insert(Department dpt);
	void deleteById(Integer id);
	void update(Department dpt, Integer id);
	List<Department> findAll();
	Department findById(Integer id);

}
