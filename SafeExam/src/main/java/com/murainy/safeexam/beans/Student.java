package com.murainy.safeexam.beans;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by murainy on 2015/12/11.
 */
public class Student extends BmobUser {

    private String name = "";
    private String mClass = "";
    private Integer identity = 1;
    private String nick = "";
    private String headurl = "";
    private BmobFile headpng;
// 方法

    public BmobFile getHeadpng() {
        return headpng;
    }

    public void setHeadpng(BmobFile headpng) {
        this.headpng = headpng;
    }

    public String getHeadurl() {
        return headurl;
    }

    public void setHeadurl(String headurl) {
        this.headurl = headurl;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setIdentity(Integer identity) {
        this.identity = identity;
    }

    public Integer getIdentity() {
        return identity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getmClass() {
        return mClass;
    }

    public void setmClass(String mClass) {
        this.mClass = mClass;
    }

}
