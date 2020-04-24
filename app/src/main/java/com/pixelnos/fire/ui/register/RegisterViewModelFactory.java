package com.pixelnos.fire.ui.register;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.pixelnos.fire.data.LoginDataSource;
import com.pixelnos.fire.data.LoginRepository;
import com.pixelnos.fire.ui.login.LoginViewModel;

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
public class RegisterViewModelFactory implements ViewModelProvider.Factory {
    private Context context;
    public RegisterViewModelFactory(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(RegisterViewModel.class)) {
            return (T) new RegisterViewModel(LoginRepository.getInstance(new LoginDataSource(this.context)));
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
