package net.coders.roomexample.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;

import com.android.databinding.library.baseAdapters.BR;

@Entity(tableName = "User")
public class User extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    private int Id;

    @ColumnInfo(name = "user_name")
    private String userName;

    @ColumnInfo(name = "user_pass")
    private String passWord;

    @ColumnInfo(name = "age")
    private String age;

    @Ignore
    public User(String userName, String passWord, String age) {
        this.userName = userName;
        this.passWord = passWord;
        this.age = age;
    }

    public User() {

    }

    @Bindable
    public int getId() {
        return Id;
    }

    @NonNull
    @Override
    public String toString() {
        return "User{" +
                "Id=" + Id +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", age='" + age +
                '}';
    }

    public void setId(int id) {
        Id = id;
    }

    @Bindable
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
        notifyPropertyChanged(BR.userName);
    }

    @Bindable
    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
        notifyPropertyChanged(BR.passWord);
    }

    @Bindable
    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
        notifyPropertyChanged(BR.age);
    }
}
