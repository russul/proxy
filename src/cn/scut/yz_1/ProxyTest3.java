package cn.scut.yz_1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
//���ÿ�ܵ�˼�룬��ϵͳ���ܣ�Advice����Ŀ�깦�ܣ�Target������һ���ഫ��InvacationHandler,�������˾Ϳ���ͨ���޸�������������̬����ɲ�ͬ�Ĺ���
import java.util.Collection;

public class ProxyTest3 {
	public static void main(String[] args) {
		try {
			final Collection target = new ArrayList();
			final MyAdvice myAdvice = new MyAdvice();
			Collection proxyCollection = (Collection) getProxy(target, myAdvice);

			proxyCollection.add("����");
			proxyCollection.add("����");
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
				new InvocationHandler() { // ���췽��Ҫ����һ��InvocationHandler���ͣ��������ǽӿڣ�ͨ�������ڲ��ഫ�������������

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
