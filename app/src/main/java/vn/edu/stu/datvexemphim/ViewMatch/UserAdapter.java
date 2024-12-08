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
import vn.edu.stu.datvexemphim.Models.User;
import vn.edu.stu.datvexemphim.R;

public class UserAdapter extends ArrayAdapter<User> {
    private List<User> userList;
    private Activity context;
    private int resource;


    public UserAdapter(@NonNull Activity context, int resource, @NonNull List<User> objects) {
        super(context, resource, objects);
        this.userList = objects;
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View item = inflater.inflate(this.resource, null);

        TextView tv_hoTen,tv_sdt;
        tv_hoTen =item.findViewById(R.id.item_nguoiDung_hoTen);
        tv_sdt =item.findViewById(R.id.item_nguoiDung_sdt);


        User ac = this.userList.get(position);
        tv_hoTen.setText(ac.getHoTen());
        tv_sdt.setText(ac.getSdt());


        return item;
    }
}
