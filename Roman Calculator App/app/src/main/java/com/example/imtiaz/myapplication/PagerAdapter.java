package com.example.imtiaz.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int mNoOfTabs;

    /**
     * This method manages all the fragments and sets param NumberOfTabs.
     * @param fm is a management of the fragments possible.
     * @param NumberOfTabs is the number of tabs in the application.
     */
    public PagerAdapter(FragmentManager fm, int NumberOfTabs) {
        super(fm);
        this.mNoOfTabs = NumberOfTabs;
    }

    /**
     * Determines which fragment needs to be accessed at a certain time. Access to more fragments can be added here.
     * @param i used to determine which case.
     * @return the tab that needs to be accessed.
     */
    public Fragment getItem(int i) {
        switch (i) {

            case 0:
                IntegerScreen tab1 = new IntegerScreen();
                return tab1;
            case 1:
                RomanScreen tab2 = new RomanScreen();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }
}
