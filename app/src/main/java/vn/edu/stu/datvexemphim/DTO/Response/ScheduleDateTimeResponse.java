package vn.edu.stu.datvexemphim.DTO.Response;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDateTimeResponse implements Serializable {
    private int schedule_id;

    private MovieResponse movies;

    private String scheduleDate;

    private String scheduleStart;
}
