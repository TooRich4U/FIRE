package com.pixelnos.fire.ui.register;

import androidx.annotation.Nullable;

public class RegisterFormState {
    @Nullable
    private Integer usernameError;
    @Nullable
    private Integer passwordError;
    @Nullable
    private Integer passwordConfirmationError;
    @Nullable
    private Integer firstNameError;
    @Nullable
    private Integer lastNameError;
    private boolean isDataValid;

    RegisterFormState(@Nullable Integer usernameError, @Nullable Integer passwordError, @Nullable Integer passwordConfirmationError, @Nullable Integer firstNameError, @Nullable Integer lastNameError) {
        this.usernameError = usernameError;
        this.passwordError = passwordError;
        this.passwordConfirmationError = passwordConfirmationError;
        this.firstNameError = firstNameError;
        this.lastNameError = lastNameError;
        this.isDataValid = false;
    }

    RegisterFormState(boolean isDataValid) {
        this.usernameError = null;
        this.passwordError = null;
        this.passwordConfirmationError = null;
        this.firstNameError = null;
        this.lastNameError = null;
        this.isDataValid = isDataValid;
    }

    @Nullable
    Integer getUsernameError() {
        return usernameError;
    }

    @Nullable
    Integer getPasswordError() {
        return passwordError;
    }

    @Nullable
    Integer getPasswordConfirmationError() {
        return passwordConfirmationError;
    }

    @Nullable
    Integer getFirstNameError() {
        return firstNameError;
    }

    @Nullable
    Integer getLastNameError() {
        return lastNameError;
    }
    boolean isDataValid() {
        return isDataValid;
    }
}
