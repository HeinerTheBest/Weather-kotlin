package com.mobileapps.umbrella.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.mobileapps.umbrella.R;
import com.mobileapps.umbrella.adapters.WeathersAdapter;
import com.mobileapps.umbrella.models.watherofweek.WeatherOfTheWeek;
import com.mobileapps.umbrella.utilities.SharedPreferences;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsOfTheWeekActivity extends AppCompatActivity implements DetailViewInterface {


    @BindView(R.id.rvWeathers)
    RecyclerView rvWeather;

    private String TAG = "DetailsOfTheWeekActivity";
    RecyclerView.Adapter adapter;
    DetailPresenter detailPresenter;
    SharedPreferences sharedPreferences;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_of_the_week);

        ButterKnife.bind(this);
        sharedPreferences = new SharedPreferences();
        context = this;
        setupMVP();
        setupViews();
        getWeatherList();
    }

    private void getWeatherList()
    {
        detailPresenter.getWeatherOfTheWeek();
    }


    private void setupMVP() {
        detailPresenter = new DetailPresenter(this,sharedPreferences.getZipCode(this));
    }

    private void setupViews(){
        rvWeather.setLayoutManager(new LinearLayoutManager(this));
    }




    @Override
    public void displayWeather(WeatherOfTheWeek weatherOfTheWeek)
    {
        if(weatherOfTheWeek!=null) {
            adapter = new WeathersAdapter(weatherOfTheWeek.getList(), context);
            rvWeather.setAdapter(adapter);
        }else{
            Log.d(TAG,"Weather response null");
        }

    }

    @Override
    public void displayError(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }
}
