package cn.qs.bean.job;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * 求职者与工作对应关系
 */
public class UserJob {
    @Id
    private String id;

    private String jobid;//工作的ID

    private String seekerid;//求职者ID
    private String seekerusername;//求职者username
    private String seekerfullname;//求职者全名
    private String seekerfilepath;//求职者简历路径

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJobid() {
        return jobid;
    }

    public void setJobid(String jobid) {
        this.jobid = jobid;
    }

    public String getSeekerid() {
        return seekerid;
    }

    public void setSeekerid(String seekerid) {
        this.seekerid = seekerid;
    }

    public String getSeekerusername() {
        return seekerusername;
    }

    public void setSeekerusername(String seekerusername) {
        this.seekerusername = seekerusername;
    }

    public String getSeekerfullname() {
        return seekerfullname;
    }

    public void setSeekerfullname(String seekerfullname) {
        this.seekerfullname = seekerfullname;
    }

    public String getSeekerfilepath() {
        return seekerfilepath;
    }

    public void setSeekerfilepath(String seekerfilepath) {
        this.seekerfilepath = seekerfilepath;
    }
}
