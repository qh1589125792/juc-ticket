package com.atguigu.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;


/*class MyThread implements Callable<String>{

	@Override
	public String call() throws Exception {
		System.out.println(Thread.currentThread().getName()+"\t ******come in call");
		return "0615";
	}
}*/

/**
 * 第三种获得多线程的方法 
 * 	Callable: 异步编程思想
 * 	适配器模式：
 *    1.get 方法一般请放在最后一行
 *
 */
public class CallableDemo {
	
	public static void main(String[] args) throws InterruptedException , ExecutionException {
		
//		FutureTask<String> futureTask = new FutureTask<>(new MyThread());
		//匿名内部类
		/*FutureTask<String> futureTask = new FutureTask(new Callable<String>() {
	
			@Override
			public String call() throws Exception {
				System.out.println(Thread.currentThread().getName()+"\t ******come in call");
				return "0615";
			}
			
		});*/
		
		//lambda表达式
		FutureTask<String> futureTask = new FutureTask(()-> {
				System.out.println(Thread.currentThread().getName()+"\t ******come in call");
				return "0615";
			
			
		});
		new Thread(futureTask, "AAA").start();
		
		
		String result = futureTask.get();
		System.out.println(result);
		
		System.out.println("***********主线程：\t" + Thread.currentThread().getName());
	}

}
