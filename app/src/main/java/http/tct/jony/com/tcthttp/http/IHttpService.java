package http.tct.jony.com.tcthttp.http;

/**
 * Created by Jony on 2018/3/4 0004.
 * Email: litaocreate@gmail.com
 * Github: https://github.com/Jony-Li/
 * 处理请求
 * 1.设置url
 * 2.设置请求参数
 * 3.执行请求
 */
public interface IHttpService {
    void setUrl(String url);
    void setRequestData(byte[] requestData);
    void execute();
    //使两个接口连接起来
    void setHttpCallBack(IHttpListener httpListener);
}
