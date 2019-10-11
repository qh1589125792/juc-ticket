package com.atguigu.lambda1;


@FunctionalInterface
interface Foo{
//	public void add();
	public int add(int a,int b);
	
	//default方法
	default int div(int a ,int b) {
		return a/b;
	}
	
	//static方法
	static int mul(int a,int b) {
		return a*b;
	}
	
	
	
	
}


/**
 * 2  lambda Express （前提是函数式接口，只有一个方法）
 * 	2.1 拷贝小括号，写死右箭头，落地大括号
 * 	2.2 @FunctionalInterface 函数是接口
 * 	2.3 default方法
 * 	2.4 static方法    类名.方法名
 * @author QH
 *
 */

public class LambdaDemo {
	
	public static void main(String[] args) {
		
		Foo foo = (int a,int b) -> {
			System.out.println("lambda 表达式");
			return a+b;
		};
		System.out.println(foo.add(3, 5));
		System.out.println(foo.div(10, 5));
		System.out.println(Foo.mul(8, 5));
	}

}
