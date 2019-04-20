package cn.qs.bean.common;

import org.springframework.data.annotation.Id;

import java.util.Date;
public class Message {
    @Id
    private String id;

    private String name;

    private Date createtime;

    private Date updatetime;

    private String messageblank;

    private String content;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getMessageblank() {
        return messageblank;
    }

    public void setMessageblank(String messageblank) {
        this.messageblank = messageblank == null ? null : messageblank.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}