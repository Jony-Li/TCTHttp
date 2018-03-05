package http.tct.jony.com.tcthttp.http;

import java.io.InputStream;

/**
 * Created by Jony on 2018/3/4 0004.
 * Email: litaocreate@gmail.com
 * Github: https://github.com/Jony-Li/
 * 处理请求结果
 */

public interface IHttpListener {
    //接收上一个接口（IHttpService）的处理结果
    void onSuccess(InputStream inputStream);
    void onFailure();
}
