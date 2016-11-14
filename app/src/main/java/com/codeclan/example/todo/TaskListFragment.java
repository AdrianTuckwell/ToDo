package com.codeclan.example.todo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

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

    @Override
    public void onResume()
    {
        super.onResume();
        updateUI();
    }

    private void updateUI()
    {
        TaskList taskList = TaskList.get(getActivity());
        List<Task> tasks = taskList.getTasks();
        if (mAdapter == null)
        {
            mAdapter = new TaskAdapter(tasks);
            mTaskRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    private class TaskHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private Task mTask;
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private CheckBox mCompletedCheckBox;
        public TaskHolder(View itemView)
        {
            super(itemView);
            itemView.setOnClickListener(this);
            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_task_title_text_view);
            mDateTextView = (TextView) itemView.findViewById(R.id.list_item_task_date_text_view);
            mCompletedCheckBox = (CheckBox) itemView.findViewById(R.id.list_item_task_completed_check_box);
        }

        @Override
        public void onClick(View v)
        {
                // Toast.makeText(getActivity(), mTask.getTitle() + "clicked!", Toast.LENGTH_SHORT).show();

            Intent intent = TaskActivity.newIntent(getActivity(),mTask.getId());
            startActivity(intent);
        }


        public void bindTask(Task task)
        {
            mTask = task;
            mTitleTextView.setText(mTask.getTitle());
            mDateTextView.setText(DateFormat.format("EEEE dd MMM yyyy", mTask.getDate()).toString());
            mCompletedCheckBox.setChecked(mTask.isCompleted());

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
            View view = layoutInflater.inflate(R.layout.list_item_task, parent, false);
            return new TaskHolder(view);
        }

        @Override
        public void onBindViewHolder(TaskHolder holder, int position)
        {
            Task task = mTasks.get(position);
            holder.bindTask(task);
        }

        @Override
        public int getItemCount()
        {
            return mTasks.size();
        }
    }
}
