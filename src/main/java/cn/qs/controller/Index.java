package cn.qs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 整合layui+thymeleaf
 * 
 * @author Administrator
 *
 */
@Controller
public class Index {
	@RequestMapping("/index")
	public String index(ModelMap map) {
		map.addAttribute("name", "testname");
		return "index";
	}

	@RequestMapping("/seekerIndex")
	public String seekerIndex(ModelMap map) {
		return "seekerIndex";
	}

	@RequestMapping("/welcome")
	public String welcome(ModelMap map) {
		return "welcome";
	}

	@RequestMapping("/preIndex")
	public String preIndex(ModelMap map) {
		return "preIndex";
	}

}
