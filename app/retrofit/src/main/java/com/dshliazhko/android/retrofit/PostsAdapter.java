package com.dshliazhko.android.retrofit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private List<Lists> posts;
    private Context context;


    public PostsAdapter(List<Lists> posts) {
        this.posts = posts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);

        context = parent.getContext();
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Lists post = posts.get(position);



        holder.bind(posts.get(position), position);

    }

    @Override
    public int getItemCount() {
        if (posts == null)
            return 0;
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView date;
        TextView temp;
        TextView deskr;
        ImageView imageView;


        public ViewHolder(View itemView) {
            super(itemView);
            date = (TextView) itemView.findViewById(R.id.viewdate);
            temp = (TextView) itemView.findViewById(R.id.viewtemp);
            deskr = itemView.findViewById(R.id.viewdeskr);

            imageView = itemView.findViewById(R.id.viewImagine);
           // itemView.findViewById(R.id.viewMainPost).setBackground();

        }

        @SuppressLint("ResourceAsColor")
        public void bind(Lists lists, int pos) {
            final double constat = -273.15;

            temp.setText(lists.getMain().getTemp().toString());
            date.setText(lists.getDt_txt());

            deskr.setText(lists.getWeather().get(0).description);
            String s = lists.getWeather().get(0).getIcon();
            Log.d("Dima ", " = " + s);
            String path = "https://openweathermap.org/img/wn/" + s + "@2x.png";
            Picasso.with(context)
                    .load(path)
                    .placeholder(R.color.purple_200)
                    .fit()
                    .into(imageView);

        }
    }
}