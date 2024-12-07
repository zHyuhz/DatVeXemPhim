//package vn.edu.stu.datvexemphim.testUI;
//
//import static androidx.test.espresso.Espresso.onView;
//import static androidx.test.espresso.matcher.ViewMatchers.withId;
//
//import static java.util.regex.Pattern.matches;
//
//import android.app.Activity;
//import static androidx.test.espresso.matcher.ViewMatchers.withText;
//import static androidx.test.espresso.assertion.ViewAssertions.matches;
//
//import androidx.test.espresso.action.ViewActions;
//import androidx.test.espresso.contrib.RecyclerViewActions;
//import androidx.test.ext.junit.rules.ActivityScenarioRule;
//import androidx.test.ext.junit.runners.AndroidJUnit4;
//
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import vn.edu.stu.datvexemphim.Activity.trangChu_MainActivity;
//import vn.edu.stu.datvexemphim.Models.Movies;
//import vn.edu.stu.datvexemphim.Models.Schedule;
//import vn.edu.stu.datvexemphim.Models.TimeSlot;
//import vn.edu.stu.datvexemphim.R;
//
//@RunWith(AndroidJUnit4.class)
//public class MovieDetailsAndScheduleTest {
//
//    // Quy tắc để khởi tạo và chạy kiểm thử trên Activity
//    @Rule
//    public ActivityScenarioRule<trangChu_MainActivity> activityScenarioRule = new ActivityScenarioRule<>(trangChu_MainActivity.class);
//
//    // Kiểm thử chọn bộ phim, hiển thị chi tiết phim và kiểm tra lịch chiếu
//    @Test
//    public void testSelectMovieAndShowDetailsAndSchedule() {
//        // Tạo đối tượng Movie giả lập
//        Movies movie = new Movies(1, "Avatar", "A sci-fi epic", "Sci-Fi, Action", "2009-12-18", 162, "avatar_poster.jpg", scheduleList);
//
//        // Tạo lịch chiếu và giờ chiếu cho bộ phim này
//        Schedule schedule = new Schedule(1, movie, getListTimeSlot());
//        movie.setGetScheduleList(getListSchedule(schedule));
//
//
//        // Giả lập việc nhấn vào bộ phim và chuyển đến màn hình chi tiết phim
//        onView(withId(R.id.TC_RCV_dangChieu)) // Giả sử đây là RecyclerView chứa các bộ phim
//                .perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));
//
//        // Kiểm tra các thành phần chi tiết của bộ phim trên màn hình chi tiết
//        onView(withId(R.id.tv_Item_movieName))
//                .check(matches(withText(movie.getMovie_name())));  // Kiểm tra tên bộ phim
//        onView(withId(R.id.tv_theLoai))
//                .check(matches(withText(movie.getMovie_genres())));  // Kiểm tra thể loại phim
//        onView(withId(R.id.tv_Item_length))
//                .check(matches(withText(String.valueOf(movie.getMovie_length()) + " min")));  // Kiểm tra độ dài phim
//        onView(withId(R.id.tv_Item_date))
//                .check(matches(withText(movie.get())));  // Kiểm tra ngày phát hành phim
//
//        // Kiểm tra nếu lịch chiếu được hiển thị
//        onView(withId(R.id.RCV_date)) // Giả sử đây là RecyclerView hiển thị lịch chiếu
//                .perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));  // Nhấn vào lịch chiếu đầu tiên
//
//        // Kiểm tra giờ chiếu
//        onView(withId(R.id.RCV_hour)) // Giả sử đây là TextView hiển thị giờ chiếu
//                .check(matches(withText("10:00 AM"))); // Kiểm tra giờ chiếu
//    }
//
//    // Tạo danh sách lịch chiếu
//    private List<Schedule> getListSchedule(Schedule schedule) {
//        List<Schedule> schedules = new ArrayList<>();
//        schedules.add(schedule);
//        return schedules;
//    }
//
//    // Tạo danh sách thời gian chiếu (TimeSlot)
//    private List<TimeSlot> getListTimeSlot() {
//        List<TimeSlot> timeSlots = new ArrayList<>();
//        timeSlots.add(new TimeSlot(1, "10", "00")); // giờ: phút
//        timeSlots.add(new TimeSlot(2, "12", "00"));
//        timeSlots.add(new TimeSlot(3, "02", "00"));
//        return timeSlots;
//    }
//}