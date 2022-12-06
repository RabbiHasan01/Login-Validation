package com.example.loginvalidation03;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private EditText userName, email, password;
    private TextView userNameError, emailError, passwordError;
    private TextView tvColor;
    private CardView frameOne, frameTwo, frameThree, frameFour;
    private CardView btnRegister;
    private boolean isAtLeast8 = false, hasUppercase = false, hasNumber = false, hasSymbol = false, isRegistrationClickable = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvColor = findViewById(R.id.tvColor);
        userNameError = findViewById(R.id.userNameError);
        emailError = findViewById(R.id.emailError);
        passwordError = findViewById(R.id.passwordError);
        userName = findViewById(R.id.userNameId);
        email = findViewById(R.id.emailId);
        password = findViewById(R.id.passwordId);
        frameOne = findViewById(R.id.frameOne);
        frameTwo = findViewById(R.id.frameTwo);
        frameThree = findViewById(R.id.frameThree);
        frameFour = findViewById(R.id.frameFour);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = userName.getText().toString(),
                        myEmail = email.getText().toString(),
                        myPassword = password.getText().toString();

                if (name.length() > 0 && myEmail.length() > 0 && myPassword.length() > 0) {
                    if (isRegistrationClickable) {
                        Toast.makeText(MainActivity.this, "Registration complete", Toast.LENGTH_LONG).show();
                    }
                } else {
                    if (name.length() == 0) {
                        userNameError.setVisibility(View.VISIBLE);
                        //userName.setError("Field can not be empty!");
                    }
                    if (myEmail.length() == 0) {
                        emailError.setVisibility(View.VISIBLE);
                        //email.setError("Field can not be empty!");
                    }
                    if (myPassword.length() == 0) {
                        passwordError.setVisibility(View.VISIBLE);
                        //password.setError("Field can not be empty!");
                    }
                }
            }
        });
        inputChange();
    }

    private void checkEmpty(String name, String email, String password) {
        if (name.length() > 0 && userNameError.getVisibility() == View.VISIBLE) {
            userNameError.setVisibility(View.GONE);
        }
        if (password.length() > 0 && passwordError.getVisibility() == View.VISIBLE) {
            passwordError.setVisibility(View.GONE);
        }
        if (email.length() > 0 && emailError.getVisibility() == View.VISIBLE) {
            emailError.setVisibility(View.GONE);
        }
    }

    @SuppressLint("ResourceType")
    private void checkAllData(String email) {
        if (isAtLeast8 && hasUppercase && hasNumber && hasSymbol && email.length() > 0) {
            isRegistrationClickable = true;
            tvColor.setTextColor(Color.WHITE);
            btnRegister.setCardBackgroundColor(Color.parseColor(getString(R.color.colorAccent)));
        } else {
            isRegistrationClickable = false;
            btnRegister.setCardBackgroundColor(Color.parseColor(getString(R.color.colorDefault)));
        }
    }

    @SuppressLint("ResourceType")
    private void registrationDataCheck() {
        String myPassword = password.getText().toString(),
                myEmail = email.getText().toString(),
                name = userName.getText().toString();

        checkEmpty(name, myEmail, myPassword);

        if (myPassword.length() >= 8) {
            isAtLeast8 = true;
            frameOne.setCardBackgroundColor(Color.parseColor(getString(R.color.colorAccent)));
        } else {
            isAtLeast8 = false;
            frameOne.setCardBackgroundColor(Color.parseColor(getString(R.color.colorDefault)));
        }
        if (myPassword.matches("(.*[A-Z].*)")) {
            hasUppercase = true;
            frameTwo.setCardBackgroundColor(Color.parseColor(getString(R.color.colorAccent)));
        } else {
            hasUppercase = false;
            frameTwo.setCardBackgroundColor(Color.parseColor(getString(R.color.colorDefault)));
        }
        if (myPassword.matches("(.*[0-9].*)")) {
            hasNumber = true;
            frameThree.setCardBackgroundColor(Color.parseColor(getString(R.color.colorAccent)));
        } else {
            hasNumber = false;
            frameThree.setCardBackgroundColor(Color.parseColor(getString(R.color.colorDefault)));
        }
        if (myPassword.matches("^(?=.*[_!@#$%^&*_+=.()]).*$")) {
            hasSymbol = true;
            frameFour.setCardBackgroundColor(Color.parseColor(getString(R.color.colorAccent)));
        } else {
            hasSymbol = false;
            frameFour.setCardBackgroundColor(Color.parseColor(getString(R.color.colorDefault)));
        }

        checkAllData(myEmail);
    }

    private void inputChange() {
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                registrationDataCheck();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                registrationDataCheck();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
