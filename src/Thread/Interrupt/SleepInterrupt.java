package Thread.Interrupt;

/**
 * ʹ�� interrupt()�ж��̵߳�һ���߳�����ʱ����һ���߳̿��Ե��ö�Ӧ�� Thread ����� interrupt()�������ж�����
 * �÷���ֻ����Ŀ���߳�������һ����־����ʾ���Ѿ����жϣ����������ء�������Ҫע����ǣ�
 * ���ֻ�ǵ����ĵ��� interrupt()�������̲߳�û��ʵ�ʱ��жϣ����������ִ�С�
 * 
 * ���߳��������̺߳��������� 2 ���ӣ��������̻߳������ʱ�䡣���̴߳�ӡ��Ϣabout to sleep for 20 seconds��
 * �̶����� 20 ���ӣ���Լ 2 ���Ӻ�main �߳�֪ͨ���߳��жϣ���ô���̵߳� 20 ������߽�����ϣ�
 * �Ӷ��׳� InterruptException �쳣��ִ����ת�� catch �飬��ӡ��interrupted while sleeping��Ϣ��
 * �������� run�����������أ�Ȼ���������������ӡ�� catch ������leaving normally��Ϣ��
 * ��ע�⣺���ڲ�ȷ�����̹߳滮����ͼ���н���ĺ����п���˳���෴����ȡ�������̺߳����߳��ĸ���������
 * ��ǰ������Ϣ��˳��ض�����ͼ��ʾ��
 * ���⣬����� catch ���е� return ���ע�͵���
 * ���߳����׳��쳣�󣬻��������ִ�У������ᱻ�жϣ��Ӷ����ӡ��leaving normally��Ϣ��
 * 
 */
public class SleepInterrupt extends Object implements Runnable{  
    public void run(){  
        try{  
            System.out.println("in run() - about to sleep for 20 seconds");  
            Thread.sleep(20000);  
            System.out.println("in run() - woke up");  
        }catch(InterruptedException e){  
            System.out.println("in run() - interrupted while sleeping");  
            //�������ж��쳣�󣬷��ص�run���������˿ڣ�  
            //���û��return���̲߳���ʵ�ʱ��жϣ����������ӡ�������Ϣ  
            return;    
        }  
        System.out.println("in run() - leaving normally");  
    }  

    public static void main(String[] args) {  
        SleepInterrupt si = new SleepInterrupt();  
        Thread t = new Thread(si);  
        t.start();  
        //���߳�����2�룬�Ӷ�ȷ���ղ��������߳��л���ִ��һ��ʱ��  
        try {  
            Thread.sleep(2000);   
        }catch(InterruptedException e){  
            e.printStackTrace();  
        }  
        System.out.println("in main() - interrupting other thread");  
        //�ж��߳�t  
        t.interrupt();  
        System.out.println("in main() - leaving");  
    }  
} 