package cn.scut.yz_2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import cn.scut.yz_1.*;

public class ProxyFactoryBean {
	private  Object target = null;
	private  Advice advice = null;

	public Object getProxy() {
		Object proxyObject = Proxy.newProxyInstance(target.getClass()
				.getClassLoader(), target.getClass().getInterfaces(),
				new InvocationHandler() {

					@Override
					public Object invoke(Object proxy, Method method,
							Object[] args) throws Throwable {
						advice.beforeMethod();
						Object retVal = method.invoke(target, args);
						advice.afterMethod();
						return retVal;
					}
				});
		return proxyObject;

	}

	public void setTarget(Object target) {
		this.target = target;
	}

	public void setAdvice(Advice advice) {
		this.advice = advice;
	}

	public Object getTarget() {
		return target;
	}

	public Advice getAdvice() {
		return advice;
	}

	
	
	
	

	// public ProxyFactoryBean(final Object target, final MyAdvice advice) {
	// Object proxyObject = Proxy.newProxyInstance(target.getClass()
	// .getClassLoader(), target.getClass().getInterfaces(),
	// new InvocationHandler() {
	//
	// @Override
	// public Object invoke(Object proxy, Method method,
	// Object[] args) throws Throwable {
	// // TODO Auto-generated method stub
	// return null;
	// }
	// });
	// }
}
