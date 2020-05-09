package org.demo.dao;

import java.util.List;
import org.demo.entity.Employee;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Integer empId);

    int insert(Employee record);

    Employee selectByPrimaryKey(Integer empId);
    
    Employee selectByName(String name);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee record);
    
}