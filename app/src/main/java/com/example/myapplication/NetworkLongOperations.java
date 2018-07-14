package com.example.myapplication;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkLongOperations {

    public Observable<String> longNetworkOperationStub() {
        return Observable.create(subscriber -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            subscriber.onNext("Получена строка");
        });
    }

    public Observable<String> getMailRuSite() {
        return Observable.create(subscriber -> {
            OkHttpClient client = new OkHttpClient();


            Request request = new Request.Builder()
                    .url("https://mail.ru")
                    .build();

            Response response = client.newCall(request).execute();


            subscriber.onNext( response.body().string());
        });
    }


}
