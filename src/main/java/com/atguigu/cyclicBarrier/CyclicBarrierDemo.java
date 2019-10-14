package com.atguigu.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

//CyclicBarrier（循环屏障） 
public class CyclicBarrierDemo {
	
	public static void main(String[] args) {
		CyclicBarrier cyclicBarrier = new CyclicBarrier(7, ()->{System.out.println("集齐7颗龙珠，召唤神龙");});
		for (int i = 1; i <= 7; i++) {
			int finalI = i;
			new Thread(()->{
				try {
					System.out.println(Thread.currentThread().getName()+"\t收集到第"+ finalI+"\t");
					cyclicBarrier.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			},String.valueOf(i)).start();
		}
	}

}
