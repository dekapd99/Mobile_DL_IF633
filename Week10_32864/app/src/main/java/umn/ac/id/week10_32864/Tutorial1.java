package umn.ac.id.week10_32864;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Tutorial1 extends AppCompatActivity {
    private static final String TEXT_STATE = "currentText";
    private TextView mTextView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial1);
        getSupportActionBar().setTitle("Tutorial 1");
        getSupportActionBar().setHomeButtonEnabled(true);
        mTextView = findViewById(R.id.textView1);
        mProgressBar = findViewById(R.id.progressBar);
        mProgressBar.setMax(100);
        if(savedInstanceState!=null) {
            mTextView.setText(savedInstanceState.getString(TEXT_STATE));
        }
    }

    public void startTask(View view) {
        mTextView.setText("Siap untuk mulai");
        new AsyncTaskSederhana().execute((int)(Math.random()*50)+10);
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(TEXT_STATE, mTextView.getText().toString());
    }
    private class AsyncTaskSederhana extends AsyncTask<Integer, Integer, String> {
        @Override
        protected void onPreExecute() {
            mProgressBar.setVisibility(View.VISIBLE);
            mProgressBar.setProgress(0);
        }
        @Override
        protected String doInBackground(Integer... integers) {
            int n = integers[0].intValue();
            int s = 0;
            try {
                s = n * 200;
                for(int i = 0; i < n; i++) {
                    Thread.sleep(200);
                    publishProgress(((int)((100 * i)/(float) n)));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Aktif lagi setelah tidur selama "+ s +  " milidetik";
        }
        @Override
        protected void onProgressUpdate(Integer ... progress){
            mTextView.setText("progress = "+progress[0]+" persen");
            mProgressBar.setProgress(progress[0]);
        }
        @Override
        protected void onPostExecute(String result) {
            mTextView.setText(result);
            mProgressBar.setVisibility(View.INVISIBLE);
        }
    }

}