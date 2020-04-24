package com.pixelnos.fire.data;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.pixelnos.fire.WelcomeActivity;
import com.pixelnos.fire.data.model.LoggedInUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {
    private Context context;

    public LoginDataSource(Context context) {
        this.context = context;
    }

    public Boolean isUserLoggedIn(){
        String string = readFromFile(this.context);
        try {
            JSONObject obj = new JSONObject(string);
            return obj.getBoolean("connected");
        }
        catch (JSONException e) {
            return false;
        }
    }

    public LoggedInUser getLoggedInUser() throws Exception {
        String string = readFromFile(this.context);
        try {
            JSONObject obj = new JSONObject(string);
            return new LoggedInUser(obj.getString("id"), obj.getString("user"));
        }
        catch (JSONException e) {
            throw new Exception(e.getMessage());
        }
    }

    public Result<LoggedInUser> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication
            LoggedInUser fakeUser =
                    new LoggedInUser(
                            java.util.UUID.randomUUID().toString(),
                            username);
            writeToFile("{\"user\":" + fakeUser.getDisplayName() +
                    ", \"id\":" + fakeUser.getUserId() +
                    ", \"connected\": true}", this.context);
            return new Result.Success<>(fakeUser);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }

    private void writeToFile(String data, Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("config.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
            Log.i("Info", "File write succeeded!");
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    private String readFromFile(Context context) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput("config.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append("\n").append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }
}
