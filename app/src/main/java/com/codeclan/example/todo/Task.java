package com.codeclan.example.todo;

import java.util.Date;
import java.util.UUID;

/**
 * Created by user on 14/11/2016.
 */

public class Task {

    private UUID mId;
    private String mTitle;
    private String mDetails;
    private Date mDate;
    private boolean mCompleted;

    public Task()
    {
        this(UUID.randomUUID());
    }

    public Task(UUID id)
    {
        mId = id;
        mDate = new Date();
    }

    // create getter and setters... (mId does not need setter)
    public UUID getId()
    {
        return mId;
    }

    public String getTitle()
    {
        return mTitle;
    }

    public void setTitle(String title)
    {
        this.mTitle = title;
    }


    public String getDetails()
    {
        return mDetails;
    }

    public void setDetails(String details)
    {
        this.mDetails = details;
    }

    public Date getDate()
    {
        return mDate;
    }

    public void setDate(Date date)
    {
        this.mDate = date;
    }

    public boolean isCompleted()
    {
        return mCompleted;
    }

    public void setCompleted(boolean completed)
    {
        this.mCompleted = completed;
    }
}
