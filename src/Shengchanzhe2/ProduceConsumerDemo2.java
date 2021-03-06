package Shengchanzhe2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*对于多个生产者和消费者
 * 为什么要定义while判断标记。
 * 原因:让被唤醒的线程再一次判断标记。
 * 
 * 为什么定义notifyAll
 * 因为需要唤醒对方线程。
 * 因为只用notify,容易出现只唤醒奔放线程的情况。导致程序中的所有线程都等待
 * 
 * t1.setDaemon(true);守护线程
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
/*JDk1.5 中提供了多线程的解决方案
 * 将同步Synchronized替换成显示的Lock操作
 * 将Object中的wait，notify，notifyAll，替换了Condition对象
 * 该对象可以Lock锁，进行获取
 * 该示例中，实现了本方只唤醒对方的操作
 * 释放锁的动作一定要执行所以放在finally中
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
			System.out.println(Thread.currentThread().getName()+"...生产者"+this.name);
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
			System.out.println(Thread.currentThread().getName()+"...消费者...."+this.name);
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
				res.set("+商品+");
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