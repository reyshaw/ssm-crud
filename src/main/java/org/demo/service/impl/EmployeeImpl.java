package org.demo.service.impl;

import java.util.List;

import org.demo.dao.EmployeeMapper;
import org.demo.entity.Employee;
import org.demo.service.IEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeImpl implements IEmployee {
	
	@Autowired
	private EmployeeMapper empMapper;
	
	public Employee getlEmployeeById(Integer id) {
		return empMapper.selectByPrimaryKey(id);
	}

	public List<Employee> getAllEmployee() {
		return empMapper.selectAll();
	}

	public int addEmployee(Employee emp) {
		Employee empExist = empMapper.selectByName(emp.getEmpName());
		if(null !=  empExist) {
			return -1;
		} else {
			return empMapper.insert(emp);
		}
	}
	
	public int updateEmployee(Employee emp) {
		Employee empExist = empMapper.selectByPrimaryKey(emp.getEmpId());
		if(null != empExist) {
			return empMapper.updateByPrimaryKey(emp);
		}
		return -1;
	}

	public int deleteEmployee(Integer id) {
		Employee empExist = empMapper.selectByPrimaryKey(id);
		if(null != empExist) {
			return empMapper.deleteByPrimaryKey(id);
		}
		return -1;
	}

}
