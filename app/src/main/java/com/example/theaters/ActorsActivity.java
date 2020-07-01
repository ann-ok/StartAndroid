package com.example.theaters;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.theaters.adapter.ActorAdapter;
import com.example.theaters.models.Actor;
import com.example.theaters.models.Theater;

import java.util.ArrayList;

public class ActorsActivity extends AppCompatActivity {

    Theater theater;

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

        setTitle("Труппа");
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
        ActorAdapter actorAdapter = new ActorAdapter(actors);
        RecyclerView actorRecyclerView = findViewById(R.id.actors_recycler_view);
        actorRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        actorRecyclerView.setAdapter(actorAdapter);
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
}