package com.murainy.safeexam.Utils;

import android.content.Context;
import android.util.Log;

import com.murainy.safeexam.beans.Grade;
import com.murainy.safeexam.beans.Paper;
import com.murainy.safeexam.beans.Question;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by murainy on 2015/12/29.
 */
public class BmobUtils {
    private static Set<Paper> paperSet;
    public static List<Grade> gradeList = new ArrayList<Grade>();
    public static List<Paper> paperList = new ArrayList<Paper>();
    public static List<Question> questionsList = new ArrayList<Question>();
    public static List<Question> qthList = new ArrayList<Question>();

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

    public static void downloadQuestionList(Context context, String paperName) {

        BmobQuery<Question> query = new BmobQuery<Question>();
        query.addWhereEqualTo("paperName", paperName);
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

    public static void downloadQuestionListMy(Context context, String paperName) {

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

    public static void updatepaper( List<Paper> paper) {
        for ( int i = 0; i < paper.size(); i++) {
            Paper q = paper.get(i);
            q.save(new SaveListener<String>() {
                @Override
                public void done(String objectId, BmobException e) {
                    if (e == null) {
                        Logger.i("标记成功");
                    } else {
                        Logger.i("标记失败"  + e);
                    }
                }
            });
        }
    }

    public static void updatemark(  List<Question> questions) {
        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            q.setMark("1");
            q.update(new UpdateListener() {
                @Override
                public void done(BmobException e) {
                    if (e == null) {
                        Logger.i("标记成功");
                    } else {
                        Logger.i("标记失败"  + e);
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
                    Logger.i("成绩失败"+e);
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
     * 判断某个字符串是否存在于数组中
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
}