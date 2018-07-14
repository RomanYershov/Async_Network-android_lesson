package com.example.myapplication;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LoaderActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    private static final int LOADER_ID = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader);


        Loader<String> loader = getSupportLoaderManager()
                .initLoader(LOADER_ID, null, this);
        loader.forceLoad();
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new AsyncLoader(getApplicationContext());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String d) {
        //текст из параметра d присваиваем TextView
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}
