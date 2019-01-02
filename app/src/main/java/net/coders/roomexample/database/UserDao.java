package net.coders.roomexample.database;

// Room Imports
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import net.coders.roomexample.model.User;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;

@Dao
public interface UserDao {
    @Query("SELECT * FROM User")
    Maybe<List<User>> getAll();

    @Query("SELECT * FROM User WHERE id IN (:userIds)")
    Flowable<List<User>> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM User WHERE user_name LIKE :first")
    User findByName(String first);

    @Query("SELECT * FROM User where id = :id")
    Maybe<User> findById(int id);


    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);

    @Update
    void updateUsers(User... users);
}
