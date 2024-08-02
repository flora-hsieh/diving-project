package com.example.divingproject;

import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.widget.ExpandableListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HealthRecommendation extends AppCompatActivity {

    ExpandableListViewAdapter listViewAdapter;
    ExpandableListView expandableListView;
    List<String> chapterList;
    HashMap<String, List<String>> topicList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_health_recommendation);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.health_recommendation), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        expandableListView = findViewById(R.id.eListView);
        showList();

        listViewAdapter = new ExpandableListViewAdapter(this,chapterList,topicList);
        expandableListView.setAdapter(listViewAdapter);
    }

    private void showList() {
        chapterList = new ArrayList<String>();
        topicList = new HashMap<String, List<String>>();

        chapterList.add(getString(R.string.chapter_1));
        chapterList.add(getString(R.string.chapter_2));
        chapterList.add(getString(R.string.chapter_3));
        chapterList.add(getString(R.string.chapter_4));
        chapterList.add(getString(R.string.chapter_5));

        List<String> topic1 = new ArrayList<>();
        topic1.add(getString(R.string.diver_health_fitness_recommendations));


        List<String> topic2 = new ArrayList<>();
        topic2.add(getString(R.string.heart_health));


        List<String> topic3 = new ArrayList<>();
        topic3.add(getString(R.string.drug_use));

        List<String> topic4 = new ArrayList<>();
        topic4.add(getString(R.string.menstruation_pregnancy));


        List<String> topic5 = new ArrayList<>();
        topic5.add(getString(R.string.day_to_day));


        topicList.put(chapterList.get(0), topic1);
        topicList.put(chapterList.get(1), topic2);
        topicList.put(chapterList.get(2), topic3);
        topicList.put(chapterList.get(3), topic4);
        topicList.put(chapterList.get(4), topic5);
    }
}