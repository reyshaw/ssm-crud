package io.demo.crud.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServ {
	
	@Autowired
	private ILogDao logDao;
	
	// 添加日志
	public Log addLog(Log log) {
		return logDao.save(log);
	}
}
