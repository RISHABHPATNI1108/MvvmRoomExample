package net.coders.roomexample;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.coders.roomexample.databinding.RowUsersBinding;
import net.coders.roomexample.model.User;

import java.util.ArrayList;

public class UserRecyclerViewAdapter extends RecyclerView.Adapter<UserRecyclerViewAdapter.UserViewHolder> {
    private ArrayList<User> users;
    RowUsersBinding viewDataBinding;
    Context context;

    public UserRecyclerViewAdapter(ArrayList<User> users , Context context) {
        this.users = users;
        this.context = context;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        viewDataBinding = DataBindingUtil
                .inflate(layoutInflater , R.layout.row_users , viewGroup , false);
        View view = viewDataBinding.getRoot();
        return new UserViewHolder(viewDataBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int i) {
        userViewHolder.dataBinding.setUser(users.get(i));
    }

    @Override
    public int getItemCount() {
        return users != null ? users.size() : 0;
    }

    class UserViewHolder extends RecyclerView.ViewHolder {
        RowUsersBinding dataBinding;
        UserViewHolder(RowUsersBinding itemView) {
            super(itemView.getRoot());
            dataBinding = itemView;
        }
    }
}
