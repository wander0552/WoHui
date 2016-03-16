package com.wander.model.download;


import com.wander.core.moduleMgr.IModuleBase;

import java.util.List;



//本模块提供向上层提供任务列表和控制任务, isUserAction表明是否是用户操作
//by wang meng
public interface IDownloadMgr extends IModuleBase {


	// 删除所有任务
	boolean deleteAllTasks();

	// 获得当前所有的任务
	int getTaskCount();

	// 开始所有任务
	boolean startAllTasks(boolean isUserAction);

	// 暂停所有任务
	boolean pauseAllTasks(boolean autoPause);

	//用户修改下载目录
	boolean changeDownloadPath(final String path);

}
