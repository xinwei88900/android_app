package com.example.gestiondesdpenses.utils;

import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.Editable;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import com.example.gestiondesdpenses.R;

public class KeyboardUtils {

    private final Keyboard k1;
    private KeyboardView keyboardView;
    private EditText editText;

    //Interface call
    public interface OnEnsureListener{
        public void onEnsure();
    }
    KeyboardUtils.OnEnsureListener onEnsureListener;
    public void setOnEnsureListener(KeyboardUtils.OnEnsureListener onEnsureListener){
        this.onEnsureListener = onEnsureListener;
    }
    //Interface call over

    public KeyboardUtils(KeyboardView keyboardView, EditText editText) {
        this.keyboardView = keyboardView; //Localization of keyboardView from the parameter of constructor
        this.editText = editText; //Localization of editText from the parameter of constructor
        this.editText.setInputType(InputType.TYPE_NULL); //Cancel the automatic pop-up keyboard
        k1 = new Keyboard(this.editText.getContext(), R.xml.key);//Match keyboard with editText and create instance k1


        this.keyboardView.setKeyboard(k1); // Match the keyboard instance to the keyboardView
        this.keyboardView.setEnabled(true);// Activate
        this.keyboardView.setPreviewEnabled(false);
        this.keyboardView.setOnKeyboardActionListener(listener); //Incoming listener of keyboardView

    }

    KeyboardView.OnKeyboardActionListener listener = new KeyboardView.OnKeyboardActionListener() {
        @Override
        public void onPress(int primaryCode) {

        }

        @Override
        public void onRelease(int primaryCode) {

        }

        @Override
        public void onKey(int primaryCode, int[] keyCodes) {
            Editable editable = editText.getText();
            int start = editText.getSelectionStart();


            switch (primaryCode){
                case Keyboard.KEYCODE_DELETE: //pressed "Effacer" with the value of key -5
                    if(editable!=null && editable.length()>0){
                        if(start>0){
                            editable.delete(start-1,start); //Delete the last digit: delete from start-1 to start.
                        }
                    }
                    break;
                case Keyboard.KEYCODE_CANCEL: //pressed "Annuler" with the value of key -3
                    editable.clear();  //clear all of the text
                    break;

                case Keyboard.KEYCODE_DONE: // pressed "Valider" with the value of key -4
                    onEnsureListener.onEnsure(); //call this method when <<Valider>> is pressed
                    break;

                default: // else pressed numbers
                    editable.insert(start,Character.toString((char) primaryCode));//?
                    break;
            }
        }

        @Override
        public void onText(CharSequence text) {

        }

        @Override
        public void swipeLeft() {

        }

        @Override
        public void swipeRight() {

        }

        @Override
        public void swipeDown() {

        }

        @Override
        public void swipeUp() {

        }
    };

    public void showKeyboard(){
        //show the keyboard
        int visibility = keyboardView.getVisibility();
        if( visibility== View.INVISIBLE || visibility==View.GONE){
            keyboardView.setVisibility(View.VISIBLE);
        }
    }
    public void hideKeyboard(){
        //hide the keyboard
        int visibility = keyboardView.getVisibility();
        if( visibility == View.VISIBLE || visibility == View.INVISIBLE){
            keyboardView.setVisibility(View.GONE);
        }
    }
}
