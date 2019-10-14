package com.atguigu.semaphore;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 
 * Semaphore  信号灯
 *
 */
public class SemaphoreDemo {
	
	public static void main(String[] args) {
		
		Semaphore semaphore = new Semaphore(3); //模拟3个车位
		for (int i = 1; i <= 6; i++) {
			new Thread(()->{
				boolean flag = false;
				try {
					semaphore.acquire();
					flag = true;
					System.out.println(Thread.currentThread().getName()+"\t 抢到车位");
					//暂停3秒
					try {
						TimeUnit.SECONDS.sleep(new Random().nextInt(5));
					} catch (Exception e) {
						// TODO: handle exception
					}
					System.out.println(Thread.currentThread().getName()+"\t 离开车位");
				} catch (Exception e) {
					// TODO: handle exception
				}finally {
					if (flag) {
						semaphore.release();
					}
				}
			}, String.valueOf(i)).start();
		}
	}

}
