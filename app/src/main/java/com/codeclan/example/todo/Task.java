package com.codeclan.example.todo;

import java.util.UUID;

/**
 * Created by user on 14/11/2016.
 */

public class Task {

    private UUID mId;
    private String mTitle;

    public Task(){
        //Create an ID for the task
        mId = UUID.randomUUID();
    }


    // create getter and setters... (mId does not need setter)
    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }
}
