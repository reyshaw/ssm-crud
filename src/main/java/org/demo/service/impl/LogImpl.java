package org.demo.service.impl;

import java.util.List;

import org.demo.dao.LogMapper;
import org.demo.entity.Log;
import org.demo.service.ILog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogImpl implements ILog {

	@Autowired
	private LogMapper logDao;
	
	public int addLog(Log log) {
		return logDao.addLog(log);
	}

	public List<Log> getLogs() {
		return logDao.selectAll();
	}

}
