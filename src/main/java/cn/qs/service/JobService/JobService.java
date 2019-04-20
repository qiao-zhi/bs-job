package cn.qs.service.JobService;

import cn.qs.bean.common.Message;
import cn.qs.bean.job.Job;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface JobService {
	void insert(Job job);

	Job getJobDetail(String jobId);

	void deleteJob(String jobId);

	void updateJob(Job job);

	Page<Job> getMessageServicePage(Map condition);
}
