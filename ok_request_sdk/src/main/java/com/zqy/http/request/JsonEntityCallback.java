package com.zqy.http.request;


import com.zqy.http.HttpManage;
import com.zqy.sdk.gson.Gson;
import com.zqy.sdk.gson.JsonSyntaxException;

/**
 * 作者: zhangqingyou
 * 时间: 2020/9/14
 * 描述: json对象实体类
 */

public abstract class JsonEntityCallback<T> extends BaseCallback {
    private Class<T> classOfBean;//json对象实体

    public JsonEntityCallback(Class<T> classOfBean) {
        this.classOfBean = classOfBean;
    }

    public JsonEntityCallback(Class<T> classOfBean, String requestName) {
        super(requestName);
        this.classOfBean = classOfBean;
    }

    @Override
    public void onFinish(String msg) {

    }

    @Override
    public void onSuccess(com.zqy.http.okgo.model.Response<String> response) {
        super.onSuccess(response);
        try {
            T t = new Gson().fromJson(response.body(), classOfBean);
            onSuccess(t);
        } catch (JsonSyntaxException e) {

            if (HttpManage.getApiCallback() != null) {
                response.setException(new JsonSyntaxException("json数据格式错误:" + e.getMessage()));
                HttpManage.getApiCallback().onError(getBaseUrl(), getEndUrl(), response);
            }


        }

    }

    /**
     * 对返回数据进行操作的回调， UI线程
     */
    public abstract void onSuccess(T t);

}