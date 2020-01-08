package com.ardroid.petroom.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ardroid.petroom.data.APIProvider;
import com.ardroid.petroom.data.GalaxyPicture;
import com.ardroid.petroom.data.NasaAPI;
import com.ardroid.petroom.ui.basics.BasicViewModel;

import java.util.Date;
import java.util.function.Consumer;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomeViewModel extends BasicViewModel {
    private NasaAPI nasaApi = APIProvider.getNasaAPI();
    MutableLiveData<GalaxyPicture> galaxyPictureLiveData = new MutableLiveData<>();

    public void getPicture(Date date) {
        compositeDisposable.add(
        nasaApi.getGalaxyPicture(APIProvider.API_KEY, APIProvider.SDF.format(date))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(galaxyPicture -> galaxyPictureLiveData.setValue(galaxyPicture)
                        , Throwable::printStackTrace));
    }
}