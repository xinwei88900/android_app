package com.example.gestiondesdpenses;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class SetUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up);
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.setup_iv_close:
                finish();
                break;
            case R.id.setup_tv_1:
                break;
            case R.id.setup_tv_2:
                break;
            default:
                break;
        }
    }
}