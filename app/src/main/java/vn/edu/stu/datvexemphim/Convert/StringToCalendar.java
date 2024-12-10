package vn.edu.stu.datvexemphim.Convert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class StringToCalendar {
    public static void main(String[] args) {
        String dateString = "2024-12-08"; // Chuỗi ngày tháng

        // Định dạng chuỗi ngày tháng
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // Chuyển đổi từ String sang Date
            Date date = dateFormat.parse(dateString);

            // Chuyển đổi từ Date sang Calendar
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            // In ra thông tin Calendar
            System.out.println("Year: " + calendar.get(Calendar.YEAR));
            System.out.println("Month: " + (calendar.get(Calendar.MONTH) + 1)); // Lưu ý: MONTH bắt đầu từ 0
            System.out.println("Day: " + calendar.get(Calendar.DAY_OF_MONTH));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
