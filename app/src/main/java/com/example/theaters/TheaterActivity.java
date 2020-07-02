package com.example.theaters;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.res.Configuration;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.theaters.models.Theater;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.Locale;

public class TheaterActivity extends AppCompatActivity {

    protected Theater theater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // получение входных аргументов
        Bundle arg = getIntent().getExtras();
        if (arg != null) {
            if (theater == null) {
                theater = (Theater) arg.getSerializable(Theater.class.getSimpleName());
            }
        }

        setTitle(getResources().getString(R.string.theaterActivity_name));
        setContentView(R.layout.activity_theater);

        // заполнение Activity полученными данными
        ImageView image = findViewById(R.id.theater_image_view_details);
        image.setImageResource(theater.getImageId());

        TextView name = findViewById(R.id.theater_name_text_view_details);
        name.setText(theater.getName());

        TextView vk = findViewById(R.id.theater_vk_text_view);
        vk.setText(theater.getVk());

        TextView site = findViewById(R.id.theater_web_text_view);
        site.setText(theater.getSite());

        TextView phone = findViewById(R.id.theater_phone_text_view);
        phone.setText(theater.getPhone());

        TextView map = findViewById(R.id.theater_map_text_view);
        map.setText(theater.getAddress());

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.mapView, new MapFragment(theater)).commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void TroupeButtonClick(View view) {
        // создаем объект для вызова новой Activity
        Intent intent = new Intent(getApplicationContext(), ActorsActivity.class);

        // передаем theater бущущей Activity
        intent.putExtra(Theater.class.getSimpleName(), theater);

        startActivity(intent);
    }
}