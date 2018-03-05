package http.tct.jony.com.tcthttp.http;

/**
 * Created by Jony on 2018/3/4 0004.
 * Email: litaocreate@gmail.com
 * Github: https://github.com/Jony-Li/
 * 用于返回结果给调用层
 */

public interface IDataListener<T> {
    void onSuccess(T t);
    void onFailure();
}
