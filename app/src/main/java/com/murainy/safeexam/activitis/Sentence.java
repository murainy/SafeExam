package com.murainy.safeexam.activitis;

import java.util.Random;

/**
 * Created by Tenerify on 2016/12/20.
 */
public class Sentence {

	public String getSentence() {
		Random rand = new Random();

		String jiang = rand.toString();
		String jj = "谢谢惠顾";
		switch (jiang.substring(5, 5)) {
			case "1":
				jj = "特等奖";
				break;
			case "9":
				jj = "一等奖";
				break;
			case "7":
				jj = "二等奖";
				break;
			case "0":
				jj = "三等奖";
				break;
		}
		return jj;
	}
}
