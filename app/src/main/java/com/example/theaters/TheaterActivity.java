package com.example.theaters;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.theaters.models.Theater;

public class TheaterActivity extends AppCompatActivity {

    Theater theater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // получение входных аргументов (у нас это театр)
        Bundle arg = getIntent().getExtras();
        if (arg != null) {
            if (theater == null) {
                theater = (Theater) arg.getSerializable(Theater.class.getSimpleName());
            }
        }

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
    }
}