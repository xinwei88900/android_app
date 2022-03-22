package com.example.gestiondesdpenses;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class AnalyseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyse);
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.analyse_close:
                finish();
                break;
            default:
                break;
        }
    }
}