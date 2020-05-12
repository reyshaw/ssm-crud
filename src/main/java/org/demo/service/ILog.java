package org.demo.service;

import java.util.List;

import org.demo.entity.Log;

public interface ILog {
	int addLog(Log log);
	
	List<Log> getLogs();
}
