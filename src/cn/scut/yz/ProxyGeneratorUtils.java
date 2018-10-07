package cn.scut.yz;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import sun.misc.ProxyGenerator;

public class ProxyGeneratorUtils {
	public static void writeProxyClassToHardDisk(String proxyName, String path,
			Class target) {
		// 获取代理类的字节码方式一
		// System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles",
		// true);
		// 方式二
		byte[] classFile = ProxyGenerator.generateProxyClass(proxyName,
				target.getInterfaces());
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
