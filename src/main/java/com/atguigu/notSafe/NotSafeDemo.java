package com.atguigu.notSafe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * 1.故障现象 
 * 	java.util.ConcurrentModificationException 
 * 2.导致原因
 * 	底层add方法上没有synchronized 3.解决方法 3.1 vertor() 3.2 Collections.synchronizedList()
 * 3.3 CopyOnWriteArrayList ---> 读写分离，写时复制
 * 
 * 4.优化建议
 */
/*
 * 写时复制
 * CopyOnWrite容器即写时复制的容器。往一个容器添加元素的时候，不直接往当前容器Object[]添加，而是先将当前容器Object[]进行Copy，
 * 复制出一个新的容器Object[] newElements，然后新的容器Object[] newElements里添加元素，添加完元素之后，
 * 再将原容器的引用指向新的容器 setArray(newElements);。这样做的好处是可以对CopyOnWrite容器进行并发的读，
 * 而不需要加锁，因为当前容器不会添加任何元素。所以CopyOnWrite容器也是一种读写分离的思想，读和写不同的容器 public boolean
 * public boolean add(E e)
 {
 final ReentrantLock lock = this.lock;

 lock.lock();

 try{
 Object[] ele ments = getArray();
 int len = elements.length;
 Object[] newElements = Arrays.copyOf(elements, len + 1);
 newElements[len] = e;
 setArray(newElements);
 return true;
 }
 finally {
 lock.unlock();
 }
 }
 */

public class NotSafeDemo {
	public static void main(String[] args) {
		// 用ArrayList模拟高并发
		// List<String> list = new ArrayList<String>();
		// List<String> list = new Vector<>();
		// List<String> list = Collections.synchronizedList(new ArrayList<>());
		List<String> list = new CopyOnWriteArrayList<String>();
		for (int i = 1; i <= 3; i++) {
			new Thread(() -> {
				list.add(UUID.randomUUID().toString().substring(0, 6));
				System.out.println(list);

			}, String.valueOf(i)).start();
		}
	}

	/*
	 * hashSet底层是HashMap,在源码中HashSet主要使用的是map的key键，而value值则是固定的常量
	 */
	public static void setNotSafe() {
		// Set<String> set = new HashSet<>();
		// Set<String> set = Collections.synchronizedSet(new HashSet<>());
		Set<String> set = new CopyOnWriteArraySet<>();
		for (int i = 1; i <= 3; i++) {
			new Thread(() -> {
				set.add(UUID.randomUUID().toString().substring(0, 6));
				System.out.println(set);

			}, String.valueOf(i)).start();
		}
	}

	public static void mapNotSafe() {
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 1; i <= 30; i++) {
			new Thread(() -> {
				map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 6));
				System.out.println(map);
			}, String.valueOf(i)).start();
		}
	}

}
