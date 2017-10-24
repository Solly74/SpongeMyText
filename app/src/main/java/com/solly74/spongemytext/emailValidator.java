package com.solly74.spongemytext;

import android.accounts.AuthenticatorDescription;

import static android.util.Patterns.EMAIL_ADDRESS;

/**
 * Created by Solly74 on 2017-08-06.
 */

public class emailValidator {

    public boolean validateEmail(String email)
    {

        boolean valid = EMAIL_ADDRESS.matcher(email).matches();
        return valid;

    }

}
