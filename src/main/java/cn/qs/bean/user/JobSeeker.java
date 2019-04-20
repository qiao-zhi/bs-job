package cn.qs.bean.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * 求职者信息
 */
public class JobSeeker implements Serializable {
    // id
    //唯一主键
    @Id
    @Field("id")
    private String id;

    // 用户名
    @Field("username")
    private String username;

    //用户性别
    @Field("sex")
    private String sex;

    // 用户姓名
    @Field("fullname")
    private String fullname;

    // 用户邮箱
    @Field("email")
    private String email;

    // 用户住址
    @Field("address")
    private String address;

    // 简历文件路径
    @Field("filepath")
    private String filepath;

    // 毕业院校
    @Field("school")
    private String school;

    //目标岗位
    @Field("targetjob")
    private String targetjob;

    // 工作经验
    @Field("workexperience")
    private String workexperience;

    // 项目简介
    @Field("projectintruction")
    private String projectintruction;

    // 自我评价
    @Field("evaluation")
    private String evaluation;

    // 手机号
    @Field("phone")
    private String phone;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getWorkexperience() {
        return workexperience;
    }

    public void setWorkexperience(String workexperience) {
        this.workexperience = workexperience;
    }

    public String getProjectintruction() {
        return projectintruction;
    }

    public void setProjectintruction(String projectintruction) {
        this.projectintruction = projectintruction;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTargetjob() {
        return targetjob;
    }

    public void setTargetjob(String targetjob) {
        this.targetjob = targetjob;
    }
}