package com.example.a20161005.learnmpandroidchartdemo;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends BaseAcitivty implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        findViewById(R.id.btn_line_chart).setOnClickListener(this);
        findViewById(R.id.btn_pie_chart).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        	case R.id.btn_line_chart:
                startToAcitivty(LineChartActivity.class);
                break;
            case R.id.btn_pie_chart:
                startToAcitivty(PieChartActivity.class);
                break;
        	default:
        		break;
        }
    }

}
