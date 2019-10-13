package com.atguigu.threadWait;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class AirConditioner{ // 资源类

	//lock同步
	private int num = 0;
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	public void increment() throws Exception{
		lock.lock();
		try {
			//判断
			while (num != 0){
				condition.await();
			}
			//干活
			num++;
			System.out.println(Thread.currentThread().getName()+"\t"+num);
			//通知
			condition.signalAll();
		} finally {
			lock.unlock();
		}
	}
	
	public void decrement() throws Exception{
		lock.lock();
		try {
			//判断
			while (num==0){
				condition.await();
			}
			//干活
			num--;
			System.out.println(Thread.currentThread().getName()+"\t"+num);
			//通知
			condition.signalAll();
			
		} finally {
			lock.unlock();
		}
	}
	
	
	
	
	
	
	
	//synchronized方法同步
	/*private int num = 0;
	public synchronized void increment() throws Exception{
		
		//判断
		while(num != 0){
			this.wait();
		}
		//干活
		num++;
		System.out.println(Thread.currentThread().getName()+"\t"+num);
		//通知
		this.notifyAll();
	}
	
	public synchronized void decrement() throws Exception{
		//判断
		while (num==0) {
			this.wait();
		}
		//干活
		num--;
		System.out.println(Thread.currentThread().getName()+"\t"+num);
		//通知
		this.notifyAll();
	}*/
	

}




/**
 * 题目：现在两个线程，可以操作初始值为零的一个变量，
 * 	实现一个线程对该变量加1，一个线程对该变量减1，
 * 	实现交替，来10轮，变量初始值为零。
 * 	
 * 1. 高内聚低耦合的前提下，线程操作资源类
 * 2. 判断 --> 干活 -->	 通知
 * 3. 小心，防止多线程的虚假唤醒，判断的时候用while，而不用if
 * 
 * 
 * 
 */
public class ThreadWaitNotifyDemo {
	public static void main(String[] args) {
		AirConditioner airConditioner = new AirConditioner();
		new Thread(()->{
			for (int i = 1; i <=10; i++) {
				try {
					
					Thread.sleep(200);
					airConditioner.increment();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, "A").start();
		
		
		new Thread(()->{
			for (int i = 0; i <=10; i++) {
				try {
					Thread.sleep(300);
					airConditioner.decrement();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, "B").start();
		new Thread(()->{
			for (int i = 0; i <=10; i++) {
				try {
					Thread.sleep(400);
					airConditioner.increment();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, "C").start();
		new Thread(()->{
			for (int i = 0; i <=10; i++) {
				try {
					Thread.sleep(500);
					airConditioner.decrement();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, "D").start();
	}

}
