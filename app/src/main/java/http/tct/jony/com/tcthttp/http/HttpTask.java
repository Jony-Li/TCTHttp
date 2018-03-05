package http.tct.jony.com.tcthttp.http;

import com.alibaba.fastjson.JSON;

import java.io.UnsupportedEncodingException;

/**
 * Created by Jony on 2018/3/4 0004.
 * Email: litaocreate@gmail.com
 * Github: https://github.com/Jony-Li/
 */

public class HttpTask<T> implements Runnable {
    private IHttpService httpService;
    private IHttpListener httpListener;

    protected HttpTask(String url,T requestInfo,IHttpService httpService, IHttpListener httpListener) {//只在包里面使用
        this.httpService = httpService;
        this.httpListener = httpListener;
        //设置URL
        httpService.setUrl(url);
        //设置关系
        httpService.setHttpCallBack(httpListener);
        //设置请求参数
        if (requestInfo != null){
            //把对象转换成JSON格式的请求参数
            String requestContent = JSON.toJSONString(requestInfo);
            try {
                this.httpService.setRequestData(requestContent.getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        httpService.execute();
    }
}
