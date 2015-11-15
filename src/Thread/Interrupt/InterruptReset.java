package Thread.Interrupt;

/**
 * ʹ�� Thread.interrupted()�����ж��ж�״̬
 * ����ʹ�� Thread.interrupted()��������鵱ǰ�̵߳��ж�״̬������ʽ����Ϊ false����
 * ���������Ǿ�̬��������˲������ض����߳���ʹ�ã���ֻ�ܱ�����������̵߳��ж�״̬��
 * ����̱߳��жϣ������ж�״̬�в��������ô������������� true���� isInterrupted()��ͬ��
 * �����Զ������ж�״̬Ϊ false���ڶ��ε��� Thread.interrupted()���������Ƿ��� false�������ж����̡߳�
 * @author chou
 *���ﲹ���� yield �� join ������ʹ��
 *		join �������̶߳�����ã������һ���߳� A �е�����һ���߳� B �� join �������߳� A ����ȴ��߳� B ִ����Ϻ���ִ�С�
 *		yield ����ֱ���� Thread ����ã�yield �ó� CPU ִ��Ȩ��ͬ�ȼ����̣߳�
 *			���û����ͬ������߳��ڵȴ� CPU ��ִ��Ȩ������̼߳���ִ�С�
 *
 */
public class InterruptReset extends Object {
	public static void main(String[] args) {
		System.out.println("Point X: Thread.interrupted()=" + Thread.interrupted());
		Thread.currentThread().interrupt();
		System.out.println("Point Y: Thread.interrupted()=" + Thread.interrupted());
		System.out.println("Point Z: Thread.interrupted()=" + Thread.interrupted());
	}
}