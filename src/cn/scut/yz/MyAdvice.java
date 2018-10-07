package cn.scut.yz;

public class MyAdvice implements Advice {
	private long startTime;
	private long endTime;

	@Override
	public void beforeMethod() {
		startTime = System.currentTimeMillis();
		System.out.println("beforeMethod#start time:" + startTime);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void afterMethod() {
		endTime = System.currentTimeMillis();
		System.out.println("afterMethod#end time:" + endTime);

	}

}
