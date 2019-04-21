package cn.qs.service.impl.Job;

import cn.qs.bean.job.Job;
import cn.qs.dao.job.JobRepository;
import cn.qs.service.JobService.JobService;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository messageRepository;

    @Override
    public void insert(Job message) {
        messageRepository.insert(message);
    }

    @Override
    public Job getJobDetail(String blogId) {
        return messageRepository.findOne(blogId);
    }

    @Override
    public void deleteJob(String id) {
        messageRepository.delete(id);
    }

    @Override
    public void updateJob(Job message) {
        messageRepository.save(message);
    }

    @Override
    public Page<Job> getMessageServicePage(Map condition) {
        Job job = new Job();
        if (StringUtils.isNotBlank(MapUtils.getString(condition, "username"))) {
            job.setUsername(MapUtils.getString(condition, "username"));
        }
        if (StringUtils.isNotBlank(MapUtils.getString(condition, "fullname"))) {
            job.setFullname(MapUtils.getString(condition, "fullname"));
        }
        if (StringUtils.isNotBlank(MapUtils.getString(condition, "jobname"))) {
            job.setJobname(MapUtils.getString(condition, "jobname"));
        }

        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("username", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("fullname", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("jobname", ExampleMatcher.GenericPropertyMatchers.contains());
        Example example = Example.of(job, matcher);

        int pageNum = MapUtils.getInteger(condition, "pageNum", 0);
        int pageSize = MapUtils.getInteger(condition, "pageSize", 0);
        Pageable pageRequest = new QPageRequest(pageNum, pageSize);

        return messageRepository.findAll(example, pageRequest);
    }
}
