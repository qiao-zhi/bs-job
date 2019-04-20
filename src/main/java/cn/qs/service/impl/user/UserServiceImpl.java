package cn.qs.service.impl.user;

import cn.qs.bean.user.User;
import cn.qs.dao.user.UserRepository;
import cn.qs.service.user.UserService;
import cn.qs.utils.MD5Util;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userDao;

	public List<User> findAllUser() {
		List<User> list = userDao.findAll();
		return list;
	}

	@Override
	public void addUser(User user) {
		userDao.insert(user);
	}

	@Override
	public Page<User> getUsersPage(Map condition) {
		User user = new User();
		if(StringUtils.isNotBlank(MapUtils.getString(condition,"username"))){
			user.setUsername(MapUtils.getString(condition,"username"));
		}

		ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("username",ExampleMatcher.GenericPropertyMatchers.contains());//查询fullname包含修改
		Example example = Example.of(user,matcher);

		//构造请求参数，页号从0开始。
		int pageNum = MapUtils.getInteger(condition,"pageNum",0);
		int pageSize = MapUtils.getInteger(condition,"pageSize",0);
		Pageable pageRequest = new QPageRequest(pageNum,pageSize);

		Page<User> page = userDao.findAll(example,pageRequest);
		return  page;
	}

	@Override
	public void deleteUser(String id) {
		userDao.delete(id);
	}

	@Override
	public User getUser(String id) {
		return userDao.findOne(id);
	}

	@Override
	public void updateUser(User user) {
		String updatedPassword = user.getPassword();
		if(StringUtils.isBlank(updatedPassword)){
			User tmp = userDao.findByUsername(user.getUsername());
			user.setPassword(tmp.getPassword());
		}else {
			user.setPassword(MD5Util.md5(user.getPassword(), ""));
		}

		userDao.save(user);
	}

	@Override
	public User getUserByUserNameAndPassword(String username, String password) {
		password = MD5Util.md5(password,"");
		return userDao.findByUsernameAndPassword(username,password	);
	}

	@Override
	public User findUserByUsername(String username) {
		return userDao.findByUsername(username);
	}
}
