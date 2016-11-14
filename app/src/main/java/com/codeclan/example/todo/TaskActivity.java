package com.codeclan.example.todo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;

public class TaskActivity extends SingleFragmentActivity
{
    @Override
    protected Fragment createFragment()
    {
        return new TaskFragment();
    }
}
