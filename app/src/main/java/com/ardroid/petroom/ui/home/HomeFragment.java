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
import androidx.lifecycle.ViewModel;
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
        btnDateSet.setOnClickListener(view -> openDatePicker());
        btnDateReset.setOnClickListener(view -> loadImage(new Date()));
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadImage(new Date());
        homeViewModel.galaxyPictureLiveData.observe(getViewLifecycleOwner(),galaxyPicture -> {
            Glide.with(HomeFragment.this).load(galaxyPicture.getUrl()).centerCrop().into(imageView);
            displayDate();
        });
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

    private void loadImage(Date data){
        homeViewModel.getPicture(data);
    }

}
