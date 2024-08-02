package com.example.divingproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {

    private Context context;
    List<String> chapterList;
    private HashMap<String, List<String>> topicsList;

    public ExpandableListViewAdapter(Context context,List<String> chapterList, HashMap<String, List<String>> topicsList ) {
        this.context = context;
        this.chapterList = chapterList;
        this.topicsList = topicsList;
    }

    @Override
    public int getGroupCount() {
        return this.chapterList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.topicsList.get(this.chapterList.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.chapterList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.topicsList.get(this.chapterList.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String chapterTitle = (String) getGroup(groupPosition);
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_health_recommendation,null);
        }

        TextView chapterTV = convertView.findViewById(R.id.chapter_tv);
        chapterTV.setText(chapterTitle);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String topicTitle = (String) getChild(groupPosition,childPosition);
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_topics,null);
        }

        TextView topicTv = convertView.findViewById(R.id.topics_tv);
        topicTv.setText(topicTitle);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
