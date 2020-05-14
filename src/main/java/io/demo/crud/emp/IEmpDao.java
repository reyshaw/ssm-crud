package io.demo.crud.emp;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmpDao extends JpaRepository<Employee, Integer> {

}
