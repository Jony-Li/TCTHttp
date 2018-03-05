package http.tct.jony.com.tcthttp.http;

import android.util.Log;

/**
 * Created by Jony on 2018/3/4 0004.
 * Email: litaocreate@gmail.com
 * Github: https://github.com/Jony-Li/
 * 对外API
 * 1.请求路径
 * 2.请求参数
 * 3.获取结果对象
 * 4.处理结果用的回调
 */

public class TCTHttp {
    /**
     * JSON 操作
     */
    public static<T,M> void sendJsonRequest(String url,T requestInfo,Class<M> responseClass,IDataListener<M> dataListener ){
        Log.e("TCTHttp","sendJsonRequest");
        IHttpService httpService = new JsonHttpService();
        IHttpListener httpListener = new JsonHttpListener<>(dataListener,responseClass);
        HttpTask<T> httpTask = new HttpTask<>(url,requestInfo,httpService,httpListener);
        ThreadPoolManager.getInstance().execute(httpTask);
    }
    /**
     * 其他操作
     */
}
