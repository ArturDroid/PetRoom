package com.ardroid.petroom.ui.home;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.ardroid.petroom.R;
import com.ardroid.petroom.data.APIProvider;
import com.ardroid.petroom.data.GalaxyPicture;
import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import retrofit2.Response;


public class HomeFragment extends Fragment implements DatePickerDialog.OnDateSetListener {
    private static final String TAG = "HOME_FRAGMENT";
    private static SimpleDateFormat SDF;
    private HomeViewModel homeViewModel;
    private static Handler handler = null;
    private Date apodDate = new Date();
    private Button btnDateSet;
    private Button btnDateReset;
    private TextView dateTextView;
    private ImageView imageView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        SDF = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
        final View root = inflater.inflate(R.layout.fragment_home, container, false);
        dateTextView = root.findViewById(R.id.textViewDate);
        imageView = root.findViewById(R.id.galaxy_image_view);
        btnDateSet = root.findViewById(R.id.btnDateSet);
        btnDateReset = root.findViewById(R.id.btnDateReset);
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                if(msg.getData().containsKey("url")){
                    Glide.with(HomeFragment.this).load(msg.getData().getString("url")).centerCrop().into(imageView);
                    displayDate();
                }
                else if(msg.getData().containsKey("error")){
                    Snackbar.make(root.findViewById(R.id.home_container), getString(R.string.API_error), Snackbar.LENGTH_LONG).show();
                }
                return true;
            }
        });

        btnDateSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDatePicker();
            }
        });

        btnDateReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadImage(new Date());
            }
        });

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadImage(new Date());
    }

    private void loadImage(final Date apodDate) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response<GalaxyPicture> galaxyPictureResponse = APIProvider.getNasaAPI().getGalaxyPicture(APIProvider.API_KEY, APIProvider.SDF.format(apodDate)).execute();
                    if(galaxyPictureResponse.isSuccessful()){
                        HomeFragment.this.apodDate = apodDate;
                        Bundle bundle = new Bundle();
                        bundle.putString("url", Objects.requireNonNull(galaxyPictureResponse.body()).getUrl());
                        Message message = new Message();
                        message.setData(bundle);
                        handler.sendMessage(message);
                        Log.d(TAG, "run: " + Objects.requireNonNull(galaxyPictureResponse.body()).toString());
                    }
                    else{
                        Bundle bundle = new Bundle();
                        bundle.putString("error", galaxyPictureResponse.message());
                        Message message = new Message();
                        message.setData(bundle);
                        handler.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Calendar clnd = Calendar.getInstance();
        clnd.set(year, month, day);
        loadImage(clnd.getTime());
    }

    private void openDatePicker() {
        Calendar clnd = Calendar.getInstance();
        clnd.setTime(apodDate);
        DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(),
                HomeFragment.this,
                clnd.get(Calendar.YEAR),
                clnd.get(Calendar.MONTH),
                clnd.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

    private void displayDate() {
        dateTextView.setText(SDF.format(apodDate));
    }

}