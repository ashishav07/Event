package com.example.owner.event;

public class view {
    private String mName;
    private String mDate;
    private String mTime;
    public view(String name,String date,String time)
    {
        mName=name;
        mDate=date;
        mTime=time;
    }

    public String getmDate() {
        return mDate;
    }

    public String getmName() {
        return mName;
    }

    public String getmTime() {
        return mTime;
    }
}
