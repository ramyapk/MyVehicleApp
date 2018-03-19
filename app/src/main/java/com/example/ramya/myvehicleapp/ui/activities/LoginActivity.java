package com.example.ramya.myvehicleapp.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ramya.myvehicleapp.R;
import com.example.ramya.myvehicleapp.database.ExampleDao;
import com.example.ramya.myvehicleapp.models.UserProfile;
import com.example.ramya.myvehicleapp.util.Constants;


public class LoginActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button login;
    TextView signup;
    ImageView ivHide;

    ExampleDao exampleDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        exampleDao = new ExampleDao(getApplicationContext());

        etEmail = findViewById(R.id.activity_login_et_email);
        etPassword = findViewById(R.id.activity_login_et_password);

        login = findViewById(R.id.activity_login_btn_login);

        signup = findViewById(R.id.tv_signup);

        ivHide = findViewById(R.id.activity_login_iv_password_hide);

        //To show or hide passwords
        ivHide.setOnClickListener(new View.OnClickListener() {
            boolean isPasswordHidden = true;

            @Override
            public void onClick(View view) {
                if (isPasswordHidden) {
                    etPassword.setTransformationMethod(null);
                    ivHide.setImageResource(R.drawable.hide);
                    isPasswordHidden = false;
                } else {
                    etPassword.setTransformationMethod(new PasswordTransformationMethod());
                    ivHide.setImageResource(R.drawable.eye);
                    isPasswordHidden = true;
                }
            }
        });

        //To login with given user details
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email, password;
                email = etEmail.getText().toString();
                password = etPassword.getText().toString();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), R.string.fill_fields, Toast.LENGTH_SHORT).show();
                } else {

                    UserProfile user = exampleDao.getUser(email);

                    if (user == null) {
                        Toast.makeText(getApplicationContext(),R.string.sign_in_first, Toast.LENGTH_SHORT).show();
                    }
                    else if (user.getEmail().equals(email) && user.getPassword().equals(password))   {
                        //Passing the email id to VehicleInfoActivity to get vehivle Information
                        Intent intent = new Intent(getApplicationContext(), VehicleInfoActivity.class);
                        intent.putExtra(Constants.KEY_USER_EMAIL, user.getEmail());
                        startActivity(intent);

                    } else {
                        Toast.makeText(getApplicationContext(), R.string.inavalid_credential, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        SpannableString signupSpannableString = new SpannableString(getResources().getString(R.string.signup));
        signupSpannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorAccent)), 14, 21, 0);
        signup.setText(signupSpannableString);

        //Switching to SignUpActivity to register new user
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}
