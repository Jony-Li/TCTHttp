package http.tct.jony.com.tcthttp.http;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Jony on 2018/3/4 0004.
 * Email: litaocreate@gmail.com
 * Github: https://github.com/Jony-Li/
 * 处理响应结果
 */

public class JsonHttpListener<T> implements IHttpListener {
    private static String TAG = "JsonHttpListener";
    //1.回调把结果返回到调用层
    private IDataListener<T> dataListener;
    //2.响应结果调用层的数据类型（接收对象的类型）
    Class<T> responseClass;
    //定义一个handle做线程的切换
    Handler handler = new Handler(Looper.getMainLooper());

    public JsonHttpListener(IDataListener<T> dataListener, Class<T> responseClass) {
        this.dataListener = dataListener;
        this.responseClass = responseClass;
    }

    @Override
    public void onSuccess(InputStream inputStream) {
        //把获取到的结果转换成字符串
        String content = getContent(inputStream);

        //把获取的字符串（JSON）转换成对象
        final T response = JSON.parseObject(content,responseClass);
        Log.e("TAG",response.toString());
        //把结果传递给调用层的接口
        handler.post(new Runnable() {//将数据直接返回给主线程
            @Override
            public void run() {
                if (response != null){
                    Log.e("JsonHttpListener","onSuccess");
                    dataListener.onSuccess(response);
                }
            }
        });
    }

    @Override
    public void onFailure() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Log.e("JsonHttpListener","onFailure");
                dataListener.onFailure();
            }
        });
    }

    //读取流中的数据
    private String getContent(InputStream inputStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null){
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            System.out.print("Error= " + e.toString());
            e.printStackTrace();
        } finally {
            if (inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    System.out.print("Error= " + e.toString());
                    e.printStackTrace();
                }
            }
        }
        return  sb.toString();
    }
}
