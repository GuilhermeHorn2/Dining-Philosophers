package misc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Simulate {
	
	//number of philosophers in the simulation:
	private int n;
	
	private int count = 0;//count the number of philosophers ho have alredy eaten
	
	private List<Chopstick> chops = new ArrayList<>();
	
	private List<Philosopher> phils = new ArrayList<>();
	
	Simulate(int n){
		this.n = n;
		
		List<ReentrantLock> locks = new ArrayList<>();
		for(int i = 0;i < n;i++){
			locks.add(new ReentrantLock(true));
		}
		
		ReentrantLock past = null;
		for(int i = 0;i <= n;i++) {
			if(past == null) {
				past = locks.get(i);
				continue;
			}
			if(i == n){
				chops.add(new Chopstick(locks.get(0),past,0,i-1));
				continue;
			}
			chops.add(new Chopstick(past,locks.get(i),i-1,i));
			past = locks.get(i);
			
		}
		
		//System.out.println(chops);
		
		for(int i = 0;i < n;i++) {
			phils.add(new Philosopher(i,chops.get(i)));
		}
		
	}
	
	public void start_simulation(){
		
		for(int i = 0;i < n;i++) {
			Thread tmp = new Thread(phils.get(i));
			tmp.start();
		}
		
	}
	
	

}
