package com.codeclan.example.todo.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.codeclan.example.todo.Task;
import com.codeclan.example.todo.database.TaskDbSchema.TaskTable;

import java.util.Date;
import java.util.UUID;

/**
 * Created by user on 15/11/2016.
 */

public class TaskCursorWrapper extends CursorWrapper
{
    public TaskCursorWrapper (Cursor cursor) {
        super(cursor);
    }

    public Task getTask()
    {
        String uuidString = getString(getColumnIndex(TaskTable.Cols.UUID));
        String title = getString(getColumnIndex(TaskTable.Cols.TITLE));
        String details = getString(getColumnIndex(TaskTable.Cols.DETAILS));
        Long date = getLong(getColumnIndex(TaskTable.Cols.DATE));
        int isCompleted = getInt(getColumnIndex(TaskTable.Cols.COMPLETED));

        Task task = new Task(UUID.fromString(uuidString));
        task.setTitle(title);
        task.setDetails(details);
        task.setDate(new Date(date));
        task.setCompleted(isCompleted !=0);

        return task;
    }
}
