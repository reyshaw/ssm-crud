package org.demo.dao;

import java.util.List;

import org.demo.entity.Log;

public interface LogMapper {
	
	List<Log> selectAll();
	
	int addLog(Log log);

}
