package cn.qs.controller.user;

import cn.qs.bean.user.User;
import cn.qs.service.user.UserService;
import cn.qs.utils.DefaultValue;
import cn.qs.utils.JSONResultUtil;
import cn.qs.utils.MD5Util;
import cn.qs.utils.ValidateCheck;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

@Controller /** 自动返回的是json格式数据 ***/
@RequestMapping("user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@RequestMapping("/user_list")
	public String user_list() {
		return "user/user-list";
	}

	@RequestMapping("/user-add")
	public String user_add() {
		return "user/user-add";
	}

	@Autowired
	private UserService userService;

	/**
	 * 添加user
	 * 
	 * @param user
	 *            springMVC自动映射的实体
	 * @return
	 */
	@RequestMapping("addUser")
	@ResponseBody
	public JSONResultUtil addUser(User user) {
		if (user != null && "admin".equals(user.getUsername())) {
			return JSONResultUtil.error("您不能添加管理员用户");
		}

		User findUser = userService.findUserByUsername(user.getUsername());
		if (findUser != null) {
			return JSONResultUtil.error("用户已经存在");
		}

		user.setCreatetime(new Date());
		user.setPassword(MD5Util.md5(user.getPassword(), ""));// md5加密密码
		logger.info("user -> {}", user);
		userService.addUser(user);
		return JSONResultUtil.ok();
	}

	/**
	 * 分页查询user
	 * 
	 * @param condition
	 * @return
	 */
	@RequestMapping("getUsers")
	@ResponseBody
	public Page<User> getUsers(@RequestParam Map condition) {
		int pageNum = 1;
		if (ValidateCheck.isNotNull(MapUtils.getString(condition, "pageNum"))) { // 如果不为空的话改变当前页号
			pageNum = MapUtils.getInteger(condition, "pageNum");
		}
		int pageSize = DefaultValue.PAGE_SIZE;
		if (ValidateCheck.isNotNull(MapUtils.getString(condition, "pageSize"))) { // 如果不为空的话改变当前页大小
			pageSize = MapUtils.getInteger(condition, "pageSize");
		}

		condition.put("pageNum",pageNum-1);
		condition.put("pageSize",pageSize);

		Page<User> users = null;
		// 开始分页
		try {
			users = userService.getUsersPage(condition);
		} catch (Exception e) {
			logger.error("getUsers error！", e);
		}

		return users;
	}

	/**
	 * 删除user
	 * 
	 *            springMVC自动映射的实体
	 * @return
	 */
	@RequestMapping("deleteUser")
	@ResponseBody
	public JSONResultUtil deleteUser(String  id) {
		userService.deleteUser(id);
		return JSONResultUtil.ok();
	}

	/**
	 * 跳转打修改用户页面
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("updateUser")
	public String updateUser(String id, String from, ModelMap map, HttpServletRequest request) {
		if ("personal".equals(from)) {
			User user = (User) request.getSession().getAttribute("user");
			id = user.getId();
		}

		User user = userService.getUser(id);
		map.addAttribute("user", user);
		return "user/updateUser";
	}

	/**
	 * 添加user
	 * 
	 * @param user
	 *            springMVC自动映射的实体
	 * @return
	 */
	@RequestMapping("doUpdateUser")
	@ResponseBody
	public JSONResultUtil doUpdateUser(User user) {
		logger.info("user -> {}", user);
		userService.updateUser(user);
		return JSONResultUtil.ok();
	}
}