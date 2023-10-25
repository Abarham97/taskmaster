package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskListViewHolder> {
    List<Task> tasks;
    Context callingActivity;

    public TaskAdapter(List<Task> tasks, Context callingActivity) {
        this.tasks = tasks;
        this.callingActivity = callingActivity;
    }

    public TaskAdapter() {
        super();
    }

    @NonNull
    @Override
    public TaskAdapter.TaskListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View taskFragment = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_task, parent, false);
        return new TaskListViewHolder(taskFragment);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.TaskListViewHolder holder, int position) {
        TextView taskText = (TextView) holder.itemView.findViewById(R.id.TaskFragmentTextView);
        Log.d("SHANAB", "onBindViewHolder: " + tasks.get(position).getTitle());
        String taskTitle = tasks.get(position).getTitle();
        taskText.setText(position  + ". " + taskTitle);
        View listViewHolder = holder.itemView;
        listViewHolder.setOnClickListener(view -> {
            Intent goToTaskFormIntent = new Intent(callingActivity, TaskDetail.class);
            goToTaskFormIntent.putExtra("TaskTitle", taskTitle);
            callingActivity.startActivity(goToTaskFormIntent);
        });
    }

    @Override
    public int getItemCount() {
       return tasks.size();
    }
    public static class TaskListViewHolder extends RecyclerView.ViewHolder {
        public TaskListViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
