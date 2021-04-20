package umn.ac.id.week10_32864;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class Tutorial2 extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Void>  {
    private TextView mTextView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial2);
        getSupportActionBar().setTitle("Tutorial 2");
        getSupportActionBar().setHomeButtonEnabled(true);
        mTextView = findViewById(R.id.textView1);
        mProgressBar = findViewById(R.id.progressBar);
        mProgressBar.setMax(100);
    }

    public void startTask(View view){
        if(!getSupportLoaderManager().hasRunningLoaders())
            getSupportLoaderManager().initLoader(0,(Bundle) null, this);
    }

    @NonNull
    @Override
    public Loader<Void> onCreateLoader(int id, @Nullable Bundle args) {
        AsyncTaskLoader<Void> asyncTaskLoader = new ContohLoader(this,(int)(Math.random()*50)+10,this);
        asyncTaskLoader.forceLoad();
        return asyncTaskLoader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Void> loader, Void data)
    {
        getSupportLoaderManager().destroyLoader(0);
    }
    @Override
    public void onLoaderReset(@NonNull Loader<Void> loader) {
    }
    static class ContohLoader extends AsyncTaskLoader<Void> {
        static WeakReference<Tutorial2> mActivity;
        int mCounter = 0;
        public ContohLoader(@NonNull Context context, int n, Tutorial2 main) {
            super(context);
            mCounter = n;
            mActivity = new WeakReference<Tutorial2>(main);
        }
        @Nullable
        @Override
        public Void loadInBackground() {
            try {
                for(int i = 0; i <= mCounter; i++) {
                    Thread.sleep(200);
                    final int progress = ((int)((100 * i)/ (float) mCounter));
                    if (mActivity.get() != null) {
                        mActivity.get().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(progress < 100) {
                                    mActivity.get().mProgressBar.setProgress(progress);
                                    mActivity.get().mTextView.setText("progress = " + progress + " persen");
                                }else {
                                    mActivity.get().mProgressBar.setProgress(100);
                                    mActivity.get().mTextView.setText("Selesai selama " + mCounter * 200 +" milidetik...");
                                }
                            }
                        });
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}