package duoXianCheng;

/*
 * ͬ����������synchronized�ŵ����������η��ϾͿ�����
 * ������Ҫ��������ã���ô��������һ�������������á�����this
 * ����ͬ������ʹ�õ�����this.
 * ʹ�������߳�����Ʊ��
 * һ���߳���ͬ���������.
 * һ���߳���ͬ�������С�
 * ����ִ����Ʊ����
 * 
 * ���ͬ����������̬���κ�ʹ�õ�����ʲô��?
 * ͨ����֤�����ֲ�����this ����Ϊ��̬������ҳ�����Զ���this
 * ��̬���ڴ�ʱ���ڴ���û�б�����󣬵���һ���и����Ӧ���ֽ����ļ���������.class �ö���������.class
 * ��̬��ͬ��������ʹ�õ����Ǹ÷�����������ֽ����ļ���������.class
 * 
 * */
class Bank{
	private int sum;
	public synchronized void add(int n){
		sum = sum + n;
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("sum="+sum);
	}
}

class Cus implements Runnable{
	private Bank b = new Bank();
	public void run(){
		for(int x=0;x<3;x++){
			b.add(100);
		}
	}
}
public class Demo3 {
	public static void main(String[] args){
		Cus c = new Cus();
		Thread t1 = new Thread(c);
		Thread t2 = new Thread(c);
		t1.start();
		t2.start();
	}
}
