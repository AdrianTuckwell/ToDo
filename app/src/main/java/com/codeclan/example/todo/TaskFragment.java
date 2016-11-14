package com.codeclan.example.todo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by user on 14/11/2016.
 */

public class TaskFragment extends Fragment
{

    private Task mTask;
    private EditText mTitleField;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mTask = new Task();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancesState)
    {
        View v = inflater.inflate(R.layout.fragment_task,container,false);

        mTitleField = (EditText)v.findViewById(R.id.task_title);
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

        return v;
    }
}
