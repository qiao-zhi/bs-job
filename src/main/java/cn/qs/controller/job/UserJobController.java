package cn.qs.controller.job;

import cn.qs.bean.job.Job;
import cn.qs.bean.job.UserJob;
import cn.qs.bean.user.JobSeeker;
import cn.qs.bean.user.User;
import cn.qs.service.JobService.JobService;
import cn.qs.service.JobService.UserJobService;
import cn.qs.utils.JSONResultUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("userJob")
public class UserJobController {

	private static final Logger logger = LoggerFactory.getLogger(UserJobController.class);

	@Autowired
	private UserJobService userJobService;

	@Autowired
	private JobService jobService;

	@RequestMapping("applyJob")
	@ResponseBody
	public JSONResultUtil applyJob(String jobId, HttpServletRequest request) {
		JobSeeker seeker = (JobSeeker) request.getSession().getAttribute("seeker");
		User user = (User) request.getSession().getAttribute("user");
		if(seeker == null || StringUtils.isBlank(jobId)){
			return JSONResultUtil.error("参数错误");
		}

		UserJob byJobidAndSeekerid = userJobService.findByJobidAndSeekerid(jobId, seeker.getId());
		if(byJobidAndSeekerid != null){
			return JSONResultUtil.error("您已经投递过该公司，请勿重复投递!");
		}

		String fullname = StringUtils.isBlank(seeker.getFullname())?user.getFullname():seeker.getFullname();
		UserJob userJob = new UserJob();
		userJob.setJobid(jobId);
		userJob.setSeekerid(seeker.getId());
		userJob.setSeekerusername(seeker.getUsername());
		userJob.setSeekerfullname(fullname);
		userJob.setSeekerfilepath(seeker.getFilepath());
		userJobService.insert(userJob);

		Job jobDetail = jobService.getJobDetail(jobId);
		Integer receiverNum = jobDetail.getReceiverNum() ==null ?0:jobDetail.getReceiverNum();
		jobDetail.setReceiverNum(receiverNum+1);
		jobService.updateJob(jobDetail);

		return JSONResultUtil.ok();
	}

	@RequestMapping("getUserjobs")
	public String getUserjobs(@RequestParam Map condition, HttpServletRequest request){
		List<UserJob> userjobs = userJobService.getUserjobs(condition);
		request.setAttribute("userjobs",userjobs);

		return "userJob/userjobs";
	}

}
