package com.example.myapplication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button loadBtn;
    private ProgressBar progressBar;
    private TextView loadFinishedTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadBtn = findViewById(R.id.ma_load_btn);
        progressBar = findViewById(R.id.ma_progress_pb);
        loadFinishedTv = findViewById(R.id.ma_text_tv);


        loadBtn.setOnClickListener(view -> new LongTask().execute());
    }

     class LongTask extends AsyncTask<Void, Void, String> {


        //метод выполняющийсе перед созданием и выполнением рабочего потока
        @Override
        protected void onPreExecute() {
            loadBtn.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        }

        //метод выполняющийся в отдельном(рабочем) потоке
        @Override
        protected String doInBackground(Void... voids) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Log.e(MainActivity.class.getSimpleName(), e.getMessage());
            }
            return "Some response";
        }


        //метод выполняющийся после работы раб потока
        @Override
        protected void onPostExecute(String s) {
           progressBar.setVisibility(View.GONE);
           loadFinishedTv.setVisibility(View.VISIBLE);
           loadFinishedTv.setText(s);
        }
    }
}
