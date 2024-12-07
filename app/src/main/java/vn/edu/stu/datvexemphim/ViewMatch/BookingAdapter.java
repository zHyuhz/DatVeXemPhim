package vn.edu.stu.datvexemphim.ViewMatch;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import vn.edu.stu.datvexemphim.Models.Accounts;
import vn.edu.stu.datvexemphim.Models.Booking;
import vn.edu.stu.datvexemphim.R;

public class BookingAdapter extends ArrayAdapter<Booking> {
    private List<Booking> bookingList;
    private Activity context;
    private int resource;
    public BookingAdapter(@NonNull Activity context, int resource, @NonNull List<Booking> objects) {
        super(context, resource, objects);
        this.bookingList = objects;
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View item = inflater.inflate(this.resource, null);

        TextView tv_maVe,tv_ngay,tv_gio,tv_gia;
        tv_maVe =item.findViewById(R.id.item_ve_maVe);
        tv_ngay =item.findViewById(R.id.item_ve_ngay);
        tv_gio =item.findViewById(R.id.item_ve_gio);
        tv_gia =item.findViewById(R.id.item_ve_gia);

        Booking ac = this.bookingList.get(position);
        tv_maVe.setText(ac.getBooking_id()+"");
        tv_ngay.setText(ac.getDate());
        tv_gio.setText(ac.getHour());
        tv_gia.setText(ac.getPrice()+"");

        return item;
    }
}
