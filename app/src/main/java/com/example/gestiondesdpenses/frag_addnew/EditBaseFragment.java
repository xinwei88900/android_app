package com.example.gestiondesdpenses.frag_addnew;

import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.gestiondesdpenses.R;
import com.example.gestiondesdpenses.database.AccountBean;
import com.example.gestiondesdpenses.database.DBManager;
import com.example.gestiondesdpenses.database.TypeBean;
import com.example.gestiondesdpenses.utils.EditRemarkDialog;
import com.example.gestiondesdpenses.utils.KeyboardUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

//Initialize all the components of the outcomeFragments
public abstract class EditBaseFragment extends Fragment implements View.OnClickListener{

    KeyboardView keyboardView;
    EditText editText_money;
    ImageView imageView_typeChoice;
    TextView textView_typeChoice, textView_time, textView_remarks;
    GridView gridView_types;
    List<TypeBean> typeList;
    TypeBaseAdapter typeBaseAdapter;

    AccountBean accountBean;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        accountBean = new AccountBean();
        accountBean.setTypeName("Autre");
        accountBean.setRemarks("");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_outcome, container, false);
        initView(view);
        setInstantTime();
        loadDataToGridView();
        setGridViewListener();
        return view;
    }

    private void setInstantTime() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd,MM,yyyy HH:mm");
        String time = simpleDateFormat.format(date);
        textView_time.setText(time);
        accountBean.setTime(time);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        accountBean.setYear(year);
        accountBean.setMonth(month);
        accountBean.setDay(day);
    }

    private void setGridViewListener() {
        gridView_types.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                typeBaseAdapter.selected_position = position;
                typeBaseAdapter.notifyDataSetInvalidated();
                TypeBean typeChoice = typeList.get(position);
                textView_typeChoice.setText(typeChoice.getType_name());
                imageView_typeChoice.setImageResource(typeChoice.getSelect_imageID());

                accountBean.setTypeName(typeChoice.getType_name());
                accountBean.setTypeImageId(typeChoice.getSelect_imageID());
            }
        });
    }

    public void loadDataToGridView() {
        typeList = new ArrayList<>();
        typeBaseAdapter = new TypeBaseAdapter(getContext(),typeList);//Initialize adapter
        gridView_types.setAdapter(typeBaseAdapter);

    }

    private void saveAccount(){
        DBManager.insertAccount(accountBean);
    }

    private void initView(View view) {

        keyboardView = view.findViewById(R.id.frag_outcome_keyboard);
        editText_money = view.findViewById(R.id.frag_outcome_et);
        imageView_typeChoice = view.findViewById(R.id.frag_outcome_iv);
        textView_typeChoice = view.findViewById(R.id.frag_outcome_tv);
        textView_time = view.findViewById(R.id.frag_outcome_time);
        textView_remarks = view.findViewById(R.id.frag_outcome_remark);
        gridView_types = view.findViewById(R.id.frag_outcome_gv);

        //Show customized keyboard
        KeyboardUtils keyboardUtils = new KeyboardUtils(keyboardView,editText_money);
        keyboardUtils.showKeyboard();

        keyboardUtils.setOnEnsureListener(new KeyboardUtils.OnEnsureListener() {
            @Override
            public void onEnsure() {
                //Ky “Valider” is pressed
                String moneyString = editText_money.getText().toString();
                if(moneyString.isEmpty() || moneyString.equals("0")){
                    getActivity().finish();
                }
                else{
                    float money = Float.parseFloat(moneyString);
                    accountBean.setMoney(money);
                    //Store the edited data to the local data base
                    // and return to last page
                    saveAccount();
                    getActivity().finish();
                }


            }
        });

        textView_remarks.setOnClickListener(this);
        textView_time.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.frag_outcome_time:
                comeUpDialogTime();
                break;
            case R.id.frag_outcome_remark:
                comeUpDialogRemark();
                break;
        }
    }

    private void comeUpDialogTime() {

    }

    public void comeUpDialogRemark(){
        EditRemarkDialog dialog = new EditRemarkDialog(getContext());
        dialog.show();
        dialog.setOnEnsureListener(new EditRemarkDialog.OnEnsureListener() {
            @Override
            public void onEnsure() {
                String text = dialog.getEditText();
                if(!text.isEmpty()){
                    textView_remarks.setText(text);
                    accountBean.setRemarks(text);
                }
                dialog.cancel();
            }
        });
    }
}










