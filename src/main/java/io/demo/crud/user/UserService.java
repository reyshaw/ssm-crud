package io.demo.crud.user;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import io.demo.crud.exception.BusinessException;
import io.demo.crud.vo.request.LoginRequest;

@Service
public class UserService {
	
	@Autowired
	private IUserDao userDao;
	
	/**
	 * 登录 根据用户名和密码查询
	 * @param user
	 * @return
	 * @throws BusinessException 
	 */
	public User getUserByUsernameAndPassword(LoginRequest user) throws BusinessException {
		Map<String,String> error = user.valiate(user);
		if (error.size() > 0) { // 参数校验失败
			throw new BusinessException(UserError.PARAM_ERROR.getCode(), UserError.PARAM_ERROR.getMessage(), error);
		} else {
			User u = userDao.findByUsernameAndPassword(user.getUsername(), user.getPassword());
			if (null != u) {
				return u;
			}
			throw new BusinessException(UserError.USERNAME_PASSWORD_INCORRECT.getCode(), UserError.USERNAME_PASSWORD_INCORRECT.getMessage());
		}
	}
	
	/**
	 * 注册业务
	 * @param user
	 * @return
	 * @throws BusinessException
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	public User addUser(User user, MultipartFile file, HttpServletRequest req) throws BusinessException, IllegalStateException, IOException {
		// 查看用户是否存在
		User newUser = userDao.findByUsername(user.getUsername());
		if (null != newUser) {
			throw new BusinessException(UserError.USER_EXIST.getCode(), UserError.USER_EXIST.getMessage());
		}
		// 获取参数
		User regUser = new User();
		BeanUtils.copyProperties(user, regUser); // 复制对象
		regUser.setAvatar(handleFile(file, req));
		regUser.setId(null);
		// 添加用户
		newUser = userDao.save(regUser);
		if (null != newUser) {
			return newUser;
		}
		throw new BusinessException(UserError.ADD_USER_FAIL.getCode(), UserError.ADD_USER_FAIL.getMessage());
	}
	
	/**
	 * 处理文件上传
	 * @param upload
	 * @throws IllegalStateException
	 * @throws IOException
	 * @throws BusinessException
	 */
	private String handleFile(MultipartFile upload, HttpServletRequest req) throws IllegalStateException, IOException, BusinessException {
		final String FILE_PATH = "/uploads/";

		// 判断文件是否为空
		if (upload.isEmpty()) {
			throw new BusinessException(UserError.EMPTY_FILE.getCode(), UserError.EMPTY_FILE.getMessage());
		}
		// 构建路径
		String path = req.getServletContext().getRealPath(FILE_PATH);
		
		// 生成目录
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		// 获取文件名并生成文件名
		String uuid = UUID.randomUUID().toString().replace("-", "");
		String fileName = uuid +  "_" + upload.getOriginalFilename();
		// 传送文件
		upload.transferTo(new File(path, fileName));
		
		return FILE_PATH + fileName;
	}
}
