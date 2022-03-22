package com.example.gestiondesdpenses.frag_addnew;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.gestiondesdpenses.R;
import com.example.gestiondesdpenses.database.DBManager;
import com.example.gestiondesdpenses.database.TypeBean;

import java.util.List;


public class IncomeFragment extends EditBaseFragment {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        accountBean.setTypeImageId(R.mipmap.elseblue);
        accountBean.setKind(1);
    }

    @Override
    public void loadDataToGridView() {
        super.loadDataToGridView();
        List<TypeBean> outList = DBManager.getTypeList(1);//
        typeList.addAll(outList);
        typeBaseAdapter.notifyDataSetChanged();
        imageView_typeChoice.setImageResource(R.mipmap.elseblue);
    }
}