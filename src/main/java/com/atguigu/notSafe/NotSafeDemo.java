package com.atguigu.notSafe;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NotSafeDemo {
	public static void main(String[] args) {
		//用ArrayList模拟高并发
		List<String> list = new ArrayList<String>();
		for (int i = 0;i<=30; i++) {
			new Thread(()->{
				list.add(UUID.randomUUID().toString().substring(0, 6));
				System.out.println(list);
				
			}, String.valueOf(i)).start();
		}
		
	}

}
