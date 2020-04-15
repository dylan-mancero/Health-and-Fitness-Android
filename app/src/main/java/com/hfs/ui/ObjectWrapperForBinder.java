package com.hfs.ui;

import android.os.Binder;

import com.hfs.lib.activity.FinishedActivity;

//This class allows us to pass an object from one activity to another.
class ObjectWrapperForBinder extends Binder {

    private final FinishedActivity mData;

    public ObjectWrapperForBinder(FinishedActivity data) {
        mData = data;
    }

    public FinishedActivity getData(){
        return mData;
    }
}
