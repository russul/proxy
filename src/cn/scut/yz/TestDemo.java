package cn.scut.yz;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TestDemo {
	public static void main(String[] args) {
		Human studentProxy= (Human)Proxy.newProxyInstance(Student.class.getClassLoader(),Student.class.getInterfaces(),new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				// TODO Auto-generated method stub
				Student target = new Student();
				Advice advice = new MyAdvice();
				
				advice.beforeMethod();
				Object retVal=method.invoke(target,args);
				advice.afterMethod();
				return retVal;
			}
		});
		
		
		
		studentProxy.work();
		ProxyGeneratorUtils.writeProxyClassToHardDisk("$Proxy#student","G:/$Proxy#student.class",Student.class);
	}
}
