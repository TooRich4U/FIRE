package com.pixelnos.fire.ui.register;

import android.util.Patterns;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pixelnos.fire.R;
import com.pixelnos.fire.data.LoginRepository;
import com.pixelnos.fire.data.Result;
import com.pixelnos.fire.data.model.LoggedInUser;

public class RegisterViewModel extends ViewModel {
    private MutableLiveData<RegisterFormState> registerFormState = new MutableLiveData<>();
    private MutableLiveData<RegisterResult> registerResult = new MutableLiveData<>();
    private LoginRepository loginRepository;

    RegisterViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    LiveData<RegisterFormState> getRegisterFormState() {
        return registerFormState;
    }

    LiveData<RegisterResult> getRegisterResult() {
        return registerResult;
    }

    public void register(String username, String password,  String firstName, String lastName) {
        // can be launched in a separate asynchronous job
        Result<LoggedInUser> result = loginRepository.register(username, password, firstName, lastName);

        if (result instanceof Result.Success) {
            LoggedInUser data = ((Result.Success<LoggedInUser>) result).getData();
            registerResult.setValue(new RegisterResult(new RegisteredUserView(data.getDisplayName())));
        } else {
            registerResult.setValue(new RegisterResult(R.string.login_failed));
        }
    }

    public void registerDataChange(String username, String password, String password2, String firstName, String lastName) {
        if (!isUserNameValid(username)) {
            registerFormState.setValue(new RegisterFormState(R.string.invalid_email, null, null, null, null));
        } else if (!isPasswordValid(password)) {
            registerFormState.setValue(new RegisterFormState(null, R.string.invalid_password, null, null, null));
        }else if (!isPasswordConfirmationValid(password, password2)) {
            registerFormState.setValue(new RegisterFormState(null, null, R.string.invalid_password_confirmation, null, null));
        }else if (!isFirstNameValid(firstName)) {
            registerFormState.setValue(new RegisterFormState(null, null, null, R.string.invalid_first_name, null));
        }else if (!isLastNameValid(lastName)) {
            registerFormState.setValue(new RegisterFormState(null, null, null, null, R.string.invalid_last_name));
        } else {
            registerFormState.setValue(new RegisterFormState(true));
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        return Patterns.EMAIL_ADDRESS.matcher(username).matches();
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }

    // A placeholder password confirmation validation check
    private boolean isPasswordConfirmationValid(String password, String password2) {
        return password.equals(password2);
    }

    // A placeholder first name validation check
    private boolean isFirstNameValid(String firstName) {
        if (firstName == null) {
            return false;
        }
        return !firstName.trim().isEmpty();
    }

    // A placeholder last name validation check
    private boolean isLastNameValid(String lastName) {
        if (lastName == null) {
            return false;
        }
        return !lastName.trim().isEmpty();
    }
}
