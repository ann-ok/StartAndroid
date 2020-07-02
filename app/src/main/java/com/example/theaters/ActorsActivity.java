package com.example.theaters;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.theaters.adapter.ActorAdapter;
import com.example.theaters.models.Actor;
import com.example.theaters.models.Theater;
import com.example.theaters.service.JSoupActorService;
import com.example.theaters.service.JSoupService;

import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;

public class ActorsActivity extends AppCompatActivity {

    Theater theater;
    ArrayList<Actor> actors;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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

        setTitle(getResources().getString(R.string.actorsActivity_name));
        setContentView(R.layout.activity_actors);

        // вывод доп. информации
        TextView toolbarTextView = findViewById(R.id.toolbarTextView);
        toolbarTextView.setText(theater.getName());

        // получение списка актёров
        ArrayList<Actor> actors = new ArrayList<>();
        actors.add(new Actor("Инвестор", "https://pp.userapi.com/c851016/v851016703/16034/gRauIagzjGw.jpg"));
        actors.add(new Actor("Ваня", "https://s15.stc.all.kpcdn.net/share/i/12/10537134/inx960x640.jpg"));
        actors.add(new Actor("Аня", "https://pm1.narvii.com/6821/1055fedb52d33e3913d0b503e26cf8db9964f002v2_hq.jpg"));
        actors.add(new Actor("Алексей", "https://dev.by/storage/images/17/20/80/15/derived/afe7bcaee6374c4b056d21c08a36a003.jpg"));

        // вывод актёров
        ActorsTask task = new ActorsTask();
        task.execute();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    class ActorsTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                // получаем документ
                Document document = JSoupService.goToHref(theater.getTroupeUrl());

                // получаем актёров (парсим)
                if (getResources().getString(R.string.theater_tuz_name).equals(theater.getName())) {
                    actors = JSoupActorService.getTUZTheaterActorList(document, getResources().getString(R.string.theater_tuz_site));
                } else if (getResources().getString(R.string.theater_drama_name).equals(theater.getName())) {
                    actors = JSoupActorService.getDramaTheaterActorList(document, getResources().getString(R.string.theater_drama_site));
                } else if (getResources().getString(R.string.theater_dolls_name).equals(theater.getName())) {
                    actors = JSoupActorService.getDollsTheaterActorList(document, getResources().getString(R.string.theater_dolls_site));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            // вывод актёров
            if (actors.size() == 0) {
                TextView errorTextView = findViewById(R.id.no_actors);
                errorTextView.setVisibility(View.VISIBLE);
            } else {
                ActorAdapter actorAdapter = new ActorAdapter(actors);
                RecyclerView actorRecyclerView = findViewById(R.id.actors_recycler_view);
                actorRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
                actorRecyclerView.setAdapter(actorAdapter);
            }
        }
    }
}