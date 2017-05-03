package Shengchanzhe2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*���ڶ�������ߺ�������
 * ΪʲôҪ����while�жϱ�ǡ�
 * ԭ��:�ñ����ѵ��߳���һ���жϱ�ǡ�
 * 
 * Ϊʲô����notifyAll
 * ��Ϊ��Ҫ���ѶԷ��̡߳�
 * ��Ϊֻ��notify,���׳���ֻ���ѱ����̵߳���������³����е������̶߳��ȴ�
 * 
 * t1.setDaemon(true);�ػ��߳�
 * 
 * */
public class ProduceConsumerDemo2 {
	public static void main(String[] args){
		Resource r = new Resource();
		Producer pro = new Producer(r);
		Consumer con = new Consumer(r);
		
		Thread t1 = new Thread(pro);
		Thread t2 = new Thread(pro);
		Thread t3 = new Thread(con);
		Thread t4 = new Thread(con);
//		t1.setDaemon(true);
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}
}
/*JDk1.5 ���ṩ�˶��̵߳Ľ������
 * ��ͬ��Synchronized�滻����ʾ��Lock����
 * ��Object�е�wait��notify��notifyAll���滻��Condition����
 * �ö������Lock�������л�ȡ
 * ��ʾ���У�ʵ���˱���ֻ���ѶԷ��Ĳ���
 * �ͷ����Ķ���һ��Ҫִ�����Է���finally��
 * */

class Resource{
	private String name;
	private int count = 1;
	private boolean flag=false;
	
	private Lock lock = new ReentrantLock();
	private Condition condition_con = lock.newCondition();
	private Condition condition_pro = lock.newCondition();
	public void set(String name) throws InterruptedException{
		lock.lock();
		try{
			while(flag)
			condition_pro.await();
			this.name = name+"--"+count++;
			System.out.println(Thread.currentThread().getName()+"...������"+this.name);
			flag = true;
			condition_con.signal();
		}finally{
			lock.unlock(); 
		}
	}
	public void out() throws InterruptedException{
		lock.lock();
		try{
			while(!flag)
			condition_con.await();
			System.out.println(Thread.currentThread().getName()+"...������...."+this.name);
			flag = false;
			condition_pro.signal();
		}finally{
			lock.unlock();
		}
	}
}

class Producer implements Runnable{
	private Resource res;
	Producer(Resource res){
		this.res = res;
	}
	public void run(){
		while(true){
			try {
				res.set("+��Ʒ+");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}


class Consumer implements Runnable{
	private Resource res;
	Consumer(Resource res){
		this.res = res;
	}
	public void run(){
		while(true){
			try {
				res.out();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}