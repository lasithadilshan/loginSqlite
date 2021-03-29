package com.dilshan.loginsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ResetActivity extends AppCompatActivity {

    TextView username;
    EditText pass, rePass;
    Button confirm;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        username = findViewById(R.id.username_reset_text);
        pass = findViewById(R.id.password_reset);
        rePass = findViewById(R.id.rePassword_reset);
        confirm = findViewById(R.id.btnConfirm);
        DB = new DBHelper(this);

        Intent intent = getIntent();
        username.setText(intent.getStringExtra("username"));

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = username.getText().toString();
                String password = pass.getText().toString();
                String rePassword = rePass.getText().toString();
                if (password.equals(rePassword)) {

                    Boolean checkPasswordUpdate = DB.updatePassword(user, password);
                    if (checkPasswordUpdate == true) {
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        Toast.makeText(ResetActivity.this, "Password Updated Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ResetActivity.this, "Password Not Updated", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(ResetActivity.this, "Passwords Not Matching", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}