package com.example.feedbacker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class FeedbackAdapter extends ArrayAdapter<Feedback> {

    private final LayoutInflater inflater;
    private final int layout;
    private final List<Feedback> feedbacks;

    public FeedbackAdapter(Context context, int resource, List<Feedback> feedbacks) {
        super(context, resource, feedbacks);
        this.feedbacks = feedbacks;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        @SuppressLint("ViewHolder") View view = inflater.inflate(this.layout, parent, false);

        TextView nameView = view.findViewById(R.id.name);
        TextView textView = view.findViewById(R.id.text);

        Feedback feedback = feedbacks.get(position);

        nameView.setText(feedback.getName());
        textView.setText(feedback.getText());

        return view;
    }
}

