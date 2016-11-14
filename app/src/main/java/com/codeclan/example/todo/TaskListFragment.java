package com.codeclan.example.todo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by user on 14/11/2016.
 */

public class TaskListFragment extends Fragment
{
    private RecyclerView mTaskRecyclerView;
    private TaskAdapter mAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_task_list, container, false);
        mTaskRecyclerView = (RecyclerView) view.findViewById(R.id.task_recycler_view);
        mTaskRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    private void updateUI()
    {
        TaskList taskList = TaskList.get(getActivity());
        List<Task> tasks = taskList.getTasks();
        mAdapter = new TaskAdapter(tasks);
        mTaskRecyclerView.setAdapter(mAdapter);
    }

    private class TaskHolder extends RecyclerView.ViewHolder
    {
        public TextView mTitleTextView;
        public TaskHolder(View itemView)
        {
            super(itemView);
            mTitleTextView = (TextView) itemView;
        }
    }

    private class TaskAdapter extends RecyclerView.Adapter<TaskHolder>
    {
        private List<Task> mTasks;
        public  TaskAdapter(List<Task> tasks)
        {
            mTasks = tasks;
        }

        @Override
        public TaskHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            return new TaskHolder(view);
        }

        @Override
        public void onBindViewHolder(TaskHolder holder, int position)
        {
            Task task = mTasks.get(position);
            holder.mTitleTextView.setText(task.getTitle());
        }

        @Override
        public int getItemCount()
        {
            return mTasks.size();
        }
    }
}
