package org.demo.service;

import java.util.List;

import org.demo.entity.Employee;

public interface IEmployee {
	
	Employee getlEmployeeById(Integer id);
	
	List<Employee> getAllEmployee();
	
	int addEmployee(Employee emp);
	
	int updateEmployee(Employee emp);
	
	int deleteEmployee(Integer id);
}
