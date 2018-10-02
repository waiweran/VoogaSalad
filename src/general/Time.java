package general;

/**
 * Timer Class
 * NOTE: Currently prints out time countdown depending on user (self) input
 * TODO: GameLoop needs to connect with run function (in gamestate?), and timer should be displayed on the frontend
 * @author Ashka Stephen
 */
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Time {
	static int interval;
	static Timer timer;

	public static void main(String[] args) {
		//for testing based on self input
		//change so input arrives from frontend (?)
		Scanner sc = new Scanner(System.in);
		System.out.print("Input seconds => : ");
		String secs = sc.nextLine();
		
		int millisecondDelay = 1000;
		int period = 1000;
		timer = new Timer();
		interval = Integer.parseInt(secs);
		System.out.println(secs);
		timer.scheduleAtFixedRate(new TimerTask() {

			public void run() {
				//currently printing time to Console
				//TODO frontend display updates here
				System.out.println(setInterval());
			}
		}, millisecondDelay, period);
	}

	//Constructor tbd
	public Time(){
	}

	private static final int setInterval() {
		if ((interval == 1) ||(interval == 0))
			timer.cancel();
		return --interval;
	}
	
	/**
	 * @return current time
	 */
	public int getTime(){
		return interval;
	}

	/**
	 * Initialize Timer Value
	 */
	public void setTime(int timeReset){
		interval = timeReset;
	}

	/**
	 * Pause Timer
	 */
	public void pauseTime(){
		//TODO: stop the update
	}

	/**
	 * Restart Timer
	 */
	public void unPause(){
		//TODO: continue update
	}
}
