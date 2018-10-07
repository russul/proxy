package cn.scut.yz_2;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Target;
import java.util.Properties;

import cn.scut.yz_1.Advice;
import cn.scut.yz_1.MyAdvice;

public class BeanFactory {
	// 由配置文件传入
	Properties prop = new Properties();

	public BeanFactory(InputStream ips) {
		try {
			prop.load(ips);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Object getBean(String name) {
		String className = prop.getProperty(name);
		Class clazz = null;
		Object bean = null;
		try {
			clazz = Class.forName(className);
			bean = clazz.newInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;
		if (bean instanceof ProxyFactoryBean) {
			ProxyFactoryBean proxyFactoryBean = (ProxyFactoryBean) bean;
			Advice advice=null;
			Object target=null;
			try {
				advice = (Advice)Class.forName(prop.getProperty(name + ".advice"))
						.newInstance();
				target = Class.forName(prop.getProperty(name + ".target"))
						.newInstance();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			proxyFactoryBean.setAdvice(advice);
			proxyFactoryBean.setTarget(target);

			return proxyFactoryBean.getProxy();
		}
		return bean;
	}
}
