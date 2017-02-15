package com.example.a20161005.learnmpandroidchartdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ML on 2017/2/15.
 */

public class PieChartActivity extends BaseAcitivty implements OnChartValueSelectedListener {

    private PieChart mPieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pie_chart_view);
        mPieChart = (PieChart) findViewById(R.id.pie_chart);
        /**配置每块扇形在饼图中所占的百分比，默认是false，只会显示扇形所对应的数值**/
        mPieChart.setUsePercentValues(true);
        /**配置饼图是否显示描述信息，默认是true**/
        mPieChart.getDescription().setEnabled(false);
        /**配置饼图描述信息**/
        mPieChart.getDescription().setText("这是一个饼图");
        /**配置饼图描述信息字体的颜色**/
        mPieChart.getDescription().setTextColor(Color.BLUE);
        /**配置饼图描述信息字体的大小**/
        mPieChart.getDescription().setTextSize(10f);
        /**配置饼图所在位置的偏移量，注意：饼图会随着偏移量的大小进行缩放，但是字体不会缩放**/
        mPieChart.setExtraOffsets(5, 10, 5, 5);
        /**
         * 减速的摩擦系数在[0; 1]区间，数值越高表示速度会缓慢下降，例如，如果将其设置为0，将立即停止。 1是一个无效的值，会自动转换至0.9999。
         */
        mPieChart.setDragDecelerationFrictionCoef(0.95f);
        /**
         * 中心字体的样式，比我宋体、楷体等等
         */
        //        mPieChart.setCenterTextTypeface(mTfLight);
        /**配置饼图中间是否是空心的，默认是空心的**/
        mPieChart.setDrawHoleEnabled(true);
        /**配置饼图中间空心的的颜色**/
        mPieChart.setHoleColor(Color.WHITE);
        /**配置饼图中间空圆的半径的**/
        mPieChart.setHoleRadius(58f);
        /**配置是否显示中心字体**/
        mPieChart.setDrawCenterText(true);
        /**设置中心文字**/
        mPieChart.setCenterText("我是一个饼图呦！");
        /**配置饼图扇形内弧的颜色**/
        mPieChart.setTransparentCircleColor(Color.WHITE);
        /**配置饼图扇形内弧颜色的透明度，区间在[0,255]，0代表完全透明，255代表完全不透明**/
        mPieChart.setTransparentCircleAlpha(110);
        /**配置饼图扇形内弧的半径**/
        mPieChart.setTransparentCircleRadius(61f);
        /**配置饼图是否可以被旋转**/
        mPieChart.setRotationEnabled(true);
        /**配置饼图旋转的偏移量**/
        mPieChart.setRotationAngle(50);
        /**配置饼图扇形是否可以被点击触发特效**/
        mPieChart.setHighlightPerTapEnabled(true);
        /**配置饼图扇形点击监听事件**/
        mPieChart.setOnChartValueSelectedListener(this);
        mPieChart.setData(getPieData());
        /**
         * 配置绘制饼图是的动画效果，可以调用invalidate()进行刷新，默认绘制没有动画效果
         * API LEVEL 11 (Android 3.0.x) AND HIGHER.
         * @param durationMillis  动画效果持续时间
         * @param easing         动画类型
         */
        mPieChart.animateY(1400, Easing.EasingOption.EaseInOutSine);
        /**配置是否绘制每块扇形的标签**/
        mPieChart.setDrawEntryLabels(true);
        /**配置饼图每块扇形内标签的字体样式**/
        mPieChart.setEntryLabelColor(Color.WHITE);
        mPieChart.setEntryLabelTextSize(8f);
        /**配置图例**/
        Legend l = mPieChart.getLegend();
        /**配置是否显示图例**/
        l.setEnabled(true);
        /**配置图例在垂直方向上的位置**/
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        /**配置图例在水平方向上的位置**/
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        /**配置图例是垂直走向还是水平走向**/
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        /**配置图里是否是整个图标布局的一部分,涉及到到偏移等问题**/
        l.setDrawInside(false);
        /**配置图例在水平方向上的间隙，配合图例的走向使用*/
        l.setXEntrySpace(0f);
        /**配置图例在垂直方向上的间隙，配合图例的走向使用*/
        l.setYEntrySpace(17f);
        /**配置图例在垂直方向上位置的偏移量，配合图例的走向使用*/
        l.setYOffset(0f);
        /**配置图例在水平方向上位置的偏移量，配合图例的走向使用*/
        l.setXOffset(10f);

    }

    public PieData getPieData() {
        PieData data = new PieData(getIPieDataSet());
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        return data;
    }

    private IPieDataSet getIPieDataSet() {

        PieDataSet set1 = new PieDataSet(getPieEntryList(5, 100), "PieDataSet 1");
        /**配置每块饼图之间的间隙宽度**/
        set1.setSliceSpace(2f);
        set1.setSelectionShift(10f);
        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);
        /**配置饼图每一块扇形的颜色**/
        colors.add(ColorTemplate.getHoloBlue());
        set1.setColors(colors);
        return set1;
    }

    private List<PieEntry> getPieEntryList(int count, int range) {

        ArrayList<PieEntry> values = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            values.add(new PieEntry((float) ((Math.random() * range) + range / 5), "PieEntry" + i));
        }
        return values;
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        if (e == null)
            return;
        Log.e("VAL SELECTED",
                "Value: " + e.getY() + ", index: " + h.getX()
                        + ", DataSet index: " + h.getDataSetIndex());
    }

    @Override
    public void onNothingSelected() {
        Log.e("PieChart", "nothing selected");
    }
}
