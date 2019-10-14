package com.atguigu.countDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

//CountDownLatch（闭锁） 创建时给定初始值，线程执行，初始值减少直到为0，才会调用await方法
public class CountDownLatchDemo {
	public static void main(String[] args) throws Exception {
		CountDownLatch countDownLatch = new CountDownLatch(6);
		 
		for (int i = 1; i <= 6; i++) {
			new Thread(()->{
				/*try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				System.out.println(Thread.currentThread().getName()+"\t 离开教室");
				countDownLatch.countDown();
			}, String.valueOf(i)).start();
		}
		countDownLatch.await();
		//countDownLatch.await(2L, TimeUnit.SECONDS);
		System.out.println(Thread.currentThread().getName()+"\t 关门离开");
	}

}
