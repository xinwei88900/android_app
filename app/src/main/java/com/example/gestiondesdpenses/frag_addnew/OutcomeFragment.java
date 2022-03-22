package com.example.gestiondesdpenses.frag_addnew;


import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.gestiondesdpenses.R;
import com.example.gestiondesdpenses.database.AccountBean;
import com.example.gestiondesdpenses.database.DBManager;
import com.example.gestiondesdpenses.database.TypeBean;

import java.util.List;

public class OutcomeFragment extends EditBaseFragment {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        accountBean.setTypeImageId(R.mipmap.elsered);
        accountBean.setKind(0);
    }

    @Override
    public void loadDataToGridView() {
        super.loadDataToGridView();

        List<TypeBean> outList = DBManager.getTypeList(0);//  //////flash back
        typeList.addAll(outList);
        typeBaseAdapter.notifyDataSetChanged();
    }
}
