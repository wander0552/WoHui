package com.wander.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.wander.by.MainActivity;
import com.wander.by.R;
import com.wander.ui.Gps.GPSActivity;
import com.wander.ui.find.FindFragment;
import com.wander.ui.main.LocusFragment;

/**
 * Created by wander on 2016/3/4.
 * email 805677461@qq.com
 */
public class MainPresenter {
    private final FragmentManager fragmentManager;
    private String FIRST = "first";
    private String FIND = "find";
    private MainActivity mainActivity;

    public MainPresenter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;

        fragmentManager = mainActivity.getSupportFragmentManager();
    }

    public void goFirst() {
        LocusFragment fragment = LocusFragment.newInstance("11", "22");
        replaceFragment(null, fragment, "轨迹");
    }

    public void goFind() {
        FindFragment findFragment = FindFragment.newInstance("", "");
        replaceFragment(null, findFragment, "你我之间");
    }

    public void goActivity( Context context,Class<GPSActivity> activity) {
        context.startActivity(new Intent(context, activity));
    }

    public void replaceFragment(Fragment oldFragment, Fragment newFragment, String title) {
        if (mainActivity != null) {
            mainActivity.setTitle(title);
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            if (oldFragment != null) {
                transaction.remove(oldFragment);
            }
            if (newFragment != null) {
                transaction.replace(R.id.fragment_container, newFragment);
            }
            transaction.commit();
        }
    }


}
