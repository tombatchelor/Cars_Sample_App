/*
 * Created on 01-Jun-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Random;

import supercars.dataloader.CarDataLoader;

/**
 * @author v023094
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Driver {

	public static void main(String[] args) {
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection connection = DriverManager.getConnection ("jdbc:oracle:thin:@ROCKET:1521:PW", "supercars", "supercars");
			PreparedStatement pstmt = connection.prepareStatement("INSERT INTO ENQUIRIES (NAME, EMAIL, \"COMMENT\", CAR_ID) VALUES(?,?,?,?)");
			pstmt.setString(1, "test");
			pstmt.setString(2, "test");
			pstmt.setString(3, "test");
			pstmt.setInt(4, 99);
			for (int i=0;i < 500000;i++)
				pstmt.execute();
			
			pstmt.close();
			connection.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}	
		
		//CarDataLoader cdl = new CarDataLoader();
		
		//System.out.println(cdl.getCar(2).getName());
		//System.out.println(cdl.getCarsSummary());
		/*System.out.println("Sleeping..");
		try {
			Random r = new Random();
			
			int i = r.nextInt(5000);
			if (i < 1000)
				i+=1000;
			Thread.sleep(i);
			
		System.out.println("woke up "+i);
		}
		catch(Exception e){
			e.printStackTrace();
		}*/
	}
}
