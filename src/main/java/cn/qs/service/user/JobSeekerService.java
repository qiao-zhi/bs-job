package cn.qs.service.user;

import cn.qs.bean.user.JobSeeker;
import cn.qs.bean.user.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface JobSeekerService {
  public void insertSeeker(JobSeeker jobSeeker);

  public Page<JobSeeker> getJobSeekersPage(Map condition);

  void deleteJobSeeker(String id);

  public JobSeeker getJobSeekerById(String id);

  public void updateSeeker(JobSeeker jobSeeker);

  public void updateSeekers(JobSeeker jobSeeker);

  /**
   * 根据username创建或者删除个人信息
   * @param username
   */
  public JobSeeker getOrCreateSeekerByUsername(String username);
}
