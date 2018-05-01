package com.murainy.safeexam.beans;

import cn.bmob.v3.BmobObject;

/**
 * Created by Tenerify on 2016/7/24.
 */
public class Kemu extends BmobObject {
	private Number id;
	private String subject;
	private boolean finishState = false;
	private String year;
	private String note;

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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Number getId() {
		return id;
	}

	public void setId(Number id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Override
	public String toString() {
		return "Kemu{" +
				"id=" + id +
				", subject='" + subject + '\'' +
				", finishState=" + finishState +
				", year='" + year + '\'' +
				", note='" + note + '\'' +
				'}';
	}
}
