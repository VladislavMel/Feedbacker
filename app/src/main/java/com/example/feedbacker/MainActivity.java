package com.example.feedbacker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.feedbacker.Feedbacker.Feedback;
import com.example.feedbacker.Feedbacker.Feedbacker;
import com.example.feedbacker.databinding.ActivityMainBinding;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        recyclerView = binding.list;

        new Thread(() -> {
            try {
                ArrayList<Feedback> feedbacks = Feedbacker.getReviews("Лаврушка");
                this.runOnUiThread(() -> updateUIWithPizzaVenues(feedbacks));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();

    }

    private void updateUIWithPizzaVenues(ArrayList<Feedback> feedbacks) {
        FeedbackAdapter feedbackAdapter = new FeedbackAdapter(this, feedbacks);
        recyclerView.setAdapter(feedbackAdapter);
    }
}
