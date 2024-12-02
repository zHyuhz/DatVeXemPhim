package vn.edu.stu.datvexemphim.ViewMatch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import vn.edu.stu.datvexemphim.DTO.Response.MovieResponse;
import vn.edu.stu.datvexemphim.R;
import vn.edu.stu.datvexemphim.Retrofit.RetrofitSer;


public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.FilmViewHolder> {
    public List<MovieResponse> movieList;

    private Context context;
    private int resoure;
    public void setSearchList(List<MovieResponse> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }
    public FilmAdapter(Context context, int resoure, List<MovieResponse> movieList) {
        this.context = context;
        this.movieList = movieList;
        this.resoure = resoure;
    }

    @NonNull
    @Override
    public FilmViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(resoure, parent, false);
        return new FilmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmViewHolder holder, int position) {
        MovieResponse movie = movieList.get(position);
        holder.tv_movieName.setText(movie.getMovieName());  // Đặt tên phim
        holder.tv_movieLength.setText(String.valueOf(movie.getMovie_length()));  // Đặt độ dài
        holder.tv_movieGenres.setText(movie.getMovie_genres());  // Đặt thể loại
        String url = RetrofitSer.BASE_URL + movie.getMovie_poster();
        Glide.with(context)
                .load(url)
                .placeholder(R.drawable.ic_launcher_background) // Hình ảnh hiển thị trong khi tải
                .error(R.drawable.ic_launcher_foreground)             // Hình ảnh hiển thị khi lỗi
                .into(holder.movieImageView);

        // Load ảnh nếu có (dùng thư viện như Glide hoặc Picasso)
        // Glide.with(context).load(movie.getImageUrl()).into(holder.movieImageView);
        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList != null ? movieList.size() : 0;
    }

    public static class FilmViewHolder extends RecyclerView.ViewHolder {
        TextView tv_movieName, tv_movieLength, tv_movieGenres;
        ImageView movieImageView;

        public FilmViewHolder(View itemView) {
            super(itemView);
            tv_movieName = itemView.findViewById(R.id.tv_Item_movieName);
            tv_movieLength = itemView.findViewById(R.id.tv_Item_length);
            tv_movieGenres = itemView.findViewById(R.id.tv_Item_genres);
            movieImageView = itemView.findViewById(R.id.img_Item_movieImage);
        }
    }
}
