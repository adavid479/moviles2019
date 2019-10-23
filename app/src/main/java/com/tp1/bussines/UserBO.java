package com.tp1.bussines;

import android.content.Context;

import com.tp1.model.User;
import com.tp1.persist.DatabaseHelper;

public class UserBO {

    DatabaseHelper databaseHelper;

    public UserBO(Context context){
        databaseHelper = new DatabaseHelper(context);
    }

    public Integer checkUser(User user){
        return databaseHelper.checkUser(user);
    }

}
