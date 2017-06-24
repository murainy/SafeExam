package com.murainy.safeexam.beans;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * Created by murainy on 2015/12/13.
 */
public class Grade extends BmobObject implements Serializable {

    private String username = "";
    private String paperName = "";
    private Integer grade = 0;
    private String joinTime = "";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(String joinTime) {
        this.joinTime = joinTime;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }
}
