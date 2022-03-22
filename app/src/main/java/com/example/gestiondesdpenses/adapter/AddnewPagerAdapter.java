package com.example.gestiondesdpenses.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class AddnewPagerAdapter extends FragmentPagerAdapter {

    List<Fragment> fragmentList;
    String[]titles = {"Dépense","Revenu"};
    public AddnewPagerAdapter(@NonNull  FragmentManager fm, List<Fragment>fragmentList) {
        super(fm);

        this.fragmentList = fragmentList; //Introduce the list into the adapter through the constructor
    }

    @NonNull

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
