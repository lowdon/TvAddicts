/**
 * 
 */
package test;

import static org.junit.Assert.*;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.Test;

/**
 * @author Lowdon
 *
 */
public class junitRun {

	public static void main(String[] args) {
	      Result result = JUnitCore.runClasses(EpisodeTest.class);
			
	      for (Failure failure : result.getFailures()) {
	         System.out.println(failure.toString());
	      }
			
	      System.out.println(result.wasSuccessful());
	   }

} 	