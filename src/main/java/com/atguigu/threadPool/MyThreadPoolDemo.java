package com.atguigu.threadPool;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyThreadPoolDemo {
	public static void main(String[] args) {
		
		ExecutorService executorService = new ThreadPoolExecutor(
				2, 
				5, 
				2L, 
				TimeUnit.SECONDS, 
				new ArrayBlockingQueue<>(3), 
				Executors.defaultThreadFactory(), 
				new ThreadPoolExecutor.DiscardOldestPolicy());
		
		
		
		
		
//		ExecutorService executorService = Executors.newFixedThreadPool(5); //一池5线程
//		ExecutorService executorService = Executors.newSingleThreadExecutor(); //一池1线程
//		ExecutorService executorService = Executors.newCachedThreadPool(); //一池N线程
		
		try {
			for (int i = 1; i <= 10; i++) {
				executorService.execute(()->{
					System.out.println(Thread.currentThread().getName()+"\t 办理业务 " + new Random().nextInt());
				});
			}
		} finally {
			executorService.shutdown();
		}
		
	}

}
