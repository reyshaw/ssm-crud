package io.demo.crud.log;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ILogDao extends JpaRepository<Log, Integer> {

}
