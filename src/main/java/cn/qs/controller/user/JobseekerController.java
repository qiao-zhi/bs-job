package cn.qs.controller.user;

import cn.qs.bean.user.JobSeeker;
import cn.qs.bean.user.User;
import cn.qs.service.user.JobSeekerService;
import cn.qs.utils.*;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

@Controller
/** 自动返回的是json格式数据 ***/
@RequestMapping("jobseeker")
public class JobseekerController {
    private static final Logger log = LoggerFactory.getLogger(JobseekerController.class);

    @Autowired
    private JobSeekerService jobSeekerService;

    private String username;

    @RequestMapping("jobseeker-info")
    public String jobseeker_info() {
        return "jobseeker/jobseeker-info";
    }

    @RequestMapping("job-add")
    public String job_add() {
        return "jobseeker/job-add";
    }

    @RequestMapping("addSeeker")
    @ResponseBody
    public JSONResultUtil addJobSeeker(JobSeeker seeker) {
       jobSeekerService.insertSeeker(seeker);
        return JSONResultUtil.ok();
    }

    @RequestMapping("getJobseekers")
    @ResponseBody
    public Page<JobSeeker> getJobseekers(@RequestParam Map condition,HttpServletRequest request) {
        int pageNum = 1;
        if (ValidateCheck.isNotNull(MapUtils.getString(condition, "pageNum"))) { // 如果不为空的话改变当前页号
            pageNum = MapUtils.getInteger(condition, "pageNum");
        }
        int pageSize = DefaultValue.PAGE_SIZE;
        if (ValidateCheck.isNotNull(MapUtils.getString(condition, "pageSize"))) { // 如果不为空的话改变当前页大小
            pageSize = MapUtils.getInteger(condition, "pageSize");
        }

        condition.put("pageNum",pageNum-1);
        condition.put("pageSize",pageSize);
        condition.put("username",((User) request.getSession().getAttribute("user")).getUsername());

        Page<JobSeeker> users = null;
        // 开始分页
        try {
            users = jobSeekerService.getJobSeekersPage(condition);
        } catch (Exception e) {
            log.error("getUsers error！", e);
        }

        return users;
    }

    @RequestMapping("deleteJobSeeker")
    @ResponseBody
    public JSONResultUtil deleteJobSeeker(String id) {
        jobSeekerService.deleteJobSeeker(id);
        return JSONResultUtil.ok();
    }

    @RequestMapping("/showFile")
    public String showPicture(String seekerId, ModelMap map,HttpServletRequest request) {
        if(StringUtils.isBlank(seekerId)){
            seekerId = ((JobSeeker) request.getSession().getAttribute("seeker")).getId();
        }

        JobSeeker seeker = jobSeekerService.getJobSeekerById(seekerId);
        map.put("seeker", seeker);

        return "jobseeker/showFile";
    }

    @RequestMapping("/uploadSeekerFile")
    @ResponseBody
    public JSONResultUtil uploadPicture(MultipartFile file, String  seekerId,HttpServletRequest request) {
        if (file == null) {
            return JSONResultUtil.error("文件没接到");
        }
        log.debug("file -> {},viewId ->{}", file.getOriginalFilename(), seekerId);

        String fileOriName = file.getOriginalFilename();// 获取原名称
        String fileNowName = UUIDUtil.getUUID2() + "." + FilenameUtils.getExtension(fileOriName);// 生成唯一的名字
        try {
            FileHandleUtil.uploadSpringMVCFile(file, fileNowName);

//            Picture picture = new Picture();
//            picture.setCreatetime(new Date());
//            picture.setName(fileOriName);
//            picture.setPath(fileNowName);
//            picture.setViewId(viewId);
//            pictureService.addPicture(picture);
            JobSeeker seeker = jobSeekerService.getJobSeekerById(seekerId);
            seeker.setFilepath(fileNowName);
            jobSeekerService.updateSeeker(seeker);

            updateSessionSeeker(request,seeker);
        } catch (Exception e) {
            log.error("uploadPicture error", e);
            return JSONResultUtil.error("添加景点图片出错");
        }

        return JSONResultUtil.ok();
    }

    @RequestMapping("/getFile")
    public void getPicture(HttpServletRequest request, HttpServletResponse response, String path) {
        if(StringUtils.isBlank(path)){
            path = ((JobSeeker) request.getSession().getAttribute("seeker")).getFilepath();
        }

        FileInputStream in = null;
        ServletOutputStream outputStream = null;
        try {
            File fileByName = FileHandleUtil.getFileByName(path);
            in = new FileInputStream(fileByName);
            outputStream = response.getOutputStream();
            IOUtils.copyLarge(in, outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(outputStream);
        }
    }

    /**
     * 跳转到修改jobSeeker页面
     *
     * @return
     */
    @RequestMapping("updateJobSeeker")
    public String updateJobSeeker( ModelMap map,HttpServletRequest request) {
        String id = ((JobSeeker) request.getSession().getAttribute("seeker")).getId();
        JobSeeker seeker = jobSeekerService.getJobSeekerById(id);
        seeker.setFullname(((User) request.getSession().getAttribute("user")).getFullname());
        map.addAttribute("seeker", seeker);

        return "jobseeker/updateJobSeeker";
    }

    @RequestMapping("doUpdateJobSeeker")
    @ResponseBody
    public JSONResultUtil doUpdateTicket(JobSeeker jobSeeker, HttpServletRequest request) {
        log.info("jobSeeker -> {}", jobSeeker);

        jobSeekerService.updateSeekers(jobSeeker);
        updateSessionSeeker(request,jobSeeker);
        return JSONResultUtil.ok();
    }

    @RequestMapping("jobSeekerDetail")
    public String jobSeekerDetail( ModelMap map,String seekerId ) {
        JobSeeker seeker = jobSeekerService.getJobSeekerById(seekerId);
        map.addAttribute("seeker", seeker);

        return "jobseeker/jobSeekerDetail";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private void updateSessionSeeker(HttpServletRequest request,JobSeeker seeker){
        request.getSession().setAttribute("seeker",seeker);
    }
}
