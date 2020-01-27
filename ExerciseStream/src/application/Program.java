package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

import entities.Employee;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		
		List<Employee> list = new ArrayList<>();
		
		String path = "C:\\Users\\facd_\\Desktop\\ExerciciosStream.csv";
		
		try(BufferedReader br = new BufferedReader(new FileReader(path))){
			
			String line = br.readLine();
			while(line != null) {
				
				String[] lineSplit = line.split(",");
				
				list.add(new Employee(lineSplit[0], lineSplit[1], Double.parseDouble(lineSplit[2])));
				
				line = br.readLine();
			}
			
		} catch(IOException e) {
			e.printStackTrace();
		}

		list.forEach(System.out::println);
		System.out.println("=============");
		
		Stream<Employee> listStream = list.stream()
											.filter(s -> s.getSalary() > 2000)
											.sorted((s1, s2) -> s1.getEmail().toLowerCase().compareTo(s2.getEmail().toLowerCase()));
		
				
		listStream.forEach(System.out::println);
		System.out.println("=============");
		
		int sum = (int) list.stream()
							.filter(p -> p.getName().toLowerCase().startsWith("m"))
							.mapToDouble(Employee::getSalary)
							.sum();
		
		System.out.println(sum);
										
	}

}
