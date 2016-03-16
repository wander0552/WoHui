package com.wander.base.utils;

import android.util.Log;

import com.wander.by.App;
import com.wander.core.messageMgr.MessageManager;
import com.wander.core.messageMgr.ThreadMessageHandler;

import java.io.PrintWriter;
import java.io.StringWriter;



// by haiping
public final class KwDebug {
	private static final String TAG = "KwDebug";
	private static ThreadMessageHandler messageHandler;
	static {
			messageHandler = new ThreadMessageHandler();
	}

	private KwDebug() {
	}

	public static void classicAssert(final boolean value) {
		classicAssert(value, (String) null);
	}

	// 生成崩溃日志的时候，info会作为附加信息写入
	public static void classicAssert(final boolean value, final String info) {
		if (!value) {
			if (AppInfo.IS_DEBUG) {
				if (info != null) {
					Log.d(TAG, info);
//					KwExceptionHandler.setAdditionalInfo(info);
				}
				System.out.print(String.valueOf(new byte[512 * 1024 * 1024]) + 1 / 0); // 就不信你不挂，Throwable不容易被catch住
			} else if(!App.isExiting()){
				final String stackInfo = createStackInfo();
				MessageManager.getInstance().asyncRunTargetHandler(messageHandler.getHandler(), new MessageManager.Runner() {
					@Override
					public void call() {
						if (info != null) {
//							KwExceptionHandler.setAdditionalInfo(info);
						}
//						KwExceptionHandler.saveErrorLog(stackInfo, null);
					}
				});
			}
		}
	}

	// 生成崩溃日志的时候，info会作为附加信息写入
	public static void classicAssert(final boolean value, final Throwable info) {
		classicAssert(value, throwable2String(info));
	}

	public static void assertPointer(final Object o) {
		classicAssert(null != o);
	}

	public static void mustMainThread() {
		if (Thread.currentThread().getId() != App.getMainThreadID()) {
			classicAssert(false, "必须在主线程中调用");
		}
	}

	public static void mustNotMainThread() {
		if (Thread.currentThread().getId() == App.getMainThreadID()) {
			classicAssert(false, "禁止在主线程中调用");
		}
	}

	public static void mustSubInstance(final Class<?> c, final Object obj) {
		if (!c.isInstance(obj)) {
			classicAssert(false, obj.toString() + "必须是" + c.toString() + "的子类");
		}
	}
	
	public static void mustFromMainProgress() {
		if (!App.isMainProgress()) {
			classicAssert(false,"必须在主进程里调用");
		}
	}
	
	public static void mustNotFromMainProgress() {
		if (App.isMainProgress()) {
			classicAssert(false,"不能在主进程里调用");
		}
	}

	public static String throwable2String(final Throwable tr) {
		try {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			tr.printStackTrace(pw);
			return sw.toString();
		} catch (Throwable e) {
		}
		return "No Memory, throwable2String failed";
	}

	public static String createStackInfo() {
		try {
			throw new KwStackInfoCreator();
		} catch (Throwable e) {
			return throwable2String(e);
		}
	}
	
	private static final class KwStackInfoCreator extends Exception {
		private static final long serialVersionUID = 1L;
	}

}
