package bll;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dal.PersonDAOStub;

class PersonBOtests {
	static PersonDAOStub dao; //using test_db
	static PersonBO bo;
	@BeforeAll
	static void coldStart(){  
		try {
			dao = new PersonDAOStub();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bo = new PersonBO(dao);
	}
	@BeforeEach
	void init() { 
		
	}
	@Test 
	@DisplayName("When num_hadith is 1 and searching الزهري to narrator 'ابي سلمة")
	void testToNarrator() {
		/**
		 * @author f190271 Rameen Hamid
		 */
		Assertions.assertEquals(bo.toNarrators(1,"الزهري"),"ابي سلمة");
	}
	@Test 
	@DisplayName("When num_hadith is 1 and searching منصور narrator which doesn't exist")
	void testToNarratorOne() {
		/**
		 * @author f190271 Rameen Hamid
		 */
		Assertions.assertEquals(bo.toNarrators(1,"منصور"),null);
	}
	
	@Test 
	@DisplayName("When num_hadith is 1 and searching الزهري from narrator سفيان")
	void testFromNarrator() {
		/**
		 * @author f190271 Rameen Hamid
		 */
		Assertions.assertEquals(bo.fromNarrators(1, "الزهري"),"سفيان");
	}
	@Test 
	@DisplayName("When num_hadith is 1 and searching منصور from narrator which doesn't exist")
	void testFromNarratorOne() {
		/**
		 * @author f190271 Rameen Hamid
		 */
		Assertions.assertEquals(bo.fromNarrators(1,"منصور"),null);
	}
	@Test
	@DisplayName("Unique Narrators testcase")
	void testUniqueNarrators() {
		/**
		 * @author f190166 Hira Waheed
		 */
		Assertions.assertNotEquals(bo.listUniqueNarrators("سنن النسائى الصغرى"),null);
	}
	@Test
	@DisplayName("When narrators list is empty because of wrong bookname")
	void testUniqueNarratorsOne() {
		/**
		 * @author f190166 Hira Waheed
		 */
		ArrayList<String> arr = new ArrayList<String>();
		Assertions.assertEquals(bo.listUniqueNarrators("سنن"),arr);
	}
}
