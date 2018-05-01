package com.murainy.safeexam.beans;

import android.os.Parcel;
import android.os.Parcelable;

import cn.bmob.v3.BmobObject;

/**
 * Created by murainy on 2015/12/11.
 */
public class Question extends BmobObject implements Parcelable {


	private String questionid = "";
	private String paperName = "";
	private String subject = "";
	private String type = "";
	private String question = "";
	private String optionA = "";
	private String optionB = "";
	private String optionC = "";
	private String optionD = "";
	private String optionE = "";
	private String optionF = "";
	private String mark = "";
	private String answer = "";
	private String note = "";
	private String year = "";
	private Number count = 0;

	protected Question(Parcel in) {
		this.questionid = in.readString();
		this.paperName = in.readString();
		this.subject = in.readString();
		this.type = in.readString();
		this.question = in.readString();
		this.optionA = in.readString();
		this.optionB = in.readString();
		this.optionC = in.readString();
		this.optionD = in.readString();
		this.optionE = in.readString();
		this.optionF = in.readString();
		this.mark = in.readString();
		this.answer = in.readString();
		this.note = in.readString();
		this.year = in.readString();
		this.count = (Number) in.readSerializable();
	}

	public String getSubject() {
		return subject;
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

	public String getPaperName() {
		return paperName;
	}

	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getOptionA() {
		return optionA;
	}

	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}

	public String getOptionB() {
		return optionB;
	}

	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}

	public String getOptionC() {
		return optionC;
	}

	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}

	public String getOptionD() {
		return optionD;
	}

	public void setOptionD(String optionD) {
		this.optionD = optionD;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getQuestionid() {
		return questionid;
	}

	public void setQuestionid(String questionid) {
		this.questionid = questionid;
	}

	public String getOptionF() {
		return optionF;
	}

	public void setOptionF(String optionF) {
		this.optionF = optionF;
	}

	public String getOptionE() {

		return optionE;
	}

	public void setOptionE(String optionE) {
		this.optionE = optionE;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}


	@Override
	public int describeContents() {
		return 0;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Question() {
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.questionid);
		dest.writeString(this.paperName);
		dest.writeString(this.subject);
		dest.writeString(this.type);
		dest.writeString(this.question);
		dest.writeString(this.optionA);
		dest.writeString(this.optionB);
		dest.writeString(this.optionC);
		dest.writeString(this.optionD);
		dest.writeString(this.optionE);
		dest.writeString(this.optionF);
		dest.writeString(this.mark);
		dest.writeString(this.answer);
		dest.writeString(this.note);
		dest.writeString(this.year);
		dest.writeSerializable(this.count);
	}

	public static final Creator<Question> CREATOR = new Creator<Question>() {
		@Override
		public Question createFromParcel(Parcel source) {
			return new Question(source);
		}

		@Override
		public Question[] newArray(int size) {
			return new Question[size];
		}
	};
}
