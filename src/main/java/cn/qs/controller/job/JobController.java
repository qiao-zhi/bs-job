package cn.qs.controller.job;

import cn.qs.bean.job.Job;
import cn.qs.bean.user.User;
import cn.qs.service.JobService.JobService;
import cn.qs.utils.DefaultValue;
import cn.qs.utils.JSONResultUtil;
import cn.qs.utils.ValidateCheck;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("job")
public class JobController {

	private static final Logger logger = LoggerFactory.getLogger(JobController.class);

	@Autowired
	private JobService messageService;

	@RequestMapping("addJob")
	public String addJob() {
		return "job/addJob";
	}

	@RequestMapping("doAddJob")
	@ResponseBody
	public JSONResultUtil doAddJob(Job job,HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");

		job.setCreatetime(new Date());
		job.setFullname(user.getFullname());
		job.setUsername(user.getUsername());

		messageService.insert(job);
		return JSONResultUtil.ok();
	}

	@RequestMapping("job_list")
	public String job_list() {
		return "job/job-list";
	}

	/**
	 * 分页查询user
	 *
	 * @param condition
	 * @return
	 */
	@RequestMapping("getJobs")
	@ResponseBody
	public Page<Job> getJobs(@RequestParam Map condition,HttpServletRequest request) {
		condition.put("username",((User) request.getSession().getAttribute("user")).getUsername());

		int pageNum = 1;
		if (ValidateCheck.isNotNull(MapUtils.getString(condition, "pageNum"))) { // 如果不为空的话改变当前页号
			pageNum = MapUtils.getInteger(condition, "pageNum");
		}
		int pageSize = DefaultValue.PAGE_SIZE;

		//来自前台请求不加条件
		String requestFrom = "seeker";
		if(requestFrom.equals(MapUtils.getString(condition,"from"))){
			pageSize = 15;
			condition.remove("username");

		}

		if (ValidateCheck.isNotNull(MapUtils.getString(condition, "pageSize"))) { // 如果不为空的话改变当前页大小
			pageSize = MapUtils.getInteger(condition, "pageSize");
		}
		condition.put("pageNum",pageNum-1);
		condition.put("pageSize",pageSize);

		Page<Job> messages = null;
		// 开始分页
		try {
			messages = messageService.getMessageServicePage(condition);
		} catch (Exception e) {
			logger.error("getUsers error！", e);
		}

		return messages;
	}

	@RequestMapping("/getJobdetail/{messageId}")
	public String getMessagedetail(ModelMap map, @PathVariable() String messageId, HttpServletRequest request) {
		Job message = messageService.getJobDetail(messageId);
		map.put("job", message);

		return "job/jobDetail";
	}

	@RequestMapping("deleteJob")
	@ResponseBody
	public JSONResultUtil deleteJob(String id) {
		messageService.deleteJob(id);
		return JSONResultUtil.ok();
	}

	@RequestMapping("updateJob")
	public String updateJob(String id, ModelMap map, HttpServletRequest request) {
		Job message = messageService.getJobDetail(id);
		map.addAttribute("job", message);
		return "job/updateJob";
	}

	@RequestMapping("doUpdateJob")
	@ResponseBody
	public JSONResultUtil doUpdateMessage(Job message) {
		logger.info("user -> {}", message);
		messageService.updateJob(message);
		return JSONResultUtil.ok();
	}
}
