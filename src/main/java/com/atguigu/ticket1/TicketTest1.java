package com.atguigu.ticket1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket1 {
	/*private int number = 30;

	public synchronized void sale() {
		if (number > 0) {
			System.out.println(Thread.currentThread().getName() + " 卖出第：" + (number--) + ", 还剩下：" + number);
		}
	}*/
	
	private int number = 30;
	Lock lock = new ReentrantLock();
	
	public void sale() {
		lock.lock();
		try {
			if(number>0) {
				System.out.println(Thread.currentThread().getName() + " 卖出第：" + (number--) + ", 还剩下：" + number);
				
			}
		} finally {
			lock.unlock();
		}
	}
			
	
}

/**
 * 高内聚低耦合 线程 操作 资源类
 * 
 *
 */

public class TicketTest1 {

	public static void main(String[] args) {

		/*Ticket1 ticket = new Ticket1();
		
		new Thread(() -> {for (int i = 1; i <= 40; i++) ticket.sale();}, "A").start();
		new Thread(() -> {for (int i = 1; i <= 40; i++) ticket.sale();}, "B").start();
		new Thread(() -> {for (int i = 1; i <= 40; i++) ticket.sale();}, "C").start();*/
		
		
		//一池3线程
		Ticket1 ticket = new Ticket1();
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		
		for (int i =1; i <=30; i++) {
			executorService.submit(()->{
				ticket.sale();
			});
		}
		executorService.shutdown();
		
		
		
	
		
		
		
		
		
		/*new Thread(new Runnable() {

			public void run() {
				for (int i = 1; i <= 40; i++) {
					ticket.sale();
				}

			}
		}, "A").start();

		new Thread(new Runnable() {

			public void run() {
				for (int i = 1; i <= 40; i++) {
					ticket.sale();
				}

			}
		}, "B").start();
		new Thread(new Runnable() {
			
			public void run() {
				for (int i = 1; i <= 40; i++) {
					ticket.sale();
				}
				
			}
		}, "C").start();
*/
	}

}
