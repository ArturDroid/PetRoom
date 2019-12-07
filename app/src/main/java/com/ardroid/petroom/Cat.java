package com.ardroid.petroom;

import android.util.Log;

public class Cat implements Voice {

    @Override
    public void voice(){
        Log.d(TAG,"meow");
    }
}
