package activiti_maven_project;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MethodOver {

	public static void main(String[] args) {
		
			MethodOver m=new MethodOver();
			m.dowork();
	}
	
	
	public void dowork(){
//		ExecutorService executor = Executors.newCachedThreadPool();
		ExecutorService executor =Executors.newFixedThreadPool(2);
		Task task = new Task("aaaaa");
		
		FutureTask future = new FutureTask(task);
		executor.execute(future);
		try {
			Object result = future.get(5, TimeUnit.SECONDS);
			System.out.println(result);
		} catch (InterruptedException e) {
			System.out.println("InterruptedException!!");
		} catch (ExecutionException e) {
			System.out.println("ExecutionException!!");
		} catch (TimeoutException e) {
			System.out.println("TimeoutException!!");
		}finally {
			future.cancel(true);
//			executor.shutdown();
		}
		System.out.println("isdone:"+future.isDone());
	}
	 class Task implements Callable{
			String str="";
			public Task(String strs){
				str=strs;
			}
		    @Override
		    public String call() throws Exception {
		    	try{
			    	for(int i=0;i<1000000;i++){
			    		Thread.sleep(1000);
			    		Thread current = Thread.currentThread();
				        System.out.println("Thread.name==============================="+current.getName());
			    	}
			       
		    	}catch(InterruptedException e) {
		    		System.out.println("InterruptedException!!");
		    	}
		    	 return str;
		    }
		}

}


