package vn.edu.stu.datvexemphim.ViewMatch;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.edu.stu.datvexemphim.Models.Accounts;
import vn.edu.stu.datvexemphim.R;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.AccountViewHolder> {
    private List<Accounts> movieList;
    private Context context;


    public static class AccountViewHolder extends RecyclerView.ViewHolder{
        TextView tv_acId,tv_acUserName,tv_acPassword,tv_acRole
        public AccountViewHolder(View itemView){
            super(itemView);
            tv_acId = itemView.findViewById(R.id.item_TaiKhoan_ma);
            tv_acUserName = itemView.findViewById(R.id.item_TaiKhoan_tenTK);
            tv_acPassword = itemView.findViewById(R.id.item_TaiKhoan_matKhau);
            tv_acRole = itemView.findViewById(R.id.item_TaiKhoan_vaiTro);
        }
    }
}
