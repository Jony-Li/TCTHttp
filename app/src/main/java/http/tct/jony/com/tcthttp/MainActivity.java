package http.tct.jony.com.tcthttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import http.tct.jony.com.tcthttp.http.IDataListener;
import http.tct.jony.com.tcthttp.http.TCTHttp;

public class MainActivity extends AppCompatActivity {
    //public String url = "https://api.douban.com/v2/movie/top250";
    public String url = "http://v.juhe.cn/historyWeather/citys?4b716e19d2fe771dc6e78032f9cd86ec=&province_id=16";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view){
        TCTHttp.sendJsonRequest(url, null, ResponseData.class, new IDataListener<ResponseData>() {
            @Override
            public void onSuccess(ResponseData responseData) {
                Toast.makeText(MainActivity.this,responseData.getReason(),Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure() {
            }
        });
    }
}
