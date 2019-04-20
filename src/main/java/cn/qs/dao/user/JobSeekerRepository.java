package cn.qs.dao.user;

import cn.qs.bean.user.JobSeeker;
import cn.qs.bean.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JobSeekerRepository extends MongoRepository<JobSeeker, String> {
    public JobSeeker findByUsername(String username);
}
