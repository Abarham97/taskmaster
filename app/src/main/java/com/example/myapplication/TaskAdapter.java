package com.example.myapplication;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {



    public TaskAdapter(List<Task> tasks, MainActivity mainActivity) {

    }

    static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView taskTitleTextView;

        TaskViewHolder(View itemView) {
            super(itemView);
            taskTitleTextView = itemView.findViewById(R.id.taskTitle);
        }
    }

    @NonNull
    @Override
    public TaskAdapter.TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.TaskViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
