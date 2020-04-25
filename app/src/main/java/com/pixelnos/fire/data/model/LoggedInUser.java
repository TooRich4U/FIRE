package com.pixelnos.fire.data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    private String userId;
    private String firstName;
    private String lastName;
    private String email;

    public LoggedInUser(String userId, String firstName, String email, String lastName) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public String getEmail() { return email; }

    public String getDisplayName() {
        return firstName + " " + lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }
}
