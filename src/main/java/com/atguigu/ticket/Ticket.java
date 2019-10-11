package com.atguigu.ticket;

public class Ticket implements Runnable {
	private int ticket = 30;

	public void run() {
		// TODO Auto-generated method stub
		//窗口永远开启
		while(true) {
			synchronized (this) {
				if(ticket>0) {
	
					//获取当前对象的名字
					String name = Thread.currentThread().getName();
					System.out.println(name + "正在卖：" + ticket-- + "还剩余：" + ticket);
				}
			}
		}
		
	}
	

}
