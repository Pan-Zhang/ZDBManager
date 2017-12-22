package asuper.zhangpan.zdbmanager;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhangpan on 17/12/22.
 */

public abstract class DBCommond<T> {

    private static ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final static Handler sUIHandler = new Handler(Looper.getMainLooper());

    public final void Execute(){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try{
                    postResult(doInBackground());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    private void postResult(final T result){
        sUIHandler.post(new Runnable() {
            @Override
            public void run() {
                onPostExecute(result);
            }
        });
    }

    protected abstract T doInBackground();

    protected void onPostExecute(T result){

    }
}
