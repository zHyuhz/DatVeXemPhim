package vn.edu.stu.datvexemphim.DTO.Response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieResponse implements Serializable {

    private int movie_id;

    private String movieName;

    private String movie_description;

    private String movie_genres;

    private String movie_release;

    private int movie_length;

    private String movie_poster;
}