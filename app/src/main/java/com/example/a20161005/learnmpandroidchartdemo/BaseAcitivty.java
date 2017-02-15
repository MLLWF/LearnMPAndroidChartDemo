package com.example.a20161005.learnmpandroidchartdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

/**
 * Created by ML on 2017/2/15.
 */

public class BaseAcitivty extends FragmentActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    protected void startToAcitivty(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }
}
