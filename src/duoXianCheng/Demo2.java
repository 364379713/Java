package duoXianCheng;

/*
 * �����̵߳ĵڶ��ַ�ʽ:ʵ��Runable�ӿ�
 * 1��������ʵ��Runnable�ӿ�
 * 2������Runnable�ӿ��е�run����(���߳�Ҫ���еĴ������ڸ�run������)
 * 3��ͨ��Thread�ཨ���̶߳���
 * 4����Runnable�ӿڵ����������Ϊʵ�ʲ������ݸ�Thread��Ĺ��캯��
 * 5������Thread���start���������̲߳�����Runnable�ӿ������run����
 * 
 * 
 * 
 * ���ַ����ô������Լ̳б���࣬java���̳е������Ҫ��Ȼֻ�ܼ̳�Thread��
 * ���ⵥ�̳еľ����ԣ��ڶ����߳�ʱ������ʹ��Runable
 * 
 * ���ַ�ʽ������
 * �̳�Thread:�̴߳�����Thread�����run������
 * ʵ��Runnable,�̴߳������ڽӿ������run������
 * 
 * ͨ�����ģ����֣���ӡ�� 0�� -1  ��-2�ȴ�Ʊ
 * ���������г����˰�ȫ����(д���̵߳İ�ȫ����);
 * �����ԭ��:����������ڲ���ͬһ���̹߳�������ʱ,һ���̶߳Զ������ִֻ����һ���֣���û��
 * ִ���꣬��һ���̲߳������ִ�С������˹������ݵĴ���
 * 
 * ����취�Զ��������������ݵ���䣬ֻ����һ���̶߳�ִ���ꡣ��ִ�й����У������̲߳����Բ�������
 * 
 * java�Զ��̵߳İ�ȫ�����ṩ��רҵ�Ľ����ʽ����ͬ�������
 * synchronized(����){��Ҫ��ͬ���Ĵ���} ��������������Բ�ʹ��ͬ����
 * ������ͬ�������������߳̿�����ͬ����ִ��
 * û�г��������̼߳�ʱ��ȡcpu��ִ��Ȩ��Ҳ����ȥ����Ϊû�л�ȡ��;
 * 
 * ͬ����ǰ��:1������Ҫ�����������������ϵ��߳�.
 * 2�������Ƕ���߳�ʹ��ͬһ������
 * 
 * ���뱣֤ͬ����ֻ����һ���߳�������
 * synchronized()ͬ���ô�:����˰�ȫ���⣬�׶ˣ���Ҫ�ж���״̬��������Դ
 * 
 * */
class Ticket1 implements Runnable{
	Object obj = new Object();
	private int tick = 100;
	public void run(){
		while(true){
			//ͬ�������
			synchronized (obj) {
				if(tick>0){
					try {
						Thread.sleep(10);
					} catch (InterruptedException e){
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread()+"....sale"+tick--);
				}
			}	
		}
	}
}

public class Demo2{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Ticket1 t = new Ticket1();
		
		Thread t1= new Thread(t);
		Thread t2= new Thread(t);
		Thread t3= new Thread(t);
		Thread t4= new Thread(t);
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}

}
