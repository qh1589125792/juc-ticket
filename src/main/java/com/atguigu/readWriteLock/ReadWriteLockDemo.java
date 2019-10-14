package com.atguigu.readWriteLock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache{
	private volatile Map<String, String> map = new HashMap<String, String>();
	
	
	//读写锁
	private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	public void put(String key ,String value){
		
		readWriteLock.writeLock().lock();
		try {
			System.out.println(Thread.currentThread().getName()+"\t 写入开始");
			map.put(key, value);
			System.out.println(Thread.currentThread().getName()+"\t 写入结束");
		} finally {
			readWriteLock.writeLock().unlock();
		}
	}
	
	public void get(String key){
		
		readWriteLock.readLock().lock();
		try {
			String result = null;
			System.out.println(Thread.currentThread().getName()+"\t 读取开始");
			result = map.get(key);
			System.out.println(Thread.currentThread().getName()+"\t 读取结束" + result);
		} finally {
			readWriteLock.readLock().unlock();
		}
	}
	
	
	
	
	
	
	
	/* private Lock lock = new ReentrantLock();
	 * public void put(String key ,String value){
		//不加锁
		System.out.println(Thread.currentThread().getName()+"\t 写入开始");
		map.put(key, value);
		System.out.println(Thread.currentThread().getName()+"\t 写入结束");
		
		lock.lock();
		try {
			System.out.println(Thread.currentThread().getName()+"\t 写入开始");
			map.put(key, value);
			System.out.println(Thread.currentThread().getName()+"\t 写入结束");
		} finally {
			lock.unlock();
		}
	}
	
	public void get(String key){
		
		//不加锁
		String result = null;
		System.out.println(Thread.currentThread().getName()+"\t 读取开始");
		result = map.get(key);
		System.out.println(Thread.currentThread().getName()+"\t 读取结束" + result);
		
		
		
		
		lock.lock();
		try {
			String result = null;
			System.out.println(Thread.currentThread().getName()+"\t 读取开始");
			result = map.get(key);
			System.out.println(Thread.currentThread().getName()+"\t 读取结束" + result);
		} finally {
			lock.unlock();
		}
	}*/
	
	
	
}

/**
 * 
 *多个线程同时读一个资源类没有任何问题， 所以为了满足并发量，读取共享资源应该可以同时进行。
 *但是
 *如果有一个线程想去写共享资源，就不应该再有其他线程可以对资源进行读或写
 *
 *小总结：
 *		读-读能共存
 *		读-写不能共存
 *		写-写不能共存
 *
 */
public class ReadWriteLockDemo {
	public static void main(String[] args) {
		MyCache myCache = new MyCache();
		for (int i = 1; i <= 10; i++) {
			int finalI = i;
			new Thread(()->{
				myCache.put(finalI+"", finalI+"");
			}, String.valueOf(i)).start();
			
		}
		
		
		for (int i = 1; i <= 10; i++) {
			int finalI = i;
			new Thread(()->{
				myCache.get(finalI+"");;
			}, String.valueOf(i)).start();
			
		}
	}

}
