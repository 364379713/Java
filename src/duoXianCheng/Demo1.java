package duoXianCheng;

/*���̷߳���
 * 1���̳�Thread��
 * 2�����า�Ǹ����е�run����,���س����еĴ�������run��
 * 3��������������ͬʱ�س�ҳ������
 * 4��ͨ������start���������߳�
 * 
 * 
 * */
class Test extends Thread{
	public void run(){
		for(int x=0;x<60;x++){
			System.out.println("test run.."+x);
		}
	}
}

public class Demo1 {

	public static void main(String[] args) {
		Test t1 = new Test();
		Test t2 = new Test();
		t1.start();
		t2.start();
		for(int x=0;x<60;x++){
			System.out.println("main....."+x);
		}
	}

}
