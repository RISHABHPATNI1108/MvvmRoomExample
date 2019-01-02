package net.coders.roomexample.database;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.content.Context;

import net.coders.roomexample.DatabaseCallback;
import net.coders.roomexample.model.User;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class DataRepository {
    private static final String DB_NAME = "database-name";
    private AppDatabase db;

    public DataRepository(Context context) {
        db = Room.databaseBuilder(context, AppDatabase.class, DB_NAME).build();
    }

    @SuppressLint("CheckResult")
    public void getUsers(final DatabaseCallback databaseCallback) {
        db.userDao().getAll().subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<User>>() {
            @Override
            public void accept(List<User> users) {
                databaseCallback.onUsersLoaded(users);
            }
        });
    }

    public void addUser(final DatabaseCallback databaseCallback, final String userName,
                        final String password , final String age) {
        Completable.fromAction(new Action() {
            @Override
            public void run() {
                User user = new User(userName, password , age);
                db.userDao().insertAll(user);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {
                databaseCallback.onUserAdded();
            }

            @Override
            public void onError(Throwable e) {
                databaseCallback.onDataNotAvailable();
            }
        });
    }

    public void deleteUser(final DatabaseCallback databaseCallback, final User user) {
        Completable.fromAction(new Action() {
            @Override
            public void run() {
                db.userDao().delete(user);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {
                databaseCallback.onUserDeleted();
            }

            @Override
            public void onError(Throwable e) {
                databaseCallback.onDataNotAvailable();
            }
        });
    }


    public void updateUser(final DatabaseCallback databaseCallback, final User user) {
        Completable.fromAction(new Action() {
            @Override
            public void run() {
                db.userDao().updateUsers(user);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {
                databaseCallback.onUserUpdated();
            }

            @Override
            public void onError(Throwable e) {
                databaseCallback.onDataNotAvailable();
            }
        });
    }
}
