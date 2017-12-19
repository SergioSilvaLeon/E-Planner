package com.tecnologiasintech.e_planner;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class ChangePasswordActivity extends AppCompatActivity {


    private EditText inputPassword;
    private Button btnChangePassword;
    private ProgressBar mProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        inputPassword = (EditText) findViewById(R.id.inputNewPassword);
        btnChangePassword = (Button) findViewById(R.id.change_password_button);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePassword(inputPassword, user);
            }
        });


    }

    private void updatePassword(EditText password, FirebaseUser user){
        user.updatePassword(inputPassword.getText().toString().trim())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ChangePasswordActivity.this, "Password is updated!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ChangePasswordActivity.this, "Failed to update password!", Toast.LENGTH_SHORT).show();
                            mProgressBar.setVisibility(View.GONE);
                            try {
                                this.finalize();
                            } catch (Throwable throwable) {
                                throwable.printStackTrace();
                            }
                        }
                    }
                });
    }
}
