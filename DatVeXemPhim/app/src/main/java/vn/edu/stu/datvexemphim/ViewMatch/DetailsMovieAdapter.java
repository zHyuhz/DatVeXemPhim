package vn.edu.stu.datvexemphim.ViewMatch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.edu.stu.datvexemphim.DTO.Response.MovieResponse;
import vn.edu.stu.datvexemphim.R;

public class DetailsMovieAdapter extends RecyclerView.Adapter<DetailsMovieAdapter.DetailsMovieViewHolder> {
    public MovieResponse movieResponse;

    private Context context;
    private int resoure;


    public DetailsMovieAdapter(Context context, int resoure, MovieResponse movieResponse) {
        this.context = context;
        this.movieResponse = movieResponse;
        this.resoure = resoure;
    }

    @NonNull
    @Override
    public DetailsMovieAdapter.DetailsMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(resoure, parent, false);
        return new DetailsMovieAdapter.DetailsMovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailsMovieAdapter.DetailsMovieViewHolder holder, int position) {
            holder.tv_Item_movieName.setText(movieResponse.getMovieName());
            holder.tv_Item_length.setText(movieResponse.getMovie_length()+"m");
            holder.tv_Item_date.setText(movieResponse.getMovie_release());
    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public static class DetailsMovieViewHolder extends RecyclerView.ViewHolder {

        TextView tv_Item_movieName,tv_Item_length,tv_Item_date;
        public DetailsMovieViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_Item_movieName = itemView.findViewById(R.id.tv_Item_movieName);
            tv_Item_length = itemView.findViewById(R.id.tv_Item_length);
            tv_Item_date = itemView.findViewById(R.id.tv_Item_date);
        }
    }
}
