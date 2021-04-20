package umn.ac.id.week10_32864;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;

public class CustomService extends Service {
    public CustomService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("CUSTOMSERVICE","onCreate: CustomService");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i("CUSTOM SERVICE","onBind: Service Bind");
        return null;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.i("CUSTOMSERVICE","onDestroy: Service Destroyed");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        Log.i("CUSTOMSERVICE","onStartCommand: "+startId);
//        int n =(int)(Math.random()*50)+10;
//        try {
//            for(int i = 0; i < n; i++) {
//                Thread.sleep(200);
//                Log.i("CUSTOMSERVICE", "onStartCommand: " + startId +
//                        " berjalan "+ ((int)((100 * i)/(float) n)) + " persen");
//            }
//            Log.i("CUSTOMSERVICE", "onStartCommand: " + startId + " Selesai");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        AsyncTask customServiceTask = new CustomServiceTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,startId);
        return Service.START_STICKY;
    }
}