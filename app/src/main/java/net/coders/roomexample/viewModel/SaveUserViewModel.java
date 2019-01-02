package net.coders.roomexample.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import net.coders.roomexample.DatabaseCallback;
import net.coders.roomexample.RoomExampleAppl;
import net.coders.roomexample.database.DataRepository;
import net.coders.roomexample.model.User;

import java.util.List;

public class SaveUserViewModel extends ViewModel implements DatabaseCallback {
    public User user;
    private DataRepository dataRepository;
    private MutableLiveData<List<User>> users;

    public SaveUserViewModel() {
        this.user = new User();
        users = new MutableLiveData<>();
        if (RoomExampleAppl.getContext() != null)
            dataRepository = new DataRepository(RoomExampleAppl.getContext());
        getUsers();
    }

    public void saveData() {
        Log.d("user" , user.toString());
        dataRepository.addUser(this, user.getUserName() , user.getPassWord() , user.getAge());
    }

    private void getUsers() {
        dataRepository.getUsers(this);
    }

    @Override
    public void onUsersLoaded(List<User> users) {
        this.users.postValue(users);
    }

    public LiveData<List<User>> getAllUser() {
        return users;
    }

    @Override
    public void onUserDeleted() {
        getUsers();
    }

    @Override
    public void onUserAdded() {
        getUsers();
    }

    @Override
    public void onDataNotAvailable() {

    }

    @Override
    public void onUserUpdated() {
        getUsers();
    }
}
