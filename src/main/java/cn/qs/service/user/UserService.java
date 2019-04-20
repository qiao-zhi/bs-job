package cn.qs.service.user;

import java.util.List;
import java.util.Map;

import cn.qs.bean.user.User;
import org.springframework.data.domain.Page;

public interface UserService {

	/**
	 * 根据接口查询所用的用户
	 */
	public List<User> findAllUser();
	
	public User findUserByUsername(String username);

	public void addUser(User user);

	public Page<User> getUsersPage(Map condition);

	public void deleteUser(String id);

	public User getUser(String id);

	public void updateUser(User user);

	public User getUserByUserNameAndPassword(String username, String password);
}
