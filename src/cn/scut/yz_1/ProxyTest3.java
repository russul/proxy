package cn.scut.yz_1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
//采用框架的思想，将系统功能（Advice）和目标功能（Target）做成一个类传进InvacationHandler,这样别人就可以通过修改这两个类来动态的完成不同的功能
import java.util.Collection;

public class ProxyTest3 {
	public static void main(String[] args) {
		try {
			final Collection target = new ArrayList();
			final MyAdvice myAdvice = new MyAdvice();
			Collection proxyCollection = (Collection) getProxy(target, myAdvice);

			proxyCollection.add("您好");
			proxyCollection.add("哈哈");
			System.out.println(proxyCollection.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static Object getProxy(final Object target, final MyAdvice myAdvice)
			throws Exception {

			Object proxyObject = Proxy.newProxyInstance(
				target.getClass().getClassLoader(), 
				target.getClass().getInterfaces(),
				new InvocationHandler() { // 构造方法要接收一个InvocationHandler类型，由于他是接口，通过匿名内部类传入他的子类对象

					@Override
					public Object invoke(Object proxy, Method method,
							Object[] args) throws Throwable {
						myAdvice.beforeMethod();
						Object retVal = method.invoke(target, args);
						myAdvice.afterMethod();

						return retVal;
					}

				});
			return proxyObject;

	}
}
