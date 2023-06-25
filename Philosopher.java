package misc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Philosopher implements Runnable{
	
	private boolean stop_requested = false;
	private int id;
	private Chopstick c;
	private boolean ate = false;
	
	Philosopher(int id,Chopstick c){
		this.id = id;
		this.c = c;
	};
	
	
	public void request_stop() {
		stop_requested = true;
	}
	
	public boolean is_stop_requested() {
		return stop_requested;
	}
	
	public boolean get_ate() {
		return ate;
	}
	
	public void sleep(long millis){
		
		try {
			Thread.sleep(millis);
		}
		catch(InterruptedException ie){
			ie.printStackTrace();
		}
		
	}
	
	public boolean try_locks(){
		
		ReentrantLock c1 = c.get_c1();
		ReentrantLock c2 = c.get_c2();
		int n1 = c.get_num1();
		int n2 = c.get_num2();
		try {
			boolean try1 = c1.tryLock(1000,TimeUnit.MILLISECONDS);
			if(!try1){
				System.out.println(id + " Interrupted 0-0");
				return false;
			}
			System.out.println(id + " Holds Chopstick: " + n1);

			
		} catch (InterruptedException e) {
			//e.printStackTrace();
			System.out.println(id + " Interrupted 0-1");
			return false;
		}
		
		try {
			boolean try2 = c2.tryLock(1000,TimeUnit.MILLISECONDS);
			if(!try2) {
				System.out.println(id + " Interrupted 1-0");
				c1.unlock();
				return false;
			}
			System.out.println(id + " Holds Chopstick: " + n2);
			
		} catch (InterruptedException e) {
			//e.printStackTrace();
			System.out.println(id + " Interrupted 1-1");
			return false;
		}
		
		ate = true;
		System.out.println("Philosopher "+ id +" eats");
		return true;
	}
	
	@Override
	public void run() {
		
		ReentrantLock c1 = c.get_c1();
		ReentrantLock c2 = c.get_c2();
		
		while(!stop_requested){
			
			/*
			 * I will use time backoff deadlock prevention.
			 */
			if(!ate) {
				while(!try_locks()) {
					sleep(100L * ((long) Math.random()));
				}
				c2.unlock();
				c1.unlock();
				this.request_stop();
			}
			this.request_stop();
			
		}
		
	}
	

}
