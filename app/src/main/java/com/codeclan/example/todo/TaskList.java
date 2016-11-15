package com.codeclan.example.todo;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by user on 14/11/2016.
 */

public class TaskList
{
    private static TaskList sTaskList;

    private List<Task> mTasks;

    public static TaskList get(Context context)
    {
        if (sTaskList == null)
        {
            sTaskList = new TaskList(context);
        }
        return sTaskList;
    }

    private TaskList(Context context)
    {
        mTasks = new ArrayList<>();

//        for (int i = 0; i < 100; i++)
//        {
//            Task task = new Task();
//            task.setTitle("Task #" + i);
//            task.setCompleted(i % 2 == 0);
//            mTasks.add(task);
//        }
    }

    public void addTask (Task t)
    {
        mTasks.add(t);
    }


    public List<Task> getTasks()
    {
        return mTasks;
    }

    public Task getTask(UUID id)
    {
        for (Task task : mTasks)
        {
            if (task.getId().equals(id))
            {
                return task;
            }
        }
        return null;
    }
}
