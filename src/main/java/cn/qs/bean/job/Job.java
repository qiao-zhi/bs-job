package cn.qs.bean.job;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * @Author: qlq
 * @Description
 * @Date: 22:16 2019/4/17
 */
public class Job {
    @Id
    private String id;
    private String username; //所属公司账号
    private String fullname;//所属工程名称

    private String jobname;//岗位名称
    private String description;//岗位描述
    private Integer  needNumber;//需要人数
    private String contactUserFullname;//联系人
    private String contactUserPhone;//联系人电话
    private Date createtime;//发布时间
    private Integer receiverNum;//投递次数

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getJobname() {
        return jobname;
    }

    public void setJobname(String jobname) {
        this.jobname = jobname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNeedNumber() {
        return needNumber;
    }

    public void setNeedNumber(Integer needNumber) {
        this.needNumber = needNumber;
    }

    public String getContactUserFullname() {
        return contactUserFullname;
    }

    public void setContactUserFullname(String contactUserFullname) {
        this.contactUserFullname = contactUserFullname;
    }

    public String getContactUserPhone() {
        return contactUserPhone;
    }

    public void setContactUserPhone(String contactUserPhone) {
        this.contactUserPhone = contactUserPhone;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getReceiverNum() {
        return receiverNum;
    }

    public void setReceiverNum(Integer receiverNum) {
        this.receiverNum = receiverNum;
    }
}
