package vn.edu.stu.datvexemphim.CustomEvent;

import java.util.Calendar;

import vn.edu.stu.datvexemphim.DTO.Response.ScheduleDateTimeResponse;
import vn.edu.stu.datvexemphim.Models.Schedule;

public interface IDatePicker2 {
    void onDateClick(ScheduleDateTimeResponse schedule);
}
