package cn.qs.service.JobService;

import cn.qs.bean.job.Job;
import cn.qs.bean.job.UserJob;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface UserJobService {
	UserJob findByJobidAndSeekerid(String jobid,String seekerid);

	void insert(UserJob userJob);

	UserJob getDetail(String userJobId);

	void delete(String userJobId);

	List<UserJob> getUserjobs(Map condition);
}
