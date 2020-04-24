package com.pixelnos.fire.ui.register;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.pixelnos.fire.R;

public class RegisterActivity extends AppCompatActivity {

    private RegisterViewModel registerViewModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);
        registerViewModel = ViewModelProviders.of(this, new RegisterViewModelFactory(this.getApplicationContext()))
                .get(RegisterViewModel.class);


        final EditText usernameEditText = findViewById(R.id.username_register);
        final EditText passwordEditText = findViewById(R.id.password_register);
        final EditText password2EditText = findViewById(R.id.password2);
        final EditText firstNameEditText = findViewById(R.id.firstName);
        final EditText lastNameEditText = findViewById(R.id.lastName);
        final Button registerButton = findViewById(R.id.register_button);

        registerViewModel.getRegisterFormState().observe(this, new Observer<RegisterFormState>() {
            @Override
            public void onChanged(@Nullable RegisterFormState registerFormState) {
                if (registerFormState == null) {
                    return;
                }
                registerButton.setEnabled(registerFormState.isDataValid());
                if (registerFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(registerFormState.getUsernameError()));
                }
                if (registerFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(registerFormState.getPasswordError()));
                }
                if (registerFormState.getPasswordConfirmationError() != null) {
                    password2EditText.setError(getString(registerFormState.getPasswordConfirmationError()));
                }
                if (registerFormState.getFirstNameError() != null) {
                    firstNameEditText.setError(getString(registerFormState.getFirstNameError()));
                }
                if (registerFormState.getLastNameError() != null) {
                    lastNameEditText.setError(getString(registerFormState.getLastNameError()));
                }
            }
        });

        registerViewModel.getRegisterResult().observe(this, new Observer<RegisterResult>() {
            @Override
            public void onChanged(@Nullable RegisterResult registerResult) {
                if (registerResult == null) {
                    return;
                }
                if (registerResult.getError() != null) {
                    showLoginFailed(registerResult.getError());
                }
                if (registerResult.getSuccess() != null) {
                    updateUiWithUser(registerResult.getSuccess());
                }
                setResult(Activity.RESULT_OK);

                //Complete and destroy login activity once successful
                finish();
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                registerViewModel.registerDataChange(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString(), password2EditText.getText().toString(),
                        firstNameEditText.getText().toString(), lastNameEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        password2EditText.addTextChangedListener(afterTextChangedListener);
        firstNameEditText.addTextChangedListener(afterTextChangedListener);
        lastNameEditText.addTextChangedListener(afterTextChangedListener);

        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    registerViewModel.register(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString(),
                            firstNameEditText.getText().toString(), lastNameEditText.getText().toString());
                }
                return false;
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerViewModel.register(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString(), firstNameEditText.getText().toString(), lastNameEditText.getText().toString());
            }
        });
    }

    private void updateUiWithUser(RegisteredUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}
