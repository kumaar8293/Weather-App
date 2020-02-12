package com.mukesh.weatherapp.view.home;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mukesh.weatherapp.R;
import com.mukesh.weatherapp.datamodel.Weather;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt;

public class HomeActivity extends AppCompatActivity implements HomeAdapter.OnItemClickListener {

    private ImageView errorImage;
    private TextView textWeatherType, city, tv_celsius, tv_humidityPercent, tv_windPercent, tv_cloudlinesPercent, tv_pressurePercent;
    private HomeAdapter homeAdapter;
    FloatingActionButton fabAddCity;
    private HomeViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        initializeViewHolder();

        showFabPrompt();
    }

    private ImageView imageWeatherType;

    private void initView() {
        imageWeatherType = findViewById(R.id.iv_imageWeatherType);
        textWeatherType = findViewById(R.id.tv_textWeatherType);
        city = findViewById(R.id.tv_city);
        tv_celsius = findViewById(R.id.tv_celsius);
        tv_humidityPercent = findViewById(R.id.tv_humidityPercent);
        tv_windPercent = findViewById(R.id.tv_windPercent);
        tv_cloudlinesPercent = findViewById(R.id.tv_cloudlinesPercent);
        tv_pressurePercent = findViewById(R.id.tv_pressurePercent);
        errorImage = findViewById(R.id.errorImage);
        RecyclerView recyclerView = findViewById(R.id.rv_weaterDetail);
        recyclerView.setHasFixedSize(true);
        homeAdapter = new HomeAdapter(this, this);
        recyclerView.setAdapter(homeAdapter);
        fabAddCity = findViewById(R.id.fabAddCity);


        fabAddCity.setOnClickListener(view -> {
            showAlertAddCity("Add city", "Type the city you want to add");
        });


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    // Scroll Down
                    if (fabAddCity.isShown()) {
                        fabAddCity.hide();
                    }
                } else if (dy < 0) {
                    // Scroll Up
                    if (!fabAddCity.isShown()) {
                        fabAddCity.show();
                    }
                }
            }
        });

    }

    public void showAlertAddCity(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (title != null) builder.setTitle(title);
        if (message != null) builder.setMessage(message);
        final View view = LayoutInflater.from(this).inflate(R.layout.dialog_add_city, null);
        builder.setView(view);
        final TextView editTextAddCityName = view.findViewById(R.id.editTextAddCityName);

        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                viewModel.searchWeatherByCity(editTextAddCityName.getText().toString().trim());
                imm.toggleSoftInput(InputMethodManager.HIDE_NOT_ALWAYS, 0);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                imm.toggleSoftInput(InputMethodManager.HIDE_NOT_ALWAYS, 0);
            }
        });
        builder.create().show();
    }

    private void updateHeaderData(Weather selectedWeather) {

        if (selectedWeather != null) {

            // imageWeatherType
            textWeatherType.setText(selectedWeather.getCurrent().getWeatherDescriptions().get(0));
            city.setText(selectedWeather.getLocation().getName());
            tv_celsius.setText(("" + selectedWeather.getCurrent().getTemperature() + (char) 0x00B0));

            tv_humidityPercent.setText(("" + selectedWeather.getCurrent().getHumidity()));
            tv_windPercent.setText(("" + selectedWeather.getCurrent().getWindSpeed()));
            tv_cloudlinesPercent.setText(("" + selectedWeather.getCurrent().getCloudcover()));
            tv_pressurePercent.setText(("" + selectedWeather.getCurrent().getPressure()));

            Glide.with(this).load(selectedWeather.getCurrent()
                    .getWeatherIcons().get(0)).into(imageWeatherType);

        }
    }

    private void initializeViewHolder() {
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        viewModel.getCurrentWeather().observe(this, weathers -> {

            if (weathers == null || weathers.size() == 0) {
                errorImage.setVisibility(View.VISIBLE);

                return;
            } else errorImage.setVisibility(View.GONE);
            homeAdapter.setData(weathers);
            if (city.getText().toString().equalsIgnoreCase(""))
                updateHeaderData(weathers.get(0));
        });
        viewModel.getErrorMessageListener().observe(this, s -> {
            errorImage.setVisibility(View.VISIBLE);
            Toast.makeText(HomeActivity.this, s, Toast.LENGTH_LONG).show();
        });
        viewModel.searchWeatherByCity("Bengaluru");
    }

    private MaterialTapTargetPrompt mFabPrompt;
    private static final String TAG = "HomeActivity";

    public void showFabPrompt() {

        Log.d(TAG, "showFabPrompt: ");
        if (mFabPrompt != null) {
            return;
        }
        mFabPrompt = new MaterialTapTargetPrompt.Builder(HomeActivity.this)
                .setTarget(findViewById(R.id.fabAddCity))
                .setFocalPadding(R.dimen.dp40)
                .setPrimaryText("Add your first City")
                .setSecondaryText("Tap the add button and add your favorites cities to get weather updates")
                .setBackButtonDismissEnabled(true)
                .setAnimationInterpolator(new FastOutSlowInInterpolator())
                .setPromptStateChangeListener((prompt, state) -> {
                    if (state == MaterialTapTargetPrompt.STATE_FOCAL_PRESSED || state == MaterialTapTargetPrompt.STATE_DISMISSING) {
                        mFabPrompt = null;
                        //Do something such as storing a value so that this prompt is never shown again
                    }
                })
                .create();
        if (mFabPrompt != null)
            mFabPrompt.show();
    }

    @Override
    public void onClick(Weather weather) {
        updateHeaderData(weather);
    }
}
