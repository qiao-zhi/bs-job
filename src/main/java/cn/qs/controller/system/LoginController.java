package cn.qs.controller.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.qs.bean.user.JobSeeker;
import cn.qs.service.user.JobSeekerService;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.qs.bean.user.User;
import cn.qs.service.user.UserService;
import cn.qs.utils.JSONResultUtil;

/**
 * 登陆
 * 
 * @author Administrator
 *
 */
@Controller
public class LoginController {
	@Autowired
	private UserService userService;

	@Autowired
	private JobSeekerService jobSeekerService;

	/**
	 * 跳转到登陆界面
	 * 
	 * @return
	 */
	@RequestMapping("login")
	public String login(ModelMap map) {
		double ceil = Math.ceil(Math.random() * 8);
		int index  = (int) ceil;
		String src = "/static/x-admin/images/" + index+".jpg";
		map.put("src",src);

		return "login";
	}

	/**
	 * 处理登陆请求
	 * 
	 * @param username
	 * @param password
	 * @param session
	 * @return
	 */
	@RequestMapping("doLogin")
	@ResponseBody
	public JSONResultUtil doLogin(String username, String password, HttpSession session) {
		User loginUser = null;
		if ("admin".equals(username) && "admin".equals(password)) {
			loginUser = new User();
			loginUser.setFullname("系统管理员");
			loginUser.setUsername("admin");
			loginUser.setUsertype("1");
		} else {
			loginUser = userService.getUserByUserNameAndPassword(username, password);
		}

		if (loginUser == null) {
			return JSONResultUtil.error("账号或者密码错误");
		}

		session.setAttribute("user", loginUser);
		JSONResultUtil result = JSONResultUtil.ok();
		if(!"2".equals(loginUser.getUsertype())){
			result.setData("index.html");
		}else{
			JobSeeker seeker = jobSeekerService.getOrCreateSeekerByUsername(username);
			session.setAttribute("seeker", seeker);
			result.setData("seekerIndex.html");
		}

		return result;
	}
}
