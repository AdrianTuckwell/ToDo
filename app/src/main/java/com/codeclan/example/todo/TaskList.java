package com.codeclan.example.todo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.codeclan.example.todo.database.TaskBaseHelper;
import com.codeclan.example.todo.database.TaskCursorWrapper;
import com.codeclan.example.todo.database.TaskDbSchema.TaskTable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by user on 14/11/2016.
 */

public class TaskList
{
    private static TaskList sTaskList;

    //private List<Task> mTasks;
    private Context mContext;
    private SQLiteDatabase mDatabase;

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
        mContext = context.getApplicationContext();
        mDatabase = new TaskBaseHelper(mContext).getWritableDatabase();
    }

    public void addTask (Task t)
    {
        ContentValues values = getContentValues(t);
        mDatabase.insert(TaskTable.NAME,null, values);
    }


    public List<Task> getTasks()
    {
        List<Task> tasks = new ArrayList<>();
        TaskCursorWrapper cursor = queryTasks(null, null);

        try{
            cursor.moveToFirst();
            while (!cursor.isAfterLast())
            {
                tasks.add(cursor.getTask());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return tasks;
    }

    public Task getTask(UUID id)
    {
        TaskCursorWrapper cursor = queryTasks(TaskTable.Cols.UUID + " = ?", new String[] { id.toString()});

        try
        {
            if (cursor.getCount() == 0)
            {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getTask();
        } finally {
            cursor.close();
        }
    }

    public void updateTask(Task task)
    {
        String uuidString = task.getId().toString();
        ContentValues values = getContentValues(task);

        mDatabase.update(TaskTable.NAME, values, TaskTable.Cols.UUID + " = ?", new String[] {uuidString});
    }


    private static ContentValues getContentValues(Task task)
    {
        ContentValues values = new ContentValues();
        values.put(TaskTable.Cols.UUID, task.getId().toString());
        values.put(TaskTable.Cols.TITLE, task.getTitle());
        values.put(TaskTable.Cols.DETAILS, task.getDetails());
        values.put(TaskTable.Cols.DATE, task.getDate().getTime());
        values.put(TaskTable.Cols.COMPLETED, task.isCompleted() ? 1 : 0 );

        return values;
    }

    private TaskCursorWrapper queryTasks(String whereClause, String[] whereArgs)
    {
        Cursor cursor = mDatabase.query(
                TaskTable.NAME,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null // orderBy
        );
        return new TaskCursorWrapper(cursor);
    }

}
