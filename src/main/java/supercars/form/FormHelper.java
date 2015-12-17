/*
 * Created on 27-Jun-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package supercars.form;

import java.util.Random;

/**
 * @author v023094
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FormHelper {
	
	public static boolean isCarValid(CarForm carForm) {
	
	// Perform inefficient String validation
	try {
		Random r = new Random();
		
		Thread.sleep(r.nextInt(10)+000);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return true;
	}

}
