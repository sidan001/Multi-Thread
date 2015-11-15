package Thread.Interrupt;

/**
 * 使用 interrupt()中断线程当一个线程运行时，另一个线程可以调用对应的 Thread 对象的 interrupt()方法来中断它，
 * 该方法只是在目标线程中设置一个标志，表示它已经被中断，并立即返回。这里需要注意的是，
 * 如果只是单纯的调用 interrupt()方法，线程并没有实际被中断，会继续往下执行。
 * 
 * 主线程启动新线程后，自身休眠 2 秒钟，允许新线程获得运行时间。新线程打印信息about to sleep for 20 seconds后，
 * 继而休眠 20 秒钟，大约 2 秒钟后，main 线程通知新线程中断，那么新线程的 20 秒的休眠将被打断，
 * 从而抛出 InterruptException 异常，执行跳转到 catch 块，打印出interrupted while sleeping信息，
 * 并立即从 run（）方法返回，然后消亡，而不会打印出 catch 块后面的leaving normally信息。
 * 请注意：由于不确定的线程规划，上图运行结果的后两行可能顺序相反，这取决于主线程和新线程哪个先消亡。
 * 但前两行信息的顺序必定如上图所示。
 * 另外，如果将 catch 块中的 return 语句注释掉，
 * 则线程在抛出异常后，会继续往下执行，而不会被中断，从而会打印出leaving normally信息。
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
            //处理完中断异常后，返回到run（）方法人口，  
            //如果没有return，线程不会实际被中断，它会继续打印下面的信息  
            return;    
        }  
        System.out.println("in run() - leaving normally");  
    }  

    public static void main(String[] args) {  
        SleepInterrupt si = new SleepInterrupt();  
        Thread t = new Thread(si);  
        t.start();  
        //主线程休眠2秒，从而确保刚才启动的线程有机会执行一段时间  
        try {  
            Thread.sleep(2000);   
        }catch(InterruptedException e){  
            e.printStackTrace();  
        }  
        System.out.println("in main() - interrupting other thread");  
        //中断线程t  
        t.interrupt();  
        System.out.println("in main() - leaving");  
    }  
} 