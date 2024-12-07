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
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.edu.stu.datvexemphim.Models.Accounts;
import vn.edu.stu.datvexemphim.R;

public class AccountAdapter extends ArrayAdapter<Accounts> {
    private List<Accounts> accountsList;
    private Activity context;
    private int resource;


    public AccountAdapter(@NonNull Activity context, int resource, @NonNull List<Accounts> objects) {
        super(context, resource, objects);
        this.accountsList = objects;
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View item = inflater.inflate(this.resource, null);

        TextView tv_acMa,tv_acTaiKhoan,tv_acMatKhau,tv_acVaiTro;
        tv_acMa =item.findViewById(R.id.item_taiKhoan_ma);
        tv_acTaiKhoan =item.findViewById(R.id.item_taiKhoan_TK);
        tv_acVaiTro =item.findViewById(R.id.item_taiKhoan_vaiTro);

        Accounts ac = this.accountsList.get(position);
        tv_acMa.setText(ac.getAc_id()+"");
        tv_acTaiKhoan.setText(ac.getAc_userName());
        tv_acVaiTro.setText(ac.getAc_role());

        return item;
    }
}
