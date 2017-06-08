package com.murainy.safeexam.beans;

import cn.bmob.v3.BmobObject;

/**
 * Created by Tenerify on 2016/7/24.
 */
public class Testqeba extends BmobObject {
	private Number id;
	private String name;
	private boolean finishState = false;
	private String note;

	public boolean isFinishState() {
		return finishState;
	}

	public void setFinishState(boolean finishState) {
		this.finishState = finishState;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
}
