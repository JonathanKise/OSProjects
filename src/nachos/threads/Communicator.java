package nachos.threads;

import java.util.concurrent.locks.ReentrantLock; 
import nachos.machine.*;


/**
 * A <i>communicator</i> allows threads to synchronously exchange 32-bit
 * messages. Multiple threads can be waiting to <i>speak</i>,
 * and multiple threads can be waiting to <i>listen</i>. But there should never
 * be a time when both a speaker and a listener are waiting, because the two
 * threads can be paired off at this point.
 */
public class Communicator {
   

   Lock mailCall = new Lock();
  
   Condition condition = new Condition(mailCall);
 static boolean Speakercalledfirst = false;
 static  boolean listencalledfirst = false;
   //mailCall.acquire;
   //mailCall.release;
    /**
     * Allocate a new communicator.
     
    public Communicator() {
    //
    condition.wake();
    }

    /**
     * Wait for a thread to listen through this communicator, and then transfer
     * <i>word</i> to the listener.
     *
     * <p>
     * Does not return until this thread is paired up with a listening thread.
     * Exactly one listener should receive <i>word</i>.
     *
     * @param	word	the integer to transfer.
     */
int text = -1;
    public void speak(int word) {
       
    if(!Speakercalledfirst){
              text = word;
       mailCall.acquire();
        
try {

    System.out.println("IN SPEAK TRY");
    Speakercalledfirst = true;
    if(!listencalledfirst){condition.sleep();}
    else{condition.wake();}
   

    //PUT THE WAIT IN HERE
     
} finally {
   listencalledfirst = false;
    mailCall.release();
    
}
    }
   }

    /**
     * Wait for a thread to speak through this communicator, and then return
     * the <i>word</i> that thread passed to <tt>speak()</tt>.
     *
     * @return	the integer transferred.
     */
    public int listen() {
   
      
      mailCall.acquire();
       
try {
    System.out.println("IN listen TRY");
    listencalledfirst = true;
    if(!Speakercalledfirst){condition.sleep();}
    else{condition.wake();}
    

    //SET THE WAIT IN HERE
    
} finally {
    Speakercalledfirst = false;
    mailCall.release();
} 
	   return text;
    }
}

