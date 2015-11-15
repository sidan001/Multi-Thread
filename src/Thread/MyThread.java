package Thread;

class MyThread implements Runnable{  
    private  int ticket = 5;  
    public  void run(){  
        for (int i=0;i<10;i++)  
        {  
        	synchronized (this){
        		if(ticket > 0){  
        			System.out.println("ticket = " + ticket--);  
        		}  
        	}
        }  
    }  
}  

