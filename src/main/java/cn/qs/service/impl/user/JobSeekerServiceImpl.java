package cn.qs.service.impl.user;

import cn.qs.bean.job.Job;
import cn.qs.bean.user.JobSeeker;
import cn.qs.bean.user.User;
import cn.qs.dao.user.JobSeekerRepository;
import cn.qs.service.user.JobSeekerService;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.groovy.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class JobSeekerServiceImpl implements JobSeekerService {

    @Autowired
    private JobSeekerRepository jobSeekerDao;

    @Override
    public void insertSeeker(JobSeeker jobSeeker) {
        jobSeekerDao.insert(jobSeeker);
    }

    @Override
    public Page<JobSeeker> getJobSeekersPage(Map condition) {
        String username = MapUtils.getString(condition,"username");
        JobSeeker jobSeeker = new JobSeeker();
        jobSeeker.setUsername(username);

        //构造请求参数，页号从0开始。
        int pageNum = MapUtils.getInteger(condition,"pageNum",0);
        int pageSize = MapUtils.getInteger(condition,"pageSize",0);
        Pageable pageRequest = new QPageRequest(pageNum,pageSize);

        if("admin".equals(username)){
            return jobSeekerDao.findAll(pageRequest);
        }

        Example example = Example.of(jobSeeker);
        return jobSeekerDao.findAll(example,pageRequest);
    }

    @Override
    public void deleteJobSeeker(String id) {
        jobSeekerDao.delete(id);
    }

    @Override
    public JobSeeker getJobSeekerById(String id) {
        return jobSeekerDao.findOne(id);
    }

    @Override
    public void updateSeeker(JobSeeker jobSeeker) {
        jobSeekerDao.save(jobSeeker);
    }

    @Override
    public void updateSeekers(JobSeeker jobSeeker){
        jobSeekerDao.save(jobSeeker);
    }

    @Override
    public JobSeeker getOrCreateSeekerByUsername(String username) {
        //1.查找
        JobSeeker jobSeeker = jobSeekerDao.findByUsername(username);
        if(jobSeeker == null || StringUtils.isBlank(jobSeeker.getUsername())){
            jobSeeker = new JobSeeker();
            jobSeeker.setUsername(username);
            jobSeekerDao.save(jobSeeker);
        }

        return jobSeeker;
    }
}
