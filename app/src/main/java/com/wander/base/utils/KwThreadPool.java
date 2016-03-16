package com.wander.base.utils;

// by haiping ，简单处理，工期紧急，以后有时间再加策略
public final class KwThreadPool {

	private static final int POOL_CAPACITY_MAX = 5;

	public static enum JobType {
		NORMAL,
		NET,
		IMMEDIATELY
	}

	public static void runThread(final JobType type, final Runnable r) {
		int priority = android.os.Process.THREAD_PRIORITY_DEFAULT;
		if (type == JobType.NET) {
			priority = android.os.Process.THREAD_PRIORITY_DEFAULT; 
		}
		KwThread thread = getIdleThread();
		thread.runThread(r, priority);
	}
	
	private static KwThread getIdleThread() {
		if (nextBlankPos == 0) {
			return new KwThread();
		}

		synchronized (threads) {
			if (nextBlankPos == 0) {
				return new KwThread();
			} else {
				--nextBlankPos;
				KwThread thread = threads[nextBlankPos];
				threads[nextBlankPos] = null;
				return thread;
			}
		}
	}
	
	private static volatile int nextBlankPos = 0;
	private static KwThread[] threads = new KwThread[POOL_CAPACITY_MAX];
	
	private static final class KwThread extends Thread {
		public void runThread(final Runnable r, final int priority) {
			this.r = r;
			this.priority = priority;
			if (!running) {
				this.start();
				running = true;
			} else {
				synchronized (this) {
					this.notify();
				}
			}
		}
		
		public void run() {
			while(true) {
				android.os.Process.setThreadPriority(priority); 				
				r.run();
				r=null;
				if (nextBlankPos >= POOL_CAPACITY_MAX) {
					break;
				} else {
					synchronized (this) {
						synchronized (threads) {
							if (nextBlankPos >= POOL_CAPACITY_MAX) {
								break;
							}
							threads[nextBlankPos] = this;
							++nextBlankPos;
						}
						try {
							this.wait();
						} catch (InterruptedException e) {
							break;
						}
					}
				}
			}
			running = false;
		}
		private volatile Runnable r;
		private volatile int priority;
		private volatile boolean running;
	}
	
	private KwThreadPool() {
	}
}
