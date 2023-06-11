package dal;

import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import to.PersonTO;

class PersonDAOtests {
	
	static PersonDAO dao; //using production_db
	
	@BeforeAll
	static void coldStart() throws SQLException
	{
		dao = new PersonDAO();
	}
	@BeforeEach
	void init()
	{
		
	}
	/**
	 * @author Rameen Hamid F190271
	 * this means rows are compared before and after inserting
	 */
	@Test 
	@DisplayName("When Inserted new data")
	void testsCreateOne() {
		Assertions.assertTrue(dao.Create("ننن","ستا",211,"سيي","تستس",5));	
	}
	
	/**
	 * @author Rameen Hamid F190271
	 * this means empty input given instead of arabic
	 */
	@Test 
	@DisplayName("When Inserted empty strings and integer 0 as parameters")
	void testsCreateTwo() {
		Assertions.assertFalse(dao.Create("","",0,"","",0));	
	}
	
	/**
	 * @author Rameen Hamid F190271
	 * this means english input given instead of arabic
	 */
	@Test 
	@DisplayName("When Inserted english string as Hadith parameter")
	void testsCreateThree() {
		Assertions.assertFalse(dao.Create("Hamid","ثيؤؤؤؤؤينسن",212,"سيسيسي","يسيسي",5));	
	}
	
	/**
	 * @author Rameen Hamid F190271
	 * this means Num_Hadith is not existing and so is sanad_length
	 */
	@Test 
	@DisplayName("When Num_Hadith is 0 and Sanad Length is 0 too")
	void testsSanadLengthOne() {
		PersonTO p1 = dao.Retrieve(0);
		Assertions.assertEquals(p1.getSanad_Length(),0);	
	}
	/**
	 * @author Rameen Hamid F190271
	 * this means Num_Hadith is retrieved correct and length is also correct
	 */
	@Test 
	@DisplayName("When Num_Hadith is 1 and Sanad Length is correct")
	void testsSanadLengthTwo() {
		PersonTO p1 = dao.Retrieve(1);
		Assertions.assertEquals(p1.getSanad_Length(),4);	
	}
	/**
	 * @author Rameen Hamid F190271
	 * this means Num_Hadith is retrieved correctly but corresponding length is incorrect
	 */
	@Test 
	@DisplayName("When Num_Hadith is 2 and Sanad Length is incorrect")
	void testsSanadLengthThree() {
		PersonTO p1 = dao.Retrieve(2);
		Assertions.assertNotEquals(p1.getSanad_Length(),2);
	}
	/**
	* @author Rameen Hamid F190271
	* this means Num_Hadith is retrieved correctly but corresponding length is incorrect
	*/
	@Test 
	@DisplayName("When Num_Hadith is incorrect and Sanad Length is correct")
	void testsSanadLengthFour() {
		PersonTO p1 = dao.Retrieve(0);
		Assertions.assertNotEquals(p1.getSanad_Length(),7);
	}
	/**
	* @author Hira Waheed 19F-0166
	* Num_hadith is taken as input ,and deletes that existing row
	*/
	@Test
	@DisplayName("When Num_hadith is correct and exists in db")
	void testsDeleteRow1() {
		Assertions.assertTrue(dao.Delete(211));
	}
	/**
	* @author Hira Waheed 19F-0166
	* Num_hadith as input given and row is not deleted as it is already deleted
	*/
	@Test
	@DisplayName("When Num_hadith does not exists in db(has already been deleted)")
	void testsDeleteRow2() {
		Assertions.assertFalse(dao.Delete(207));
	}
	/**
	* @author Hira Waheed 19F-0166
	* Invalid input of num_hadith given so row doesn't exist
	*/
	@Test
	@DisplayName("When Num_hadith is incorrect ")
	void testsDeleteRow3() {
		Assertions.assertFalse(dao.Delete(-1));
	}
	/**
	 * @author MahaKhalid 19F-0142
	 * this means Num_Hadith is retrieved correctly but corresponding length is incorrect
	 */
	@Test 
	@DisplayName("When Num_Hadith is correct and Sanad Length is correct")
	void testUpdateSanadLength1() {	
		Assertions.assertTrue(dao.Update(1,4)==4);		
	}

	/**
	 * @author MahaKhalid 19F-0142
	 * this means Num_Hadith is retrieved correctly but corresponding length is incorrect
	 */
	@Test 
	@DisplayName("When Num_Hadith is incorrect so Sanad Length is incorrect")
	void testUpdateSanadLength2() {
		Assertions.assertFalse(dao.Update(0,0)==4);		
	}
}
