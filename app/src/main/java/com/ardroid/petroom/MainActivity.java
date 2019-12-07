package com.ardroid.petroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.Objects;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MAIN_ACTIVITY";
    static Handler handler = null;
    String resp;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonRandomNumber = findViewById(R.id.buttonRandomNumber);
        Button buttonGetFact = findViewById(R.id.buttonGetFact);
        final TextView textView = findViewById(R.id.textView);
        final EditText editText = findViewById(R.id.editText);

        handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                resp = msg.getData().getString("response");
                Log.d(TAG, "handleMessage: " + resp);
                textView.setText(resp);
            }
        };

        buttonGetFact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GetFact().getFact(Integer.valueOf(editText.getText().toString()),handler);

            }
        });

        buttonRandomNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GetFact().getRandomFact(handler);
            }
        });


        //test


        //getFact(32, handler);
         /*GetFact fact = new GetFact();
         fact.getFact(numbers,handler);*/

        // new GetFact().getFact(numbers,handler);



    }

   /* public void getFact(final int number, final Handler handler){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final String resp = Objects.requireNonNull(APIProvider.getInstance().getFact(number).execute().body()).string();
                    Bundle bundle = new Bundle();
                    bundle.putString("response", resp);
                    Message msg = new Message();
                    msg.setData(bundle);
                    handler.dispatchMessage(msg);
                    Log.d("TAG2", "run: ");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }*/
}
