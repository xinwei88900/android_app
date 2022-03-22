package com.example.gestiondesdpenses;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LogInActivity extends AppCompatActivity {

    String code = "000000";
    EditText editText;
    TextView errorText;
    //Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        errorText = findViewById(R.id.login_error_tv);
        editText = findViewById(R.id.login_et);
        editText.setTransformationMethod(PasswordTransformationMethod.getInstance());

    }

    private void enterMainActivity(){
        Intent intent=null;
        intent = new Intent(LogInActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_bt:
                String text = editText.getText().toString();
                if(text.equals(code)){
                    enterMainActivity();
                }else {
                    comingUpErrorMessage();
                    editText.selectAll();
                }
                break;

            case R.id.login_et:
                errorText.setText("");
            default:
                break;
        }
    }

    private void comingUpErrorMessage() {
        errorText.setText("Mot de passe incorrect!");
    }
}