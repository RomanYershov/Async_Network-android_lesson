package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RxActivity extends AppCompatActivity {

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


        loadBtn.setOnClickListener(view -> {
            loadBtn.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
              NetworkLongOperations networkLongOperations =
                      new NetworkLongOperations();


            Observable<String> observable =
                    networkLongOperations.getMailRuSite();

            observable
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(result -> {
                        loadFinishedTv.setVisibility(View.VISIBLE);
                        loadFinishedTv.setText(result);
                        progressBar.setVisibility(View.GONE);
                    }, throwable -> {
                        progressBar.setVisibility(View.GONE);
                        loadBtn.setVisibility(View.VISIBLE);
                        Toast.makeText(this,
                                throwable.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    });
        });
    }
}
