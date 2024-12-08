package vn.edu.stu.datvexemphim.DTO.Response;

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
public class ScheduleResponse {

    private int schedule_id;

    private MovieResponse movies;

    private RoomResponse room;

    private String scheduleDate;

    private String scheduleStart;

    private String scheduleEnd;

    private double price;
}
