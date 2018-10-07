package cn.scut.yz_1;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collection;

public class ProxyTest {
	public static void main(String[] args) throws Exception {

		// ��Ľӿ��Ǽ��ϣ���Ҫ��������һ�����ϵĴ�����
		// 1�� ͨ�� Proxy.getProxyClass��������һ����̬������
		Class clazzProxy = Proxy.getProxyClass(
				Collection.class.getClassLoader(), Collection.class);
		// System.out.println(clazzProxy.getName());// �������
		Constructor[] constructorProxy = clazzProxy.getConstructors();
		// 2������ʵ�����

		// newInstance();��ʱ�����ڴ�����Ĺ��췽�����в���ģ�����Class����ֱ���ã�Ҫ���þ��幹�췽����newInstance(Object...
		// initargs)

		Collection proxy1 = (Collection) constructorProxy[0] // ��Ϊ֪�������Ĵ�������Collection���͵ģ����Խ�������ת��
				.newInstance(new InvocationHandler() { // ���췽��Ҫ����һ��InvocationHandler���ͣ��������ǽӿڣ�ͨ�������ڲ��ഫ������������
					Collection target = new ArrayList();
					Advice advice = new MyAdvice();

					@Override
					public Object invoke(Object proxy, Method method,
							Object[] args) throws Throwable {
						advice.beforeMethod();

						Object retVal = method.invoke(target, args);
						advice.afterMethod();
						return retVal;
					}

				});

		// 3�����ô���ķ���

		proxy1.add("ajd");// ȥ����InvocationHandler���Invoke����
		

		// Class<?>[] getParameterTypes() Constructor�෽��
		// ��������˳�򷵻�һ�� Class ������Щ�����ʾ�� Constructor
		// �������ʾ���췽�����β����͡�

		// for (Constructor constructor : constructorProxy) {
		// String name = constructor.getName();
		// StringBuilder sb = new StringBuilder(name);
		// Class[] classParam = constructor.getParameterTypes();
		// sb.append('(');
		// for (Class classparam : classParam) {
		// sb.append(classparam.getName()).append(',');
		// }
		// sb.deleteCharAt(sb.length() - 1);
		// sb.append(')');
		// System.out.println(sb.toString()); // ������췽��
		//
		// }
		// ����Ĵ����ú�����Ĵ���Ч����һ���
		// for (Constructor constructor : constructorProxy) {
		// System.out.println(constructor.toString());
		// }
		// // ����������뻹�������������з���
		//
		// Method[] methods = clazzProxy.getDeclaredMethods();
		// for (Method method : methods) {
		// String name = method.getName();
		// StringBuilder sb = new StringBuilder(name);
		//
		// Class[] classParam = method.getParameterTypes();
		//
		// sb.append('(');
		//
		// for (Class classparam : classParam) {
		// sb.append(classparam.getName()).append(',');
		// }
		// if (classParam != null && classParam.length != 0)
		// sb.deleteCharAt(sb.length() - 1);
		// sb.append(')');
		// System.out.println(sb.toString()); // ������췽��
		//
		// }

	}

}
