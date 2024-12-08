package vn.edu.stu.datvexemphim.ViewMatch;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import vn.edu.stu.datvexemphim.CustomEvent.IDatePicker;
import vn.edu.stu.datvexemphim.CustomEvent.IDatePicker2;
import vn.edu.stu.datvexemphim.Models.Schedule;
import vn.edu.stu.datvexemphim.R;

public class DatePickerAdapter2 extends RecyclerView.Adapter<DatePickerAdapter2.DateViewHolder> {
    private List<Schedule> scheduleList;
    private int selectedPosition = -1;
    private IDatePicker2 listener;

    public DatePickerAdapter2(List<Schedule> dateList, IDatePicker2 listener) {
        this.scheduleList = dateList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public DatePickerAdapter2.DateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ngaythang, parent, false);
        return new DateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DatePickerAdapter2.DateViewHolder holder, int position) {
        Schedule schedule = scheduleList.get(position);

// Sử dụng SimpleDateFormat để định dạng ngày và tháng
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd", Locale.getDefault());
        SimpleDateFormat monthFormat = new SimpleDateFormat("MMM", Locale.getDefault());

// Lấy ngày và tháng từ calendar
        int day = schedule.getLocalDate().get(Calendar.DAY_OF_MONTH);
        int month = schedule.getLocalDate().get(Calendar.MONTH); // Tháng bắt đầu từ 0, nhớ điều chỉnh nếu cần

// Định dạng và hiển thị ngày và tháng
        holder.tvDay.setText(String.valueOf(day));  // Đặt ngày
        holder.tvMonth.setText(monthFormat.format(schedule.getLocalDate().getTime()));  // Đặt tháng
        // Highlight ngày được chọn
        if (selectedPosition == position) {
            holder.lnItem.setBackgroundColor(holder.itemView.getContext().getColor(R.color.Linear_Selected));
            holder.tvMonth.setTextColor(holder.itemView.getContext().getColor(R.color.month_selected));
            holder.tvDay.setTextColor(holder.itemView.getContext().getColor(R.color.day_selected));
        } else {
            holder.lnItem.setBackgroundColor(holder.itemView.getContext().getColor(R.color.Linear_UnSelected));
            holder.tvMonth.setTextColor(holder.itemView.getContext().getColor(R.color.month_UnSelected));
            holder.tvDay.setTextColor(holder.itemView.getContext().getColor(R.color.day_UnSelected));
        }

        holder.itemView.setOnClickListener(v -> {
            selectedPosition = position;
            notifyDataSetChanged();
            listener.onDateClick(schedule);
        });
    }

    @Override
    public int getItemCount() {
        return scheduleList.size();
    }


    public static class DateViewHolder extends RecyclerView.ViewHolder{
        TextView tvDay, tvMonth;
        LinearLayout lnItem;
        public DateViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDay = itemView.findViewById(R.id.tvDay);
            tvMonth = itemView.findViewById(R.id.tvMonth);
            lnItem = itemView.findViewById(R.id.ln_item_ngaythang);
        }
    }
}
