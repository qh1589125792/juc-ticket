package com.atguigu.blockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueDemo {
	public static void main(String[] args) throws Exception {
		BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
		
		
		//当阻塞队列满时，队列会阻塞生产者线程一定时间，超过限时后生产者线程会退出
		System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));  
		System.out.println(blockingQueue.offer("b", 3L, TimeUnit.SECONDS));  
		System.out.println(blockingQueue.offer("c", 4L, TimeUnit.SECONDS));  
		System.out.println(blockingQueue.offer("a", 5L, TimeUnit.SECONDS));  
		
		
		System.out.println(blockingQueue.poll(2L, TimeUnit.SECONDS));  
		System.out.println(blockingQueue.poll(3L, TimeUnit.SECONDS));  
		System.out.println(blockingQueue.poll(4L, TimeUnit.SECONDS));  
		System.out.println(blockingQueue.poll(5L, TimeUnit.SECONDS));  
		
		
		
		
		/*
		//阻塞:只要有错误就一直阻塞
		blockingQueue.put("a");  
		blockingQueue.put("b");  
		blockingQueue.put("c");  
		//blockingQueue.put("x");  
		
		System.out.println(blockingQueue.take());  
		System.out.println(blockingQueue.take());  
		System.out.println(blockingQueue.take());  
		System.out.println(blockingQueue.take());  
		*/
		
		
		
		
		/*
		//特殊值：有就返回true，没有就false。
		System.out.println(blockingQueue.offer("a"));  
		System.out.println(blockingQueue.offer("a"));  
		System.out.println(blockingQueue.offer("a"));  
		System.out.println(blockingQueue.offer("x")); 
		
		//有值就返回具体的值，没没查到就返回null
		System.out.println(blockingQueue.poll());  
		System.out.println(blockingQueue.poll());  
		System.out.println(blockingQueue.poll());  
		System.out.println(blockingQueue.poll()); 
		 */
		
		
		/*
		//抛出异常 只要有异常就报错
		System.out.println(blockingQueue.add("a"));
		System.out.println(blockingQueue.add("b"));
		System.out.println(blockingQueue.add("c"));
		//System.out.println(blockingQueue.add("x"));
		
		System.out.println(blockingQueue.element());
		
		System.out.println(blockingQueue.remove());
		System.out.println(blockingQueue.remove());
		System.out.println(blockingQueue.remove());
		//System.out.println(blockingQueue.remove());
		*/
		
		
	}

}
