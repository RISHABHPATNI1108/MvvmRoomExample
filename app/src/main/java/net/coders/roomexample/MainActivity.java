package net.coders.roomexample;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import net.coders.roomexample.databinding.ActivityMainBinding;
import net.coders.roomexample.model.User;
import net.coders.roomexample.viewModel.SaveUserViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SaveUserViewModel userViewModel;
    ArrayList<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this , R.layout.activity_main);
        userViewModel = ViewModelProviders.of(this).get(SaveUserViewModel.class);
        activityMainBinding.setVm(userViewModel);
        users = new ArrayList<>();
        final UserRecyclerViewAdapter userRecyclerViewAdapter = new UserRecyclerViewAdapter(users,getApplicationContext());
        activityMainBinding.rvUsers.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        activityMainBinding.rvUsers.setAdapter(userRecyclerViewAdapter);
        activityMainBinding.rvUsers.setHasFixedSize(true);

        userViewModel.getAllUser().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> usersList) {
                users.clear();
                if (usersList != null) {
                    users.addAll(usersList);
                    userRecyclerViewAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}