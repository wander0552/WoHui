package com.wander.presenter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.wander.by.MainActivity;
import com.wander.by.R;
import com.wander.ui.main.ItemFragment;
import com.wander.ui.main.LocusFragment;

/**
 * Created by wander on 2016/3/4.
 * email 805677461@qq.com
 */
public class MainPresenter {
    private MainActivity mainActivity;
    private Fragment currentFragment;

    public MainPresenter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void goFirst() {
        if (mainActivity != null) {
            mainActivity.setTitle("轨迹");
            LocusFragment fragment = LocusFragment.newInstance("11", "22");
            replaceFragment(null, fragment);
        }
    }

    public void goFragment() {
        if (mainActivity != null) {
            mainActivity.setTitle("Fragment");
            ItemFragment itemFragment = ItemFragment.newInstance(100);
            replaceFragment(currentFragment, itemFragment);
        }
    }

    public void replaceFragment(Fragment oldFragment, Fragment newFragment) {
        if (mainActivity != null) {
            FragmentTransaction transaction = mainActivity.getSupportFragmentManager().beginTransaction();
            if (oldFragment != null) {
                transaction.remove(oldFragment);
            }
            if (newFragment != null) {
                transaction.replace(R.id.fragment_container, newFragment);
                currentFragment = newFragment;
            }
            transaction.commit();
        }
    }


}
