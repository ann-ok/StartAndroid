package com.example.theaters.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theaters.R;
import com.example.theaters.models.Actor;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ActorAdapter extends RecyclerView.Adapter<ActorAdapter.ActorViewHolder> {
    private ArrayList<Actor> actorList;

    public ActorAdapter(ArrayList<Actor> actorList) {
        this.actorList = actorList;
    }

    @NonNull
    @Override
    public ActorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.actor_table_item, parent, false);
        return new ActorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActorViewHolder holder, int position) {
        holder.bind(actorList.get(position));
    }

    @Override
    public int getItemCount() {
        return actorList.size();
    }

    class ActorViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name;

        public ActorViewHolder(@NonNull View itemView) {
            super(itemView);
            this.image = itemView.findViewById(R.id.table_actor_image_view);
            this.name = itemView.findViewById(R.id.table_actor_name_text_view);
        }

        public void bind(Actor actor) {
            name.setText(actor.getName());

            // обработка изображения
            String actorPhotoUrl =  actor.getImgUrl();
            Picasso.with(itemView.getContext())
                    .load(actorPhotoUrl)
                    .fit()
                    .centerCrop()
                    .into(image);

            image.setVisibility(actorPhotoUrl != null ? View.VISIBLE : View.GONE);
        }
    }
}
