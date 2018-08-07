package logiciel;

import java.util.ArrayList;
import java.util.Random;

public class Processus  {

	private int pid_process ;
	private int priority ;
	private int time_cpu ;
	private int time_io ;
	private String name_process ;
	private ArrayList<Pile_Execution> pile_execution = new ArrayList<Pile_Execution>();
	///////////////////////////////////////////////////////////////////////////////////
	public String getName_process() {
		return name_process;
	}
	public void setName_process(String name_process) {
		this.name_process = name_process;
	}
	public int getTime_cpu() {
		return time_cpu;
	}
	public void setTime_cpu(int time_cpu) {
		this.time_cpu = time_cpu;
	}
	public int getTime_io() {
		return time_io;
	}
	public void setTime_io(int time_io) {
		this.time_io = time_io;
	}

	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public ArrayList<Pile_Execution> getPile_execution() {
		return pile_execution;
	}
	public void setPile_execution(ArrayList<Pile_Execution> pile_execution) {
		this.pile_execution = pile_execution;
	}
	public int getPid_process() {
		return pid_process;
	}
	public void setPid_process(int pid_process) {
		this.pid_process = pid_process ;
	}
	//////////////////////////////////////////////////////////////////////////
	public Processus() {
	}
	public Processus(int pid_process , String name_process ) {
		this.pid_process = pid_process ;
		this.name_process = name_process;

	}
	public int init_process () {
		Thread th = new Thread();
		int nb_processus = 0;
		while (nb_processus!=5) {
			try {
				th.sleep(4000);
				Random rand = new Random();
				this.pid_process = rand.nextInt(10);
				this.priority = rand.nextInt(5);
				this.time_cpu = rand.nextInt(2);
				this.time_io = rand.nextInt(2);
				System.out.println("Initialized PID#"+pid_process);
				System.out.println("Initialized PRIORITY#"+priority);
				System.out.println("Initialized TIME_CPU#"+time_cpu);
				System.out.println("Initialized TIME_IO#"+time_io);
				nb_processus ++ ;
			}catch(Exception e) {

			}
		}

		return pid_process;
	}
}

