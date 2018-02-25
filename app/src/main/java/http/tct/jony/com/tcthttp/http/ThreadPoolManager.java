package http.tct.jony.com.tcthttp.http;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Jony on 2018/2/25 0025.
 * Email: litaocreate@gmail.com
 * Github: https://github.com/Jony-Li/
 */

public class ThreadPoolManager {
    //1.将任务添加到请求队列
    //定义请求队列  容量 阻塞 性能优化
    private LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue();
    //添加任务
    public void excute(Runnable runnable){
        try {
            queue.put(runnable);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //2.把请求队列的任务放到线程池处理
    //定义处理中心
    private ThreadPoolExecutor threadPoolExecutor;

    private ThreadPoolManager(){
        threadPoolExecutor = new ThreadPoolExecutor(4,20,
                15, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(4),
                rejectedExecutionHandler);
        threadPoolExecutor.execute(runnable);
    }
    private RejectedExecutionHandler rejectedExecutionHandler = new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable runnable, ThreadPoolExecutor executor) {
            try {
                queue.put(runnable);//把超时的任务再放进队列执行
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
    //3.让整个机制运行起来
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //1.从队列中取出任务
            try {
                Runnable runnable = queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //2.把任务放到处理中心执行
            while (true){
                if (runnable != null){
                    threadPoolExecutor.execute(runnable);
                }
            }
        }
    };
    //单列
    private static ThreadPoolManager instance = new ThreadPoolManager();
    public static ThreadPoolManager getInstance(){
        return instance;
    }
}
