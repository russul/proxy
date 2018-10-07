package cn.scut.yz_2;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

import cn.scut.yz_1.MyAdvice;

public class AopFrameworkTest {
	public static void main(String[] args) {
		InputStream ips = AopFrameworkTest.class
				.getResourceAsStream("config.properties");
		BeanFactory bf = new BeanFactory(ips);
		Object bean = bf.getBean("xxx");
		((Collection)bean).add("aja");
		
		System.out.println(bean.getClass().getName());

	}
}
