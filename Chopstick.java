package misc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Chopstick {
	//the simution will be for n philophers so its better to set fairness
	
	private ReentrantLock c1;//left
	private ReentrantLock c2;//right
	private int num_c1;
	private int num_c2;
	
	Chopstick(ReentrantLock c1,ReentrantLock c2,int num_c1,int num_c2){
		this.c1 = c1;
		this.c2 = c2;
		this.num_c1 = num_c1;
		this.num_c2 = num_c2;
	}
	
	public ReentrantLock get_c1() {
		return c1;
	}
	
	public ReentrantLock get_c2() {
		return c2;
	}
	
	public int get_num1(){
		return num_c1;
	}
	
	public int get_num2(){
		return num_c2;
	}
	
	public String toString(){
		return "("+num_c1+","+num_c2+")";
	}
	
	
}
