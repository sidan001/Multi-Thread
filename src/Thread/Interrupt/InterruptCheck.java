package Thread.Interrupt;

/**
 * ʹ�� isInterrupted()�����ж��ж�״̬:
 * ������ Thread �����ϵ��� isInterrupted()����������κ��̵߳��ж�״̬��
 * ������Ҫע�⣺�߳�һ�����жϣ�isInterrupted()������᷵�� true����һ�� sleep()�����׳��쳣��
 * ��������жϱ�־����ʱisInterrupted()���������� false��
 * @author chou
 *
 */
public class InterruptCheck extends Object{  
    public static void main(String[] args){  
        Thread t = Thread.currentThread();  
        System.out.println("Point A: t.isInterrupted()=" + t.isInterrupted());  
        //�����жϣ��ж�����  
        t.interrupt();  
        System.out.println("Point B: t.isInterrupted()=" + t.isInterrupted());  
        System.out.println("Point C: t.isInterrupted()=" + t.isInterrupted());  

        try{  
            Thread.sleep(2000);  
            System.out.println("was NOT interrupted");  
        }catch( InterruptedException x){  
            System.out.println("was interrupted");  
        }  
        //�׳��쳣�󣬻�����жϱ�־������᷵��false  
        System.out.println("Point D: t.isInterrupted()=" + t.isInterrupted());  
    }  
}  