package com.codeclan.example.todo.database;

/**
 * Created by user on 15/11/2016.
 */

public class TaskDbSchema
{
    public static final class TaskTable
    {
        public static final String NAME = "tasks";

        public static final class  Cols
        {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DETAILS = "details";
            public static final String DATE = "date";
            public static final String COMPLETED = "completed";
        }

    }
}
