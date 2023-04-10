package com.example.feedbacker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    ListView feedbackList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Feedbacker feedbacker = new Feedbacker();

        new Thread(() -> {
            ArrayList<Feedback> feedbacks;
            try {
                feedbacks = new ArrayList<>(feedbacker.getReviews(getIntent().getExtras().getString("organization")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            FeedbackAdapter feedbackAdapter =
                    new FeedbackAdapter(ListActivity.this, R.layout.list_item, feedbacks);

            feedbackList.setAdapter(feedbackAdapter);
        });
    }
}
