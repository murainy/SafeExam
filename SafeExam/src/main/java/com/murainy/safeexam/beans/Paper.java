package com.murainy.safeexam.beans;

import cn.bmob.v3.BmobObject;

/**
 * Created by murainy on 2015/12/14.
 */
public class Paper extends BmobObject {

    private String paperId = "";
    private String paperName = "";
    private String joinTime = "";
    private boolean finishState = false;
    private String year="";

	private String subject = "";
	private Number count = 0;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Number getCount() {
		return count;
	}

	public void setCount(Number count) {
		this.count = count;
	}

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
    public boolean isFinishState() {
        return finishState;
    }

    public void setFinishState(boolean finishState) {
        this.finishState = finishState;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public String getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(String joinTime) {
        this.joinTime = joinTime;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

	@Override
	public String toString() {
		return "Paper{" +
				"paperId='" + paperId + '\'' +
				", paperName='" + paperName + '\'' +
				", joinTime='" + joinTime + '\'' +
				", finishState=" + finishState +
				", year='" + year + '\'' +
				", subject='" + subject + '\'' +
				", count=" + count +
				'}';
	}
}
