package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import entities.Employee;

public class Program {

	public static void main(String[] args) throws FileNotFoundException, IOException {

		Locale.setDefault(Locale.US);
		String path = "C:\\Users\\facd_\\Desktop\\doc.txt";
		List<Employee> employees = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line = br.readLine();
			while (line != null) {

				String[] splitado = line.split(",");

				Employee employee = new Employee(splitado[0], Double.parseDouble(splitado[1]));
				employees.add(employee);

				line = br.readLine();
			}
		}
		employees.sort((x1, x2) -> x1.getName().toLowerCase().compareTo(x2.getName().toLowerCase()));
		
//		employees.sort((p1, p2) -> p1.getSalary().compareTo(p2.getSalary()));
		
		for (Employee employee : employees) {
			System.out.println(employee);
		}
	}

}
