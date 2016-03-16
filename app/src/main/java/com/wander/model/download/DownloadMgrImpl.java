package com.wander.model.download;

import android.app.DownloadManager;
import android.content.Context;

public class DownloadMgrImpl implements IDownloadMgr {
    private static final String TAG = "DownloadMgrImpl";

    private Context context;
    private DownloadManager downloadManager;
    private int count;

    @Override
    public boolean deleteAllTasks() {
        return false;
    }

    @Override
    public int getTaskCount() {
        return 0;
    }

    @Override
    public boolean startAllTasks(boolean isUserAction) {
        return false;
    }

    @Override
    public boolean pauseAllTasks(boolean autoPause) {
        return false;
    }

    @Override
    public boolean changeDownloadPath(String path) {
        return false;
    }

    @Override
    public void init() {

    }

    @Override
    public void release() {

    }
}
