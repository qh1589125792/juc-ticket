package com.atguigu.access;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareResource{
	private int flag = 1;
	private Lock lock = new ReentrantLock();
	private Condition condition1 = lock.newCondition();
	private Condition condition2 = lock.newCondition();
	private Condition condition3 = lock.newCondition();
	
	public void print5(){
		lock.lock();
		try {
			//判断
			while(flag != 1){
				condition1.await();
			}
			//干活
			for (int i = 1; i <= 5; i++) {
				System.out.println(Thread.currentThread().getName()+"\t"+i);
			}
			//通知
			flag = 2;       //标志位一定要更新
			condition2.signal();
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	public void print10(){
		lock.lock();
		try {
			//判断
			while(flag != 2){
				condition2.await();
			}
			//干活
			for (int i = 1; i <= 10; i++) {
				System.out.println(Thread.currentThread().getName()+"\t"+i);
			}
			//通知
			flag = 3;       //标志位一定要更新
			condition3.signal();
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	public void print15(){
		lock.lock();
		try {
			//判断
			while(flag != 3){
				condition3.await();
			}
			//干活
			for (int i = 1; i <= 15; i++) {
				System.out.println(Thread.currentThread().getName()+"\t"+i);
			}
			//通知
			flag = 1;       //标志位一定要更新
			condition1.signal();
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}

/**
 * 多线程席间按顺序调用，实现A->B->C
 *	三个线程启动，要求如下：
 *	A打印5次，B打印10次，C打印15 次      打印10轮
 *
 *	1. 高内聚低耦合前提下，线程操作资源类
 *	2. 判断/干活/通知
 *	3. 多线程交互中，必须要防止多线程的虚假唤醒，也即（判断只用while，不能用if）
 *	4. 注意判断标志位的更新
 *
 */
public class ThreadOrderAccess {
	
	public static void main(String[] args) {
		
		ShareResource s1 = new ShareResource();
		new Thread(()->{
			for (int i = 1; i <= 10; i++) {
				s1.print5();
			}
		}, "A").start();
		
		
		new Thread(()->{
			for (int i = 1; i <= 10; i++) {
				s1.print10();
			}
		}, "B").start();
		
		
		
		new Thread(()->{
			for (int i = 1; i <= 10; i++) {
				s1.print15();
			}
		}, "C").start();
	}

}
