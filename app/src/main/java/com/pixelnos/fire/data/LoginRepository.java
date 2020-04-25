package com.pixelnos.fire.data;

import android.content.Context;
import android.util.Log;

import com.pixelnos.fire.data.model.LoggedInUser;

import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class LoginRepository {

    private static volatile LoginRepository instance;

    private LoginDataSource dataSource;

    // If user credentials will be cached in local storage, it is recommended it be encrypted
    // @see https://developer.android.com/training/articles/keystore
    private LoggedInUser user = null;

    // private constructor : singleton access
    private LoginRepository(LoginDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static LoginRepository getInstance(LoginDataSource dataSource) {
        if (instance == null) {
            instance = new LoginRepository(dataSource);
        }
        return instance;
    }

    public boolean isLoggedIn() {
        if(dataSource.isUserLoggedIn()){
            try {
                user = dataSource.getLoggedInUser();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(user != null){
            return true;
        }
        return false;
    }

    public void logout() {
        user = null;
        dataSource.logout(user);
    }

    private void setLoggedInUser(LoggedInUser user) {
        this.user = user;

        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }

    public Result<LoggedInUser> login(String username, String password) {
        // handle login
        Result<LoggedInUser> result = dataSource.login(username, password);
        if (result instanceof Result.Success) {
            setLoggedInUser(((Result.Success<LoggedInUser>) result).getData());
        }
        return result;
    }

    public Result<LoggedInUser> register(String username, String password, String firstName, String lastName) {
        // handle login
        Result<LoggedInUser> result = dataSource.register(username, password, firstName, lastName);
        if (result instanceof Result.Success) {
            setLoggedInUser(((Result.Success<LoggedInUser>) result).getData());
        }
        return result;
    }


}
