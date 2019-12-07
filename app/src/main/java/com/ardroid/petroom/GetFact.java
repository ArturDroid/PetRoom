package com.ardroid.petroom;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Response;

public class GetFact {

    public void getFact(final int number, final Handler handler) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final String resp = Objects.requireNonNull(APIProvider.getInstance().getFact(number).execute().body()).string();
                    Bundle bundle = new Bundle();
                    bundle.putString("response", resp);
                    Message msg = new Message();
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                    Log.d("TAG2", "run: ");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void getRandomFact(final Handler handler) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final String resp = Objects.requireNonNull(APIProvider.getInstance().getData().execute().body()).string();
                    Bundle bundle = new Bundle();
                    bundle.putString("response", resp);
                    Message msg = new Message();
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                }
        }).start();
    }
}
