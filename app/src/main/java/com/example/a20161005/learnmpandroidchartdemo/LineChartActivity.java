package com.example.a20161005.learnmpandroidchartdemo;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ML on 2017/2/15.
 * <p>
 * new LineData(List<DataSet> datasets,List<String> xvals)
 * DataSet ------> new DataSet(List<Entey> entrys,String label)
 * Entry------> new Entry(Float value,int index)
 */

public class LineChartActivity extends BaseAcitivty {

    private LineChart mLineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.line_chart_view);
        mLineChart = (LineChart) findViewById(R.id.line_chart);
        mLineChart.setData(getLineData());
    }

    public LineData getLineData() {
        LineData data = new LineData(getILineDataSetList());
        return data;
    }

    private List<ILineDataSet> getILineDataSetList() {
        List<ILineDataSet> lineDataSets = new ArrayList<>();
        lineDataSets.add(getLineDataSet());
        return lineDataSets;
    }

    private LineDataSet getLineDataSet() {
        LineDataSet set1;
        if (mLineChart.getData() != null && mLineChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) mLineChart.getData().getDataSetByIndex(0);
            set1.setValues(getEntryList(10, 100));
            mLineChart.getData().notifyDataChanged();
            mLineChart.notifyDataSetChanged();
        } else {
            set1 = new LineDataSet(getEntryList(10, 100), "LineDataSet 1");
            /**
             * 设置折线线条为虚像
             * @param lineLength  虚线的线段的长度
             * @param spaceLength 虚线线段之间间隔的距离
             * @param phase       偏移量
             */
            set1.enableDashedLine(10f, 5f, 0f); //设置折线线条为虚像
            /**配置折线高亮的颜色**/
            set1.enableDashedHighlightLine(20f, 10f, 0f);
            /**配置折线的颜色**/
            set1.setColor(Color.BLACK);
            /**配置折线坐标点的颜色（默认是蓝色空心）**/
            set1.setCircleColor(Color.BLACK);
            /**配置折线的宽度**/
            set1.setLineWidth(1f);
            /**配置折线坐标点圆的半径**/
            set1.setCircleRadius(3f);
            /**配置折线坐标点圆是否是空心，默认是空心的**/
            set1.setDrawCircleHole(false);
            /**配置每个数据值字体显示的大小**/
            set1.setValueTextSize(9f);
            /**配置绘制折线所在的范围的区域是否被填充颜色（默认不被填充，填充颜色默认是蓝色）**/
            set1.setDrawFilled(true);

            set1.setFormLineWidth(1f);
            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set1.setFormSize(15.f);
            /**配置折线所在的范围的区域填充的颜色**/
            if (Utils.getSDKInt() >= 18) {
                // fill drawable only supported on api level 18 and above
                Drawable drawable = ContextCompat.getDrawable(this, R.drawable.fade_red);
                set1.setFillDrawable(drawable);
            }
            else {
                set1.setFillColor(Color.BLACK);
            }
        }
        return set1;
    }



    private List<Entry> getEntryList(int count, int range) {

        ArrayList<Entry> values = new ArrayList<>();

        for (int i = 0; i < count; i++) {

            float val = (float) (Math.random() * range) + 3;
            values.add(new Entry(i, val));
        }
        return values;
    }

}
