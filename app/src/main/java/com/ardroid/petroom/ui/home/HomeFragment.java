package com.ardroid.petroom.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.ardroid.petroom.R;
import com.ardroid.petroom.data.APIProvider;
import com.ardroid.petroom.data.GalaxyPicture;
import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;

import retrofit2.Response;
import retrofit2.http.Header;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class HomeFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "HOME_FRAGMENT";

    private HomeViewModel homeViewModel;
    private Date apodDate = new Date();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        final ImageView imageView = root.findViewById(R.id.galaxy_image_view);
        textView.setOnClickListener(this);


        new View.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        };

        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        final Handler homeHendler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                Glide.with(HomeFragment.this).load(msg.getData().getString("url")).into(imageView);
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response<GalaxyPicture> galaxyPictureResponse = APIProvider.getNasaAPI().getGalaxyPicture(APIProvider.API_KEY,apodDate).execute();
                    Bundle bundle = new Bundle();
                    bundle.putString("url", Objects.requireNonNull(galaxyPictureResponse.body()).getUrl());
                    Message message = new Message();
                    message.setData(bundle);
                    homeHendler.sendMessage(message);
                    Log.d(TAG, "run: " + Objects.requireNonNull(galaxyPictureResponse.body()).toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        return root;
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "onClick: aaa ");
    }
}