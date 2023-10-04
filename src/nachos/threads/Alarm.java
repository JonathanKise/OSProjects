package nachos.threads;

import nachos.machine.*;

/**
 * Uses the hardware timer to provide preemption, and to allow threads to sleep
 * until a certain time.
 */
public class Alarm {
    /**
     * Allocate a new Alarm. Set the machine's timer interrupt handler to this
     * alarm's callback.
     *
     * <p><b>Note</b>: Nachos will not function correctly with more than one
     * alarm.
     */
    public Alarm() {
	Machine.timer().setInterruptHandler(new Runnable() {
		public void run() { timerInterrupt(); }
	    });
    }

    /**
     * The timer interrupt handler. This is called by the machine's timer
     * periodically (approximately every 500 clock ticks). Causes the current
     * thread to yield, forcing a context switch if there is another thread
     * that should be run.
     */
    boolean firstcall = true;
    public void timerInterrupt() {
        

        
if (time < Machine.timer().getTime() && eddy != null && firstcall){
    firstcall = false;
    boolean intStatus=Machine.interrupt().disable();
    eddy.ready();
    Machine.interrupt().restore(intStatus);
}
	//KThread.currentThread().yield();
    }

    /**
     * Put the current thread to sleep for at least <i>x</i> ticks,
     * waking it up in the timer interrupt handler. The thread must be
     * woken up (placed in the scheduler ready set) during the first timer
     * interrupt where
     *
     * <p><blockquote>
     * (current time) >= (WaitUntil called time)+(x)
     * </blockquote>
     *
     * @param	x	the minimum number of clock ticks to wait.
     *
     * @see	nachos.machine.Timer#getTime()
     */
   static KThread eddy = null;
    long time = 0;
  
    public void waitUntil(long x) {
     time = Machine.timer().getTime() + x;
        eddy = KThread.currentThread();
       
	// for now, cheat just to get something working (busy waiting is bad)
	long wakeTime = Machine.timer().getTime() + x;
    boolean intStatus=Machine.interrupt().disable();
    KThread.sleep();
    Machine.interrupt().restore(intStatus);
      
       // KThread.currentThread()
	/*while (wakeTime > Machine.timer().getTime())
    {
    }*/
	   // KThread.yield();
    }
}
