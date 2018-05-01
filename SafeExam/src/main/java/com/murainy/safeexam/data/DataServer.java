package com.murainy.safeexam.data;

import com.murainy.safeexam.Utils.BmobUtils;
import com.murainy.safeexam.beans.Kemu;

import java.util.ArrayList;
import java.util.List;
public class DataServer {

	public static List<Kemu> getSampleData() {

		return new ArrayList<>(BmobUtils.kmList);
	}

}