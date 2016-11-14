package com.codeclan.example.todo;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;

import java.util.UUID;

public class TaskActivity extends SingleFragmentActivity
{
    private static final String EXTRA_TASK_ID = "com.codeclan.example.todo.task_id";
    public static Intent newIntent(Context packageContext, UUID taskID)
    {
     Intent intent = new Intent(packageContext, TaskActivity.class);
        intent.putExtra(EXTRA_TASK_ID, taskID);
        return intent;
    }

    @Override
    protected Fragment createFragment()
    {
        UUID taskId = (UUID) getIntent().getSerializableExtra(EXTRA_TASK_ID);
        return TaskFragment.newInstance(taskId);
    }
}
