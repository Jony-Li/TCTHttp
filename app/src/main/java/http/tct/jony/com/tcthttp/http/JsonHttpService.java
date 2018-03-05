package http.tct.jony.com.tcthttp.http;

import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Jony on 2018/3/4 0004.
 * Email: litaocreate@gmail.com
 * Github: https://github.com/Jony-Li/
 * JSON 请求实现方式
 */

public class JsonHttpService implements IHttpService {
    String url;
    private byte[] requestData;
    IHttpListener httpListener;
    HttpURLConnection urlConnection;
    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void setRequestData(byte[] requestData) {
        this.requestData = requestData;
    }

    @Override
    public void execute() {
        //对网络的真实操作
        httpUrlConnPost();
    }

    @Override
    public void setHttpCallBack(IHttpListener httpListener) {
        this.httpListener = httpListener;
    }

    private void httpUrlConnPost() {
        URL url = null;
        OutputStream out = null;
        Log.e("JsonHttpService","httpUrlConnPost");
        try {
            url = new URL(this.url);
            try {
                urlConnection = (HttpURLConnection) url.openConnection();//打开http连接
                urlConnection.setConnectTimeout(6000);//连接超时时间
                urlConnection.setUseCaches(false);//不使用缓存
                urlConnection.setInstanceFollowRedirects(true);
                urlConnection.setReadTimeout(3000);//响应的超时时间
                urlConnection.setDoInput(true);//设置这个连接是否可以写入数据
                urlConnection.setDoOutput(true);//设置这个连接是否可以输出数据
                urlConnection.setRequestMethod("POST");//设置请求方式
                //urlConnection.setRequestProperty("Content-Type","application/json; charset=UTF-8");//设置文件类型
                urlConnection.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");
                urlConnection.connect();
                //使用字节流发送数据
                out = urlConnection.getOutputStream();
                //缓冲字节流包装类
                BufferedOutputStream bos = new BufferedOutputStream(out);
                if (requestData != null){
                    bos.write(requestData);
                }
                //把字节数组的数据写入缓冲区中
                bos.flush();//刷新缓冲区，发送数据
                bos.close();
                //字符流写入数据
                Log.e("JsonHttpService","InputStream111----->" + urlConnection.getResponseCode() );
                if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                    InputStream in = urlConnection.getInputStream();
                    Log.e("JsonHttpService","InputStream");
                    httpListener.onSuccess(in);//将流数据传递给IHttpListener
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (out != null){
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
