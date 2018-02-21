package project2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.DataProvider;

public class fetchdata {
	
	//@SuppressWarnings("null")
	 @DataProvider(name = "getData")
	 public static Object[][] getData() throws SQLException {
		// TODO Auto-generated method stub
		Statement stmt = null;
		Statement  stmt1=null;
		 int r;
		int c,k;
		int i =0;
		Object[][] data = null;
        
try {
	    	
	    	Connection con=DriverManager.getConnection("jdbc:oracle:thin:localhost:1521:orcl","hr","oracle");
	        stmt = con.createStatement();
	         stmt1 = con.createStatement();
	         String query1="Select COUNT (Account_no)from gmail ";
	          
	         ResultSet rows = stmt1.executeQuery(query1);
	         rows.next();
	         r = rows.getInt(1);
	         c=5;
	         String[][] dat = new String[r][c];
	        data = new Object[r][c];
	       System.out.println(r);
	         String query2 = "SELECT Account_no,user_id,password,RECEPIENT_EMAILID,SUBJECT from gmail ";
	       ResultSet rs = stmt.executeQuery(query2);
	       while (rs.next()) {
	        //System.out.println(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4));
	        
	        	
	        
	    	   for(int j=0;j<c; j++){
	    		   k= j+1;
	    		   dat[i][j] = rs.getString(k);
	    		  // System.out.println(dat[i][j]);
	    		   
	    	   } i = i+1;
	       }
	    	   for(int m = 0;m <r; m++) {
			    	  for(int n=0; n<c;n++) {
			    		  
			    	data[m][n]= dat[m][n];
			    	 System.out.println(dat[m][n]);
			    	  }
			      }    
	       
  	  
    con.close();   	   
	    	   	       
	}
catch (SQLException e ) {
    //JDBCTutorialUtilities.printSQLException(e);
}
return data;


}
}
