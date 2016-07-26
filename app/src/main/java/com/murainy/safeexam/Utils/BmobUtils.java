package com.murainy.safeexam.Utils;

import android.content.Context;
import android.util.Log;

import com.murainy.safeexam.beans.Grade;
import com.murainy.safeexam.beans.Paper;
import com.murainy.safeexam.beans.Question;
import com.murainy.safeexam.beans.Testqeba;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Set;

import cn.bmob.v3.BmobBatch;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BatchResult;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListListener;
import cn.bmob.v3.listener.SQLQueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by murainy on 2015/12/29.
 */
public class BmobUtils {
	public static List<String> paperSet = new ArrayList<String>();
	public static List<Grade> gradeList = new ArrayList<Grade>();
	public static List<Paper> paperList = new ArrayList<Paper>();
	public static List<Paper> LP = new ArrayList<>();
	public static List<Testqeba> LT = new ArrayList<>();
	public static List<Question> questionsList = new ArrayList<Question>();
	public static List<Question> qthList = new ArrayList<Question>();
	public static boolean ready = false;

	public static void downloadGradeList(Context context, String username) {
		BmobQuery<Grade> query = new BmobQuery<Grade>();
		query.addWhereEqualTo("username", username);
		query.setLimit(100);
		query.findObjects(new FindListener<Grade>() {
			@Override
			public void done(List<Grade> object, BmobException e) {
				if (e == null) {
					gradeList = new ArrayList<Grade>();
					for (Grade grade : object) {
						gradeList.add(grade);
					}
					EventBus.getDefault().post(Action.DOWNLOAD_GRADE_LIST);
				} else {
					Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
				}
			}
		});

	}


	public static void downloadPaperList(Context context) {

		BmobQuery<Paper> query = new BmobQuery<Paper>();
		query.setLimit(100);
		query.findObjects(new FindListener<Paper>() {
			@Override
			public void done(List<Paper> object, BmobException e) {
				if (e == null) {
					Logger.i(object.size() + "///");
					paperList = new ArrayList<Paper>();
					for (int i = 0; i < object.size(); i++) {
						paperList.add(object.get(i));
					}
					Logger.i(paperList.size() + "///");
					EventBus.getDefault().post(Action.DOWNLOAD_PAPER_LIST);
				} else {
					Logger.i("查询失败");
					EventBus.getDefault().post(Action.QUERY_ERROR);
				}
			}
		});
	}

	public static void downloadTPaperList(Context context) {

		BmobQuery<Paper> query = new BmobQuery<Paper>();
		query.findObjects(new FindListener<Paper>() {
			@Override
			public void done(List<Paper> object, BmobException e) {
				if (e == null) {
					paperList = new ArrayList<Paper>();
					Logger.i(object.size() + "///");
					for (int i = 0; i < object.size(); i++) {
						paperList.add(object.get(i));
					}
					Logger.i(paperList.size() + "///");
					EventBus.getDefault().post(Action.DOWNLOAD_PAPER_LIST);
				} else {
					Logger.i("查询失败");
					EventBus.getDefault().post(Action.QUERY_ERROR);
				}
			}
		});
	}

	public static void downloadQuestionList(List<String> papers) {

		BmobQuery<Question> query = new BmobQuery<Question>();
		query.addWhereContainedIn("questionid", papers);
		query.setLimit(100);
		//判断是否有缓存，该方法必须放在查询条件（如果有的话）都设置完之后再来调用才有效，就像这里一样。
		boolean isCache = query.hasCachedResult(Question.class);
		if (isCache) {//此为举个例子，并不一定按这种方式来设置缓存策略
			query.setCachePolicy(BmobQuery.CachePolicy.CACHE_ELSE_NETWORK);    // 如果有缓存的话，则设置策略为CACHE_ELSE_NETWORK
		} else {
			query.setCachePolicy(BmobQuery.CachePolicy.NETWORK_ELSE_CACHE);    // 如果没有缓存的话，则设置策略为NETWORK_ELSE_CACHE
		}
		//执行查询方法
		query.findObjects(new FindListener<Question>() {
			@Override
			public void done(List<Question> object, BmobException e) {
				if (e == null) {
					questionsList = new ArrayList<Question>();
					Logger.i(object.size() + "````");
					for (Question question : object) {
						questionsList.add(question);
					}
					Logger.i(questionsList.size() + "````");
					EventBus.getDefault().post(Action.DOWNLOAD_QUESTION_LIST);
				} else {
					Logger.i("查询失败");
					EventBus.getDefault().post(Action.QUERY_ERROR);
				}
			}

		});
	}

	public static void downloadQuestionList100(Context context, final List<Question> tl1, final List<Question> tl2, final List<Question> tl3) {

		BmobQuery<Question> query1 = new BmobQuery<Question>();
		query1.findObjects(new FindListener<Question>() {
			@Override
			public void done(List<Question> object, BmobException e) {
				if (e == null) {
					Log.i("我选了" + questionsList.size() + "道题", "");
					EventBus.getDefault().post(Action.DOWNLOAD_QUESTION_LIST100);
				} else {
					Logger.i("查询失败");
					EventBus.getDefault().post(Action.QUERY_ERROR);
				}
			}
		});
	}

	public static void downloadQuestionListMy(String paperName) {

		BmobQuery<Question> query = new BmobQuery<Question>();
		query.addWhereEqualTo("paperName", paperName);
		query.addWhereEqualTo("mark", "1");
		query.setLimit(100);
		//执行查询方法

		query.findObjects(new FindListener<Question>() {
			@Override
			public void done(List<Question> object, BmobException e) {
				if (e == null) {
					questionsList = new ArrayList<Question>();
					Logger.i(object.size() + "````");
					for (Question question : object) {
						questionsList.add(question);
					}
					Logger.i(questionsList.size() + "````");
					EventBus.getDefault().post(Action.DOWNLOAD_QUESTION_LISTMy);
				} else {
					Logger.i("查询失败");
					EventBus.getDefault().post(Action.QUERY_ERROR);
				}
			}
		});
	}


	public static void saveNewPaper(Context context, Paper paper, List<Question> questions) {

		for (int i = 0; i < questions.size(); i++) {
			Question q = questions.get(i);
			q.save(new SaveListener<String>() {
				@Override
				public void done(String objectId, BmobException e) {
					if (e == null) {
						Logger.i("成功");
					} else {
						Logger.i("失败" + e);
					}
				}
			});
		}

		paper.save(new SaveListener<String>() {

			@Override
			public void done(String objectId, BmobException e) {
				if (e == null) {
					Logger.i("试卷成功");
					EventBus.getDefault().post(Action.SAVE_PAPER_SUCCESS);
				} else {
					Logger.i("试卷失败");
					EventBus.getDefault().post(Action.SAVE_PAPER_ERROR);
				}
			}

		});
	}

	public static void updatepaper(List<Paper> paper) {
		for (int i = 0; i < paper.size(); i++) {
			Paper q = paper.get(i);
			q.save(new SaveListener<String>() {
				@Override
				public void done(String objectId, BmobException e) {
					if (e == null) {
						Logger.i("标记成功");
					} else {
						Logger.i("标记失败" + e);
					}
				}
			});
		}
	}

	public static void updatemark(List<Question> questions) {
		for (int i = 0; i < questions.size(); i++) {
			Question q = questions.get(i);
			q.setMark("1");
			q.update(new UpdateListener() {
				@Override
				public void done(BmobException e) {
					if (e == null) {
						Logger.i("标记成功");
					} else {
						Logger.i("标记失败" + e);
					}
				}
			});

		}
	}


	public static void saveGrade(Context context, Grade grade) {
		grade.save(new SaveListener<String>() {

			@Override
			public void done(String objectId, BmobException e) {
				if (e == null) {
					Logger.i("成绩成功");
				} else {
					Logger.i("成绩失败" + e);
				}
			}
		});
	}


	public static void questionCount(Context context, String paperName, String type, final int qs) {
		BmobQuery<Question> query = new BmobQuery<Question>();
		query.addWhereEqualTo("paperName", paperName);
		query.addWhereEqualTo("type", type);
		//注意：这里的Question对象中只有指定列的数据。
		query.setLimit(500);
		query.findObjects(new FindListener<Question>() {
			@Override
			public void done(List<Question> object, BmobException e) {
				if (e == null) {
					Logger.i("对象查询成功：共" + object.size() + "条数据。", "");
					int size = object.size();
					int[] numbers = new int[qs];
					qthList = new ArrayList<Question>();
					for (int i = 0; i < qs; i++) {
						numbers[i] = (int) (size * Math.random());
						for (int j = 1; j < i; j++) {
							while (numbers[i] == numbers[j]) {//如果重复，退回去重新生成随机数
								i--;
							}
						}
					}

					for (int i = 0; i < qs; i++) {
						qthList.add(object.get(numbers[i]));
						questionsList.add(object.get(numbers[i]));
					}

					Logger.i("计数器查询成功：共" + qthList.size() + "条数据。");
					Logger.i("数据样品：" + questionsList.size());
					EventBus.getDefault().post(Action.QUESTIONCOUNT);
				} else {
					Logger.e("查询失败：" + e);
				}
			}

		});

	}

	/**
	 * 判断某个字符串是否存在于数组中*
	 *
	 * @param stringArray 原数组
	 * @param source      查找的字符串
	 * @return 是否找到
	 */
	public static boolean contains(String[] stringArray, String source) {
		// 转换为list
		List<String> tempList = Arrays.asList(stringArray);

		// 利用list的包含方法,进行判断
		return tempList.contains(source);
	}

	//产生随机数，num是个数，max是最大允许的范围
	public static int[] randRange(int num, int max) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		Random rand = new Random();
		while (true) {
			int rm = rand.nextInt(max);
			if (!list.contains(rm)) {
				list.add(rm);
				if (list.size() >= num) break;
			}
		}
		int result[] = new int[num];
		for (int i = 0; i < list.size(); i++) {
			result[i] = list.get(i);
		}
		Logger.e("随机:" + String.valueOf(result[0]));
		return result;

	}

	public static void examListini(String y, String t, final int c) {
		String bql = "select questionid from Question where year = ? and type = ?";
		BmobQuery<Question> query = new BmobQuery<Question>();
		paperSet.clear();
		query.setSQL(bql);
		//设置占位符参数
		query.setPreparedParams(new Object[]{y, t});
		query.doSQLQuery(new SQLQueryListener<Question>() {

			@Override
			public void done(BmobQueryResult<Question> result, BmobException e) {
				if (e == null) {
					List<Question> list = (List<Question>) result.getResults();
					int count = list.size();
					Logger.e("条件总数：" + String.valueOf(count));
					if (list != null && count > 0) {
						if (count < c) {
							for (int i = 0; i < count; i++) {
								paperSet.add(list.get(i).getQuestionid());
							}
							downloadQuestionList(paperSet);
						} else {
							int[] tihao = randRange(c, count);
							for (int i = 0; i < c; i++) {
								paperSet.add(list.get(tihao[i]).getQuestionid());
							}
							Logger.e("第一题目：" + paperSet);

							downloadQuestionList(paperSet);
						}
					} else {
						Log.i("smile", "查询成功，无数据返回");
					}
				} else {
					Log.i("smile", "错误码：" + e.getErrorCode() + "，错误描述：" + e.getMessage());
				}
			}
		});

	}

	public static void examLists(String y) {
		questionsList.clear();
		examListini(y, "判断题", 40);
		examListini(y, "单选题", 40);
		examListini(y, "多选题", 20);
		ready = true;
	}

	public static void testBanks() {
		String bql = "select distinct year  from Question ";
		new BmobQuery<Question>().doSQLQuery(bql, new SQLQueryListener<Question>() {

			@Override
			public void done(BmobQueryResult<Question> result, BmobException e) {
				if (e == null) {
					List<Question> list = (List<Question>) result.getResults();

					if (list != null && list.size() > 0) {
						for (int i = 0; i < list.size(); i++) {
							Testqeba t = new Testqeba();
							t.setId(i);
							t.setName(list.get(i).getYear());
							t.setNote("安全套");
							LT.add(i, t);
						}
						Logger.e(LT.toString());
						List<BmobObject> bank = new ArrayList<>();
						for (int i = 0; i < BmobUtils.LT.size(); i++) {
							bank.add(i, BmobUtils.LT.get(i));
						}
						Logger.e(bank.toString());
						new BmobBatch().insertBatch(bank).doBatch(new QueryListListener<BatchResult>() {

							@Override
							public void done(List<BatchResult> o, BmobException e) {
								if (e == null) {
									for (int i = 0; i < o.size(); i++) {
										BatchResult result = o.get(i);
										BmobException ex = result.getError();
										if (ex == null) {
											Logger.e("第" + i + "个数据批量更新成功：" + result.getUpdatedAt());
										} else {
											Logger.e("第" + i + "个数据批量更新失败：" + ex.getMessage() + "," + ex.getErrorCode());
										}
									}
								} else {
									Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
								}
							}
						});
					} else {
						Log.i("smile", "查询成功，无数据返回");
					}
				} else {
					Log.i("smile", "错误码：" + e.getErrorCode() + "，错误描述：" + e.getMessage());
				}
			}
		});
	}




	public static void papers() {
		String bql = "select distinct paperName,year from Question order by year";
		new BmobQuery<Question>().doSQLQuery(bql, new SQLQueryListener<Question>() {

			@Override
			public void done(BmobQueryResult<Question> result, BmobException e) {
				if (e == null) {
					List<Question> list = (List<Question>) result.getResults();

					if (list != null && list.size() > 0) {
						for (int i = 0; i < list.size(); i++) {
							Paper p = new Paper();
							p.setPaperName(list.get(i).getPaperName());
							p.setPaperId(String.valueOf(i));
							LP.add(i, p);
						}
						Logger.e(LP.toString());
						List<BmobObject> paper = new ArrayList<BmobObject>();
						for (int i = 0; i < BmobUtils.LP.size(); i++) {
							paper.add(i, BmobUtils.LP.get(i));
						}
						Logger.e(paper.toString());
						//第二种方式：v3.5.0开始提供
						new BmobBatch().insertBatch(paper).doBatch(new QueryListListener<BatchResult>() {

							@Override
							public void done(List<BatchResult> o, BmobException e) {
								if (e == null) {
									for (int i = 0; i < o.size(); i++) {
										BatchResult result = o.get(i);
										BmobException ex = result.getError();
										if (ex == null) {
											Logger.e("第" + i + "个数据批量更新成功：" + result.getUpdatedAt());
										} else {
											Logger.e("第" + i + "个数据批量更新失败：" + ex.getMessage() + "," + ex.getErrorCode());
										}
									}
								} else {
									Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
								}
							}
						});
					} else {
						Log.i("smile", "查询成功，无数据返回");
					}
				} else {
					Log.i("smile", "错误码：" + e.getErrorCode() + "，错误描述：" + e.getMessage());
				}
			}
		});
	}



	public static void deletPaper() {
		//批量删除
		BmobBatch batch = new BmobBatch();
		List<BmobObject> paper = new ArrayList<BmobObject>();

		for (int i = 0; i < paperList.size(); i++) {
			paper.add(i, paperList.get(i));
		}
		batch.deleteBatch(paper);
		//执行批量操作
		batch.doBatch(new QueryListListener<BatchResult>() {

			@Override
			public void done(List<BatchResult> results, BmobException ex) {
				if (ex == null) {
					//返回结果的results和上面提交的顺序是一样的，请一一对应
					for (int i = 0; i < results.size(); i++) {
						BatchResult result = results.get(i);
						if (result.isSuccess()) {//只有批量添加才返回objectId
							Logger.e("第" + i + "个成功：" + result.getObjectId() + "," + result.getUpdatedAt());
						} else {
							BmobException error = result.getError();
							Logger.e("第" + i + "个失败：" + error.getErrorCode() + "," + error.getMessage());
						}
					}
				} else {
					Logger.e("bmob", "失败：" + ex.getMessage() + "," + ex.getErrorCode());
				}
			}
		});

	}

}