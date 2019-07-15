package com.mobileapps.umbrella.ui;

import android.util.Log;

import com.mobileapps.umbrella.models.CurrentWeather;
import com.mobileapps.umbrella.network.NetworkClient;
import com.mobileapps.umbrella.network.NetworkInterface;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter implements MainPresenterInterface
{

    MainViewInterface mvi;
    private String TAG = "MainPresenter";
    private String zipCode;


    public MainPresenter(MainViewInterface mvi,String zipCode)
    {
        this.mvi = mvi;
        this.zipCode = zipCode;
    }


    @Override
    public void getCurrentWeather() {
        getObservable().subscribeWith(getObserver());
    }


    public Observable<CurrentWeather> getObservable(){
        return NetworkClient.getRetrofit().create(NetworkInterface.class)
                .getCurrentWeather(zipCode,"5bf6c7f8208c9d8b5694f3b2a48f3f0a")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<CurrentWeather> getObserver()
    {
        return new DisposableObserver<CurrentWeather>() {
            @Override
            public void onNext(CurrentWeather currentWeather) {
                mvi.displayWeather( currentWeather);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG,"Error"+e);
                e.printStackTrace();
                mvi.displayError("Error fetching Movie Data");
            }

            @Override
            public void onComplete() {
                Log.d(TAG,"Completed");
            }
        };
    }


}
