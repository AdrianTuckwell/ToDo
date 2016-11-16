package com.codeclan.example.todo;

import android.support.v4.app.Fragment;

/**
 * Created by user on 14/11/2016.
 */

public class TaskListActivity extends SingleFragmentActivity
{
    @Override
    protected Fragment createFragment()
    {
        return new TaskListFragment();
    }
}
