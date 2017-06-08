package com.murainy.safeexam.beans;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tenerify on 2017/1/12.
 */

public class Newslist {

	/**
	 * code : 200
	 * msg : success
	 * newslist : [{"ctime":"2016-03-31","title":"奇虎360宣布通过私有化决议","description":"互联网头条","picUrl":"http://t1.qpic.cn/mblogpic/f01a972dbcc1060fd456/2000","url":"http://mp.weixin.qq.com/s?__biz=MjM5OTMyODA2MA==&idx=1&mid=402594468&sn=5cd644536b472a283cc1d3f5124a0cab"},{"ctime":"2016-03-31","title":"小本生意做什么挣钱十七大小本生意推荐","description":"创业最前线","picUrl":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-4225297.jpg/640","url":"http://mp.weixin.qq.com/s?__biz=MzA3NjgzNDUwMQ==&idx=2&mid=401864059&sn=cfa082e38ba38c7e673b1ce0a075faee"},{"ctime":"2016-03-31","title":"当穷游从线上社区向线下旅游服务提供者转身，创业不如就忘了\u201c大平台\u201d这件事吧","description":"中国旅讯","picUrl":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-4218551.jpg/640","url":"http://mp.weixin.qq.com/s?__biz=MzA5MzQyMzQyMw==&idx=3&mid=402488438&sn=88f2d7e415e6d18dafccaa8da549edb6"},{"ctime":"2016-03-31","title":"等我们老了，就去经营这样一家小店","description":"慢时间","picUrl":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-4225311.jpg/640","url":"http://mp.weixin.qq.com/s?__biz=MjM5NTIxNTU0MA==&idx=1&mid=405728106&sn=b8ac94b83aa92eea54acf75eff89c657"},{"ctime":"2016-03-31","title":"曝光失业女子利用微信月均收入过万，原因居然是这个？","description":"那些路过心上的句子","picUrl":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-4116605.jpg/640","url":"http://mp.weixin.qq.com/s?__biz=MjM5MTg3OTAzNg==&idx=1&mid=402804635&sn=172aa8f7bf0f8078ade644f46722c561"},{"ctime":"2016-03-31","title":"什麼人生活在一個單向的世界？","description":"今天文学","picUrl":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-4082011.jpg/640","url":"http://mp.weixin.qq.com/s?__biz=MjM5ODQxODMyMQ==&idx=1&mid=420440897&sn=cb4b009440b6a9d954bff2a622344e8d"},{"ctime":"2016-03-31","title":"这些事，足够让我们相信爱情","description":"女生心理学","picUrl":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-4225315.jpg/640","url":"http://mp.weixin.qq.com/s?__biz=MzA3NjM5NDIyMw==&idx=1&mid=401870610&sn=1baf92ad1bb1b9f575027473754551f9"},{"ctime":"2016-03-31","title":"少女们，为什么你们总爱翻白眼？","description":"青年文摘","picUrl":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-4225320.jpg/640","url":"http://mp.weixin.qq.com/s?__biz=MzA5ODEzMTIxOA==&idx=4&mid=405169046&sn=8ea51ec94fcd5d9bed6d1df0d0bf889f"},{"ctime":"2016-03-31","title":"总说韩国艺人来中国捞金，先看完这些再说吧","description":"RunningMan","picUrl":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-4225344.jpg/640","url":"http://mp.weixin.qq.com/s?__biz=MzA3NDcwNzUxMw==&idx=4&mid=403101020&sn=5a9fa497df693b768637faf85f2b6449"},{"ctime":"2016-03-31","title":"宋仲基宋慧乔频传绯闻宋仲基蛮享受的","description":"韩国新网","picUrl":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-4225352.jpg/640","url":"http://mp.weixin.qq.com/s?__biz=MjM5MDAzNjYwMA==&idx=4&mid=403617618&sn=3446bb057607d2daf2e3123f837fa24d"}]
	 */

	@SerializedName("code")
	private int code;
	@SerializedName("msg")
	private String msg;
	@SerializedName("newslist")
	private List<NewslistBean> newslist;

	public static Newslist objectFromData(String str) {

		return new Gson().fromJson(str, Newslist.class);
	}

	public static Newslist objectFromData(String str, String key) {

		try {
			JSONObject jsonObject = new JSONObject(str);

			return new Gson().fromJson(jsonObject.getString(str), Newslist.class);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static List<Newslist> arrayNewslistFromData(String str) {

		Type listType = new TypeToken<ArrayList<Newslist>>() {
		}.getType();

		return new Gson().fromJson(str, listType);
	}

	public static List<Newslist> arrayNewslistFromData(String str, String key) {

		try {
			JSONObject jsonObject = new JSONObject(str);
			Type listType = new TypeToken<ArrayList<Newslist>>() {
			}.getType();

			return new Gson().fromJson(jsonObject.getString(str), listType);

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return new ArrayList();


	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<NewslistBean> getNewslist() {
		return newslist;
	}

	public void setNewslist(List<NewslistBean> newslist) {
		this.newslist = newslist;
	}

	public static class NewslistBean {
		/**
		 * ctime : 2016-03-31
		 * title : 奇虎360宣布通过私有化决议
		 * description : 互联网头条
		 * picUrl : http://t1.qpic.cn/mblogpic/f01a972dbcc1060fd456/2000
		 * url : http://mp.weixin.qq.com/s?__biz=MjM5OTMyODA2MA==&idx=1&mid=402594468&sn=5cd644536b472a283cc1d3f5124a0cab
		 */

		@SerializedName("ctime")
		private String ctime;
		@SerializedName("title")
		private String title;
		@SerializedName("description")
		private String description;
		@SerializedName("picUrl")
		private String picUrl;
		@SerializedName("url")
		private String url;

		public static NewslistBean objectFromData(String str) {

			return new Gson().fromJson(str, NewslistBean.class);
		}

		public static NewslistBean objectFromData(String str, String key) {

			try {
				JSONObject jsonObject = new JSONObject(str);

				return new Gson().fromJson(jsonObject.getString(str), NewslistBean.class);
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		public static List<NewslistBean> arrayNewslistBeanFromData(String str) {

			Type listType = new TypeToken<ArrayList<NewslistBean>>() {
			}.getType();

			return new Gson().fromJson(str, listType);
		}

		public static List<NewslistBean> arrayNewslistBeanFromData(String str, String key) {

			try {
				JSONObject jsonObject = new JSONObject(str);
				Type listType = new TypeToken<ArrayList<NewslistBean>>() {
				}.getType();

				return new Gson().fromJson(jsonObject.getString(str), listType);

			} catch (JSONException e) {
				e.printStackTrace();
			}

			return new ArrayList();


		}

		public String getCtime() {
			return ctime;
		}

		public void setCtime(String ctime) {
			this.ctime = ctime;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getPicUrl() {
			return picUrl;
		}

		public void setPicUrl(String picUrl) {
			this.picUrl = picUrl;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}
	}
}
