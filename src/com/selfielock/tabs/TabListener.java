package com.selfielock.tabs;

import com.selfielock.R;
import android.app.ActionBar.Tab;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar;
 
public class TabListener implements ActionBar.TabListener {
 
    Fragment fragment;
 
    public TabListener(Fragment fragment) {
        this.fragment = fragment;
    }
 
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        ft.replace(R.id.fragment_container, fragment);
    }
 
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
        ft.remove(fragment);
    }
 
    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
        
    }
}
