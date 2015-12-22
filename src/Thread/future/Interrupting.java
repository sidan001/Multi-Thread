/**
* @Description: TODO
 */
package Thread.future;



import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
/**
 * @author   E-mail:liuchou.ewedata.com
 * @date 创建时间：2015年12月22日 下午3:56:43 
 * @Description 
 * @version 1.0 
 * @since  
 *  
 */
public class Interrupting {
	static ExecutorService exec=Executors.newCachedThreadPool();
	public static void main(String[] args) {
		
		test(new SleepBlocked());
		test(new IOBlocked(System.in));
		test(new SynchronizedBlocked());
	}
	public static void test(Runnable r){
		Future<?> f=exec.submit(r);
		System.out.println("prepare to cancel:"+r.getClass().getName());
		f.cancel(true);
		System.out.println("Interrupt sent to :"+r.getClass().getName());
	}

}
class SleepBlocked implements Runnable{
	@Override
	public void run(){
		try{
			TimeUnit.MINUTES.sleep(4);	//可被中断
		}catch(InterruptedException e){
			System.out.println("sleep interruption!!");
		}
		System.out.println("exiting SleepBlocked-------------------------");
	}
}
class IOBlocked implements Runnable{
	private InputStream in;
	public IOBlocked(InputStream in){
		this.in=in;
	}
	@Override
	public void run(){
		try{
			System.out.println("prepare to read:::::::::");
			in.read();		//IO阻塞，不可被线程中断
		}catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			if(Thread.interrupted()){
				System.out.println("ioblocked is interrupted!!!");
			}else{
				System.out.println("ioblocked is not interrupted, just io exception");
				throw new RuntimeException();
			}
		}
		System.out.println("exiting IOBlocked---------------------");
	}
}
class SynchronizedBlocked implements Runnable{
	public SynchronizedBlocked(){
		new Thread(){
			@Override
			public void run(){
				f();
			}
		}.start();
	}
	public synchronized void f(){
		while(true)
			Thread.yield();
	}
	@Override
	public void run(){
		System.out.println("trying to call f()");
		f();	//synchronized锁无法被线程中断
		System.out.println("exiting SynchronizedBlocked------------------------");
	}
}