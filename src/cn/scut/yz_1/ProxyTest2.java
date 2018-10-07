package cn.scut.yz_1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import sun.misc.ProxyGenerator;

//ֱ����Proxy��static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h) 
//������̬�������ʵ�����
public class ProxyTest2 {
	public static void main(String[] args) {
		Collection proxyCollection = (Collection) Proxy.newProxyInstance(
				Collection.class.getClassLoader(),
				new Class[] { Collection.class }, new InvocationHandler() { // ���췽��Ҫ����һ��InvocationHandler���ͣ��������ǽӿڣ�ͨ�������ڲ��ഫ������������
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

		// proxyCollection.add("adhj");
		Integer size = proxyCollection.size();
		System.out.println(size);
		// ProxyGeneratorUtils.writeProxyClassToHardDisk("$Proxy0","G:/$Proxy0.class");
	}
}

class ProxyGeneratorUtils {

	public static void writeProxyClassToHardDisk(String proxyName, String path) {
		// 获取代理类的字节码方式一
		// System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles",
		// true);
		// 方式二
		byte[] classFile = ProxyGenerator.generateProxyClass(proxyName,
				ArrayList.class.getInterfaces());
		FileOutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(path);
			outputStream.write(classFile);
			outputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				outputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
