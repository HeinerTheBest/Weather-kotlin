package com.mobileapps.umbrella.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mobileapps.umbrella.R;
import com.mobileapps.umbrella.models.CurrentWeather;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainViewInterface
{

    @BindView(R.id.tvTitle)
    TextView tvTitle;

    @BindView(R.id.tvWeekDay)
    TextView tvWeekDay;

    @BindView(R.id.etZipCode)
    EditText etZipCode;


    private String TAG = "MainActivity";
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);


    }

    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.tvSearch:
                setupMVP();
                setupMVP();
                getWeather();
                break;
        }
    }

    private void setupMVP() {
        mainPresenter = new MainPresenter(this,etZipCode.getText().toString());
    }

    private void getWeather() {
        mainPresenter.getCurrentWeather();
    }

    @Override
    public void displayWeather(CurrentWeather currentWeather) {
        if(currentWeather!=null) {
            String title = currentWeather.getName() + ","+etZipCode.getText().toString();
            tvTitle.setText(title);


        }else{
            Log.d(TAG,"Movies response null");
        }
    }

    @Override
    public void displayError(String s)
    {
        Toast.makeText(this, "Try to insert the zip code again", Toast.LENGTH_SHORT).show();
        //todo add clean the edittext

    }
}
