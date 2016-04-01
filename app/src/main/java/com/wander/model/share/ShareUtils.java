package com.wander.model.share;

import android.app.AlertDialog;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import com.wander.bean.Music;
import com.wander.by.App;
import com.wander.by.R;

/**
 * Created by wander on 2016/4/1.
 * email 805677461@qq.com
 */
public class ShareUtils implements View.OnClickListener {
    private Context context = App.getInstance().getApplicationContext();
    private static ShareUtils instance;
    private Dialog dialog;

    public static ShareUtils getInstance() {
        if (instance == null) {
            instance = new ShareUtils();
        }
        return instance;
    }

    private ShareUtils() {
    }

    public void shareMusic(Music music) {
        showDialog();

    }

    public void showDialog() {
        if (dialog == null) {
            dialog = new Dialog(context, R.style.Translucent_NoTitle);
            dialog.setContentView(R.layout.share_dialog);
            View view = LayoutInflater.from(context).inflate(R.layout.share_dialog, null, false);
            view.findViewById(R.id.share_qq).setOnClickListener(this);
            view.findViewById(R.id.share_qzone).setOnClickListener(this);
            view.findViewById(R.id.share_wechat).setOnClickListener(this);
            view.findViewById(R.id.share_wxcircle).setOnClickListener(this);
            view.findViewById(R.id.share_sina).setOnClickListener(this);
            dialog.setCanceledOnTouchOutside(true);
            if (null != dialog.getWindow()){
                dialog.getWindow().setGravity(Gravity.BOTTOM);
            }
        }
        dialog.show();
    }


    @Override
    public void onClick(View v) {

    }
}
