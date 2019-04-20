package cn.qs.dao.job;

import cn.qs.bean.common.Blogpicture;
import cn.qs.bean.job.Job;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Author: qlq
 * @Description
 * @Date: 21:44 2019/4/17
 */
public interface JobRepository extends MongoRepository<Job, String> {
}
