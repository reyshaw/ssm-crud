package io.demo.crud.dept;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.demo.crud.exception.BusinessException;

@Service
public class DeptServ {
	
	@Autowired
	private IDept deptDao;
	
	/**
	 * 查询全部部门
	 * @return
	 * @throws BusinessException 
	 */
	public List<Department> getDepts() throws BusinessException {
		List<Department> depts = deptDao.findAll();
		if (depts.size() == 0) {
			throw new BusinessException(DeptError.NO_DEPARTMENT.getCode(), DeptError.NO_DEPARTMENT.getMessage());
		} else {
			return depts;
		}
	}
}
