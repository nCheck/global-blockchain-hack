package com.collekarry.globlockhackthon;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

public class Funds extends AppCompatActivity {

    private static String TAG = "FUNDS";

    private float[] yData = {25.3f, 10.6f, 66.76f, 44.32f};
    private String[] xData = {"Monetary", "Cloths" , "Electronics" , "Accessories"};
    PieChart pieChart;

    LinearLayout Payout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_funds);

        pieChart = findViewById(R.id.piechart);

        Payout = findViewById(R.id.linearLayout);

        Payout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Funds.this, GetFunds.class);
                startActivity(i);
            }
        });

        pieChart.setRotationEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setCenterTextColor(Color.BLACK);
        pieChart.setHoleRadius(25f);
        pieChart.getDescription().setText("Donation Based upon its type");
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("Donation Type");
        pieChart.setCenterTextSize(10);
        pieChart.setDrawEntryLabels(true);
        pieChart.setEntryLabelTextSize(20);
        pieChart.getLegend().setTextColor(Color.BLACK);

        addDataSet();

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {



            }

            @Override
            public void onNothingSelected() {

            }
        });

    }

    private void addDataSet() {
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();

        for(int i = 0; i < yData.length; i++){
            yEntrys.add(new PieEntry(yData[i], xData[i]));
        }

        for(int i = 1; i < xData.length; i++){
            xEntrys.add(xData[i]);
        }

        //create the data set
        PieDataSet pieDataSet = new PieDataSet(yEntrys, "Donation Types");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        //add colors to dataset
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.GREEN);
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        colors.add(Color.MAGENTA);

        pieDataSet.setColors(colors);

        //add legend to chart
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        //create pie data object
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(Funds.this, Home.class);
        startActivity(i);
        finish();
    }
}
