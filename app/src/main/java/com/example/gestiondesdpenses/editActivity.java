package com.example.gestiondesdpenses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.example.gestiondesdpenses.adapter.AddnewPagerAdapter;
import com.example.gestiondesdpenses.frag_addnew.IncomeFragment;
import com.example.gestiondesdpenses.frag_addnew.EditBaseFragment;
import com.example.gestiondesdpenses.frag_addnew.OutcomeFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class editActivity extends AppCompatActivity {


    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        //Match components
        tabLayout = findViewById(R.id.edit_tab);
        viewPager = findViewById(R.id.edit_vp);
        //Set the viewpager loading pages
        initPager();
    }

    private void initPager() {
        //Initialize the viewpager page list
        List<Fragment>fragmentList = new ArrayList<>();
        OutcomeFragment outFrag = new OutcomeFragment();//Create outcome fragment instance
        IncomeFragment inFrag = new IncomeFragment();//Create income fragment instance
        fragmentList.add(outFrag);//Put outcome fragment instance into list
        fragmentList.add(inFrag); //Put income fragment instance into list
        //Create an adapter for the viewPager
        AddnewPagerAdapter pagerAdapter = new AddnewPagerAdapter(getSupportFragmentManager(),fragmentList);
        //Set up the adapter of the viewPager
        viewPager.setAdapter(pagerAdapter);
        //Associate viewPager and tabLayout
        tabLayout.setupWithViewPager(viewPager);


    }


    public void onClick(View view) {
        switch(view.getId()){
            case R.id.edit_page_close:
                finish();
                break;
            default:
                break;
        }
    }
}