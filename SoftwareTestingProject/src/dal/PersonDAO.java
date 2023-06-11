package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import to.PersonTO;

/**
 * @author Maha Khalid, Hira Waheed, Rameen Hamid
 */
public class PersonDAO  implements IDAL {
		private Connection con;
		private int totalRows;
		public PersonDAO() throws SQLException {
			/**
			 * @author Rameen Hamid  19F-0271
			 */
// 		    String url = "jdbc:mysql://localhost:3306/st_production?useSSL=false";
//			String user = "rameen"; String password = "";
			
			String url = "jdbc:mysql://localhost:3307/st_production?useSSL=false"; 
			String user = "root"; String password = "";
			 
			con = DriverManager.getConnection(url, user, password);
			totalRows = 200;
		
		}
		public PersonDAO(Connection con) {
			this.con = con;
		}
		/**
		 * @author Rameen Hamid 19F-0271
		 *input of something and returns true if rows increased 
		 */
		public boolean Create(String Hadith, String Book, int Num_hadith, String Matn, String Sanad,int Sanad_Length) 
		{
			boolean response = false;	 
			if(Hadith==null || Hadith.length()==0 || Book==null || Book.length()==0 || Matn==null || Matn.length()==0 || Sanad==null || Sanad.length()==0|| Sanad_Length==0 || Num_hadith==0)  //these are called guard conditions
				response=false;	
			else
			{
					for(int i=0;i<Hadith.length();i++) //check Hadith is not english input
					{
						if(Hadith.charAt(i)>='A' && Hadith.charAt(i)<='z')
						{
							response=false;
						}
						else
						{
							try
							{
								PreparedStatement ps = con.prepareStatement("insert into `production_db` values(?, ?,?,?,?,?)");
								ps.setString(1,Hadith );
								ps.setString(2, Book);
								ps.setInt(3, Num_hadith);
								ps.setString(4, Matn);
								ps.setString(5, Sanad);
								ps.setInt(6, Sanad_Length);
								int row = ps.executeUpdate();
								System.out.println(row); 
						        if(row == 1) //compared the row counts if previous row count less than current means data was added
						        	response=true;
						        else
						        	response=false;   
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} 
					}
			}
			return response;
		}
			
	

		/**
		 * @author Rameen Hamid 19F-0271
		 *input of Num_Hadith was given and its Sanad Length was returned 
		 */
		public PersonTO Retrieve(int Num_Hadith) {	
			PersonTO person = new PersonTO(Num_Hadith);
			try {
				PreparedStatement ps = con.prepareStatement("SELECT * FROM `production_db` WHERE `Num_Hadith` = ?");
				ps.setInt(1,Num_Hadith );
				ResultSet rs = ps.executeQuery();

				while(rs.next()) {
					person.setHadith(rs.getString("Hadith"));
					person.setBook(rs.getString("Book"));
					person.setNum_hadith(rs.getInt("Num_hadith"));
					person.setMatn(rs.getString("Matn"));
					person.setSanad(rs.getString("Sanad"));
					person.setSanadLength(rs.getInt("Sanad_Length"));
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();		
			}
			//return person.getSanad_Length();// need to change here to  return person. 
			return person;
		}
		 /**
		 * @author Maha Khalid 19F-0142
		 * input of Num_Hadith was given and its Sanad Length was returned 
		 */
		public int Update(int Num_Hadith,int length) {	
			PersonTO person = new PersonTO(Num_Hadith);
			
			try {
				PreparedStatement ps = con.prepareStatement("UPDATE `production_db` SET `Sanad_Length`= ?  WHERE `Num_hadith` = ?");
				ps.setInt(1,length );
				ps.setInt(2,Num_Hadith );
				int row = ps.executeUpdate();

				if (row > 0) {
					PreparedStatement ps1 = con.prepareStatement("SELECT * FROM `production_db` WHERE `Num_hadith` = ?");
					ps1.setInt(1, Num_Hadith);
				    ResultSet rs = ps1.executeQuery();
					while(rs.next()) {
						person.setHadith(rs.getString("Hadith"));
						person.setBook(rs.getString("Book"));
						person.setNum_hadith(rs.getInt("Num_hadith"));
						person.setMatn(rs.getString("Matn"));
						person.setSanad(rs.getString("Sanad"));
						person.setSanadLength(rs.getInt("Sanad_Length"));
					}
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(person.getSanad_Length());
			return person.getSanad_Length();
		}
		/**
		 * @author Hira Waheed 19F-0166
		 * takes num_hadith as input and deletes row 
		 */
		public boolean Delete(int Num_Hadith) {
			boolean response = false;
			if(Num_Hadith < 0 || Num_Hadith > totalRows)
				response = false;
			try {
				PreparedStatement ps = con.prepareStatement("DELETE FROM `production_db` WHERE `Num_Hadith` = ?");//change table name as needed
				ps.setInt(1,Num_Hadith );
				 int row = ps.executeUpdate();
				 System.out.println(row);
		         if(row == 0)
		        	 response = false;
		         else
		        	 response = true;
		         
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return response;
		}
		
		public ArrayList<String> getNarratorByBook(String book){
			/**
			 * @author Hira Waheed 19F-0166
			 */
			ArrayList<String> resultList = new ArrayList<String>();
			
			 try {
				PreparedStatement ps = con.prepareStatement("SELECT Sanad FROM `production_db` WHERE Book = ?");
				ps.setString(1, book );
				ResultSet rs = ps.executeQuery();

			    while (rs.next()) {
			    	String rss = rs.getString("Sanad");
			    	System.out.println("rss"+rss);
			    	//String[] sanadArray = rss.split("\\s+");
			    	//System.out.println("sanad array"+sanadArray);					
			    	resultList.add(rss);
			    }
			    
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println(resultList);
			return resultList;
		}
}
	

