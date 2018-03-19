package com.example.ramya.myvehicleapp.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ramya.myvehicleapp.R;
import com.example.ramya.myvehicleapp.database.ExampleDao;

public class SignupActivity extends AppCompatActivity {
    EditText etFirstName, etLastName, etEmail, etPassword, etConfirmPassword;
    Button btnRegister;

    ExampleDao exampleDao;

    String fName, lName, mail, pass, pass2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        exampleDao = new ExampleDao(getApplicationContext());

        etFirstName = findViewById(R.id.activity_sign_up_et_firstName);
        etLastName = findViewById(R.id.activity_sign_up_et_lastName);
        etEmail = findViewById(R.id.activity_sign_up_et_email);
        etPassword = findViewById(R.id.activity_sign_up_et_password);
        etConfirmPassword = findViewById(R.id.activity_sign_up_et_Confirmpassword);

        btnRegister = findViewById(R.id.activity_sign_up_btn_register);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fName = etFirstName.getText().toString();
                lName = etLastName.getText().toString();
                mail = etEmail.getText().toString();
                pass = etPassword.getText().toString();
                pass2 = etConfirmPassword.getText().toString();

                if (!(fName.isEmpty() || lName.isEmpty() || mail.isEmpty() || pass.isEmpty())) {
                    if (pass.equals(pass2)) {
                        exampleDao.insertUser(fName, lName, mail, pass);

                        //Switching to LoginActivity login with new user
                        Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), R.string.password_mismatch, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), R.string.fill_fields, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
