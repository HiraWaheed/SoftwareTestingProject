package bll;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dal.IDAL;
import to.PersonTO;

/**
 * @author Maha Khalid, Hira Waheed, Rameen Hamid
 */
public class PersonBO{
	private IDAL dao; //dependency inversion 
	
	public PersonBO(IDAL dao) {//dependency injection(object as parameter passed)
		this.dao = dao;
	}
	

	public String toNarrators(int num_hadith, String narrator) {
			/**
			 * @author f190271 Rameen Hamid
			 */
			System.out.println("toNARRATORS");
			// string narrator is having name of narrator in the num_hadith row
			PersonTO p = dao.Retrieve(num_hadith); // p contains the chain of narrators of a particular num hadith suppose 1
													// get hogaya hai
			String str = p.get_Sanad();
			String[] arr = str.split("' '");
			String commaSeparated=String.join(",",arr);
			System.out.println(commaSeparated);
			
			List<String> list = Arrays.asList(commaSeparated.split(","));
			if (list.contains(narrator)) {
				System.out.println(list.indexOf(narrator));
				int index = list.indexOf(narrator);
				System.out.println( arr[index + 1]);
				return arr[index + 1]; 
			}
			System.out.println("returning null");
			return null;
		}
	public String fromNarrators(int num_hadith, String narrator) {
		/**
		 * @author f190271 Rameen Hamid
		 */
		System.out.println("fromNARRATORS");
		//string narrator is having name of narrator in the num_hadith row 
		PersonTO p = dao.Retrieve(num_hadith); // p contains the chain of narrators of a particular num hadith suppose 1
		// get hogaya hai
		String str = p.get_Sanad();
		String[] arr = str.split("' '");
		String commaSeparated=String.join(",",arr);
		System.out.println(commaSeparated);
		
		List<String> list = Arrays.asList(commaSeparated.split(","));
		if (list.contains(narrator)) {
			System.out.println(list.indexOf(narrator));
			int index = list.indexOf(narrator);
			System.out.println( arr[index - 1]);
			return arr[index - 1]; 
		}
		System.out.println("returning null");
		return null;
		
	}
	
	public  ArrayList<String> listUniqueNarrators(String bookname) {
		/**
		 * @author f190166 Hira Waheed
		 */
		ArrayList<String> narratorslist = dao.getNarratorByBook(bookname);
		System.out.println(narratorslist);
		
		//keeping unique
		Set<List<String>> setOfLists = new HashSet<>();
		List<List<String>> uniqueLists = new ArrayList<>();
		for (String narrator : narratorslist) {
		    List<String> list = new ArrayList<>();
		    list.add(narrator);
		    if (!setOfLists.contains(list)) {
		        setOfLists.add(list);
		        uniqueLists.add(list);
		    }
		}
		System.out.println(uniqueLists);
		return narratorslist;
	}
	

}
