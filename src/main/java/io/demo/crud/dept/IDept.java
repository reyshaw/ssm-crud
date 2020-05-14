package io.demo.crud.dept;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IDept extends JpaRepository<Department, Integer> {

}
