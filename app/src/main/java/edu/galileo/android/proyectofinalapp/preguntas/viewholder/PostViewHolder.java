package edu.galileo.android.proyectofinalapp.preguntas.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import edu.galileo.android.proyectofinalapp.R;
import edu.galileo.android.proyectofinalapp.models.Preguntas;

public class PostViewHolder extends RecyclerView.ViewHolder {

    public TextView titleView;
    public TextView authorView;
    public ImageView starView;
    public TextView numStarsView;
    public TextView bodyView;

    public PostViewHolder(View itemView) {
        super(itemView);

        titleView = (TextView) itemView.findViewById(R.id.post_title);
        authorView = (TextView) itemView.findViewById(R.id.post_author);
        starView = (ImageView) itemView.findViewById(R.id.star);
        numStarsView = (TextView) itemView.findViewById(R.id.post_num_stars);
        bodyView = (TextView) itemView.findViewById(R.id.post_body);
    }

    public void bindToPost(Preguntas preguntas, View.OnClickListener starClickListener) {
        titleView.setText(preguntas.title);
        authorView.setText(preguntas.author);
        numStarsView.setText(String.valueOf(preguntas.starCount));
        bodyView.setText(preguntas.body);

        starView.setOnClickListener(starClickListener);
    }
}
