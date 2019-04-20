package cn.qs.service.impl.Job;

import cn.qs.bean.job.Job;
import cn.qs.bean.job.UserJob;
import cn.qs.dao.job.JobRepository;
import cn.qs.dao.job.UserJobRepository;
import cn.qs.dao.user.UserRepository;
import cn.qs.service.JobService.JobService;
import cn.qs.service.JobService.UserJobService;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserJobServiceImpl implements UserJobService {

	@Autowired
	private UserJobRepository userJobRepository;

	@Override
	public UserJob findByJobidAndSeekerid(String jobid,String seekerid){
		return userJobRepository.findByJobidAndSeekerid(jobid,seekerid);
	}

	@Override
	public void insert(UserJob userJob) {
		userJobRepository.insert(userJob);
	}

	@Override
	public UserJob getDetail(String userJobId) {
		return userJobRepository.findOne(userJobId);
	}

	@Override
	public void delete(String userJobId) {
		userJobRepository.delete(userJobId);
	}

	@Override
	public List<UserJob> getUserjobs(Map condition) {
		//构造请求参数，页号从0开始。
		String jobId = MapUtils.getString(condition,"jobId");
		if(StringUtils.isBlank(jobId)){
			return null;
		}

		UserJob userJob = new UserJob();
		userJob.setJobid(jobId);
		Example example = Example.of(userJob);

		return userJobRepository.findAll(example);
	}
}
