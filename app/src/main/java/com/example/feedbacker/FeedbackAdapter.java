package com.example.feedbacker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feedbacker.Feedbacker.Feedback;
import com.example.feedbacker.PizzaPlanet.Organization;

import java.util.List;


public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final List<Feedback> feedbacks;

    public FeedbackAdapter(Context context, List<Feedback> feedbacks) {
        super();
        this.feedbacks = feedbacks;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public FeedbackAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedbackAdapter.ViewHolder holder, int position) {
        Feedback feedback = feedbacks.get(position);
        holder.name.setText(feedback.getName());
        holder.text.setText(feedback.getText());
    }

    @Override
    public int getItemCount() {
        return feedbacks.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView name, text;
        ViewHolder(View view){
            super(view);
            name = view.findViewById(R.id.name);
            text = view.findViewById(R.id.text);
        }
    }
}

