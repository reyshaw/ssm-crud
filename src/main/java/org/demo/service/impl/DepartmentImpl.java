package org.demo.service.impl;

import java.util.List;

import org.demo.dao.DepartmentMapper;
import org.demo.entity.Department;
import org.demo.service.IDepartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentImpl implements IDepartment {

	@Autowired
	private DepartmentMapper deptMapper;
	
	public List<Department> getDept() {
		return deptMapper.selectAll();
	}

}
