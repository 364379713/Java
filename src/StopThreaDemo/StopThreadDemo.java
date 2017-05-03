package StopThreaDemo;
/*
 * stop�����Ѿ���ʱ
 * 
 * ���ֹͣ�߳�?
 * ֻ��һ�֣�run��������
 * �������߳����У����д���ͨ����ѭ���ṹ��
 * 
 * ֻҪ����סѭ�����Ϳ�����run����������Ҳ�����߳̽���
 * 
 * �������:���̳߳��ڶ���״̬�Ͳ����ȡ����ǡ���ô�߳̾Ͳ���ֹͣ
 * 
 * */

class StopThread implements Runnable{
	private boolean flag = true;
	public synchronized void run(){
		while(flag){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println(Thread.currentThread().getName()+"....Exception");
				flag = false;
			}
			System.out.println(Thread.currentThread().getName()+"...run");
		}
	}
	public void changeFlag(){
		this.flag = false;
	}
}
public class StopThreadDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StopThread st = new StopThread();
		Thread t1 = new Thread(st);
		Thread t2 = new Thread(st);
		t1.start();
		t2.start();
		
		int num = 0;
		while(true){
			if(num++ ==60){
				st.changeFlag();
				t1.interrupt();
				t2.interrupt();
				break;
			}
			System.out.println(Thread.currentThread().getName()+"...."+num);
		}
	}

}
