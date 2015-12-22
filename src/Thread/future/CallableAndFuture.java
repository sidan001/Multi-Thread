/**
* @Description: TODO
 */
package Thread.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**
 * @author   E-mail:liuchou.ewedata.com
 * @date 创建时间：2015年12月22日 下午3:00:01 
 * @Description 
 * @version 1.0 
 * @since  
 *  
 */
public class CallableAndFuture {
	public static void main(String[] args) {
		System.out.println(execute());
	}

	/**
	 *  
	 * 
	*/
	private static String execute() {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		Future<String> future = executorService.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				System.out.println("线程处理任务中。。。。。。");
				return "结果";
			}
		});
		// do other things while searching   //displayOtherThings(); 
//		 displayOtherThings(); 
	     try {
	       System.out.println("等待2秒，确定任务完成");
	       Thread.sleep(2000);
	       String result = future.get();
	       System.out.println("获取任务结果:   "+result); // use future
	       return result;
	     } catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}finally{
			System.out.println(future.cancel(true));
			executorService.shutdown();
		}
		return null;
	}
}
