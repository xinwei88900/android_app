package com.example.gestiondesdpenses.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.example.gestiondesdpenses.R;

public class EditRemarkDialog extends Dialog implements View.OnClickListener {

    Button validButton,cancelButton;
    EditText editText;
    OnEnsureListener onEnsureListener;

    public void setOnEnsureListener(OnEnsureListener onEnsureListener) {
        this.onEnsureListener = onEnsureListener;
    }

    public EditRemarkDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_edit_text);
        editText = findViewById(R.id.dialog_et);
        validButton = findViewById(R.id.dialog_bt_valid);
        cancelButton = findViewById(R.id.dialog_bt_cancel);

        validButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
    }

    public interface OnEnsureListener{
        public void onEnsure();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dialog_bt_cancel:
                cancel();
                break;
            case R.id.dialog_bt_valid:
                if (onEnsureListener!=null){
                    onEnsureListener.onEnsure(); //可以使enEnsure后的一系列行为通过接口回调定义在EditBaseFragment.java中，因为所需数据都在EditBaseFragment.java
                }
                break;
        }
    }

    public String getEditText(){
        return editText.getText().toString();
    }
}
























