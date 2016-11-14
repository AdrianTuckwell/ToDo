package com.codeclan.example.todo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.UUID;

/**
 * Created by user on 14/11/2016.
 */

public class TaskFragment extends Fragment
{
    private static final String ARG_TASK_ID = "task_Id";

    private Task mTask;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mCompletedCheckBox;

    public static TaskFragment newInstance(UUID taskId)
    {
        Bundle args = new Bundle();
        args.putSerializable(ARG_TASK_ID, taskId);
        TaskFragment fragment = new TaskFragment();
            fragment.setArguments(args);
            return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        UUID taskId = (UUID)getArguments().getSerializable(ARG_TASK_ID);
        mTask = TaskList.get(getActivity()).getTask(taskId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancesState)
    {
        View v = inflater.inflate(R.layout.fragment_task,container,false);

        mTitleField = (EditText)v.findViewById(R.id.task_title);
        mTitleField.setText(mTask.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
                // Blank on purpose...
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                mTask.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s)
            {
                // Blank on purpose...
            }
        });

        mDateButton = (Button)v.findViewById(R.id.task_date);
        mDateButton.setText(DateFormat.format("EEEE dd MMM yyyy", mTask.getDate()).toString());
        mDateButton.setEnabled(false);

        mCompletedCheckBox = (CheckBox)v.findViewById(R.id.task_completed);
        mCompletedCheckBox.setChecked(mTask.isCompleted());
        mCompletedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
            mTask.setCompleted(isChecked);
            }
        });

        return v;
    }
}
