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
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
	public static ArrayList<Question> questionsList = new ArrayList<Question>() ;
	public static List<String> paperSet = new ArrayList<String>();
	public static List<Grade> gradeList = new ArrayList<Grade>();
	public static List<Paper> paperList = new ArrayList<Paper>();
	private static List<Paper> LP = new ArrayList<>();
	private static List<Testqeba> LT = new ArrayList<>();
	public static List<Testqeba> TestList = new ArrayList<>();
	private static boolean ready = false;
	public  static String ALL="1024" ;

	public static void downloadGradeList(Context context, String username) {
		BmobQuery<Grade> query = new BmobQuery<>();
		query.addWhereEqualTo("username", username);
		query.setLimit(100);
		query.findObjects(new FindListener<Grade>() {
			@Override
			public void done(List<Grade> object, BmobException e) {
				if (e == null) {
					gradeList = new ArrayList<>();
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

		BmobQuery<Paper> query = new BmobQuery<>();
		query.setLimit(100);
		query.findObjects(new FindListener<Paper>() {
			@Override
			public void done(List<Paper> object, BmobException e) {
				if (e == null) {
					Logger.i(object.size() + "///");
					paperList = new ArrayList<>();
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

	public static void downloadTestList(Context context) {

		BmobQuery<Testqeba> query = new BmobQuery<>();
		query.setLimit(100);
		query.findObjects(new FindListener<Testqeba>() {
			@Override
			public void done(List<Testqeba> object, BmobException e) {
				if (e == null) {
					Logger.i(object.size() + "///");
					TestList = new ArrayList<>();
					for (int i = 0; i < object.size(); i++) {
						TestList.add(object.get(i));
					}
					Logger.i(TestList.size() + "///");
					EventBus.getDefault().post(Action.DOWNLOAD_Test_LIST);
				} else {
					Logger.i("查询失败");
					EventBus.getDefault().post(Action.QUERY_ERROR);
				}
			}
		});
	}

	public static void downloadTPaperList(Context context) {

		BmobQuery<Paper> query = new BmobQuery<>();
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

	public static void downloadQuestionList() {
		if(questionsList.size()>0){questionsList.clear();}
		BmobQuery<Question> query = new BmobQuery<Question>();
		//query.addWhereContainedIn("questionid",paperSet);需付费
		query.setLimit(100);
		query.findObjects(new FindListener<Question>() {
			@Override
			public void done(List<Question> object, BmobException e) {
				if (e == null) {
					Logger.i(object.size() + "````");
					for (int i = 0; i < object.size(); i++) {
						questionsList.add(object.get(i));
					}
					Logger.i(questionsList.size() + "````");
					ALL = Integer.toString(questionsList.size());
					EventBus.getDefault().postSticky(Action.DOWNLOAD_QUESTION_EXAM);
					ready = true;
				} else {
					Logger.i("查询失败");
					EventBus.getDefault().postSticky(Action.QUERY_ERROR);
				}
			}

		});
	}



	public static void downloadQuestionListIni() {
		if(questionsList.size()>0){questionsList.clear();}
		BmobQuery<Question> query = new BmobQuery<Question>();
		query.addWhereGreaterThan("count", 0).order("-questionid");
		query.setLimit(100);
		//执行查询方法
		query.findObjects(new FindListener<Question>() {
			@Override
			public void done(List<Question> object, BmobException e) {
				if (e == null) {
					Logger.i(object.size() + "````");
					for (Question question : object) {
						questionsList.add(question);
					}
					Logger.i(questionsList.size() + "````");
					EventBus.getDefault().postSticky(Action.DOWNLOAD_QUESTION_LISTINI);
					ALL = Integer.toString(questionsList.size());
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

	public static void updateTestpaper(List<Testqeba> paper) {
		for (int i = 0; i < paper.size(); i++) {
			Testqeba q = paper.get(i);
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
			Number c = (int) questions.get(i).getCount() + 1;
			q.setCount(c);
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


	public static void questionCount(String paperName, String type, final int qs) {
		BmobQuery<Question> query = new BmobQuery<Question>();
		query.addWhereEqualTo("paperName", paperName);
		query.addWhereEqualTo("type", type);
		query.setLimit(500);
		query.findObjects(new FindListener<Question>() {
			@Override
			public void done(List<Question> object, BmobException e) {
				if (e == null) {
					Logger.i("对象查询成功：共" + object.size() + "条数据。", "");
					int size = object.size();
					int[] numbers = randRange(qs, size);
					if (size>qs) {
						for (int i = 0; i < qs; i++) {
							questionsList.add(object.get(numbers[i]));
						}
					} else {
						for (int i = 0; i < size; i++) {
							questionsList.add(object.get(i));
						}
					}
					Logger.i("数据样品：" + questionsList.size());
					ALL = Integer.toString(questionsList.size());
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
	private static void examListFree(String y, String t, final int c) {

		BmobQuery<Question> query = new BmobQuery<Question>();
		query.addWhereEqualTo("year",y);
		query.addWhereEqualTo("type",t);
		query.setLimit(500);
		query.findObjects(new FindListener<Question>() {
			@Override
			public void done(List<Question> result, BmobException e) {
				if (e == null) {
					int count = result.size();
					Logger.e("条件总数：" + String.valueOf(count));
					if ( count > 0) {
						if (count < c) {
							for (int i = 0; i < count; i++) {
								questionsList.add(result.get(i));
							}

						} else {
							int[] tihao = randRange(c, count);
							for (int i = 0; i < c; i++) {
								questionsList.add(result.get(tihao[i]));
							}
							Logger.e("第一题目：" + paperSet);
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
	private static void examListini(String y, String t, final int c) {
		String bql = "select questionid from Question where year = ? and type = ?";
		BmobQuery<Question> query = new BmobQuery<Question>();
		query.setPreparedParams(new Object[]{y, t});
		query.setSQL(bql);
		query.setLimit(500);
		query.doSQLQuery(new SQLQueryListener<Question>() {

			@Override
			public void done(BmobQueryResult<Question> result, BmobException e) {
				if (e == null) {
					List<Question> list = result.getResults();
					int count = list.size();
					Logger.e("条件总数：" + String.valueOf(count));
					if ( count > 0) {
						if (count < c) {
							for (int i = 0; i < count; i++) {
								paperSet.add(list.get(i).getQuestionid());
							}

						} else {
							int[] tihao = randRange(c, count);
							for (int i = 0; i < c; i++) {
								paperSet.add(list.get(tihao[i]).getQuestionid());
							}
							Logger.e("第一题目：" + paperSet);
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

	public static void testLists(String p) {
		if(questionsList.size()>0){questionsList.clear();}
		questionCount(p, "判断题", 40);
		questionCount(p, "单选题", 40);
		questionCount(p, "多选题", 20);
		EventBus.getDefault().postSticky(Action.DOWNLOAD_QUESTION_TEST);

	}

	public static void examLists(String p) {
		if(paperSet.size()>0){paperSet.clear();}
		examListFree(p, "判断题", 40);
		examListFree(p, "单选题", 40);
		examListFree(p, "多选题", 20);
		EventBus.getDefault().postSticky(Action.DOWNLOAD_QUESTION_SET);


	}

	public static void testBanks() {
		String bql = "select distinct year  from Question ";
		new BmobQuery<Question>().doSQLQuery(bql, new SQLQueryListener<Question>() {

			@Override
			public void done(BmobQueryResult<Question> result, BmobException e) {
				if (e == null) {
					List<Question> list =  result.getResults();

					if (list != null && list.size() > 0) {
						for (int i = 0; i < list.size(); i++) {
							Testqeba t = new Testqeba();
							t.setId(i);
							t.setName(list.get(i).getYear());
							t.setNote("安全考试");
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
		String bql = "select distinct paperName from Question ";
		new BmobQuery<Question>().doSQLQuery(bql, new SQLQueryListener<Question>() {

			@Override
			public void done(BmobQueryResult<Question> result, BmobException e) {
				if (e == null) {
					List<Question> list =  result.getResults();

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