package cn.qs.dao.job;

import cn.qs.bean.job.Job;
import cn.qs.bean.job.UserJob;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Author: qlq
 * @Description
 * @Date: 21:44 2019/4/17
 */
public interface UserJobRepository extends MongoRepository<UserJob, String> {
    UserJob findByJobidAndSeekerid(String jobid,String seekerid);
}
