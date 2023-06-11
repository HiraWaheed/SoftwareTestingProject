package dal;

import java.util.ArrayList;

import to.PersonTO;

/**
 * @author Maha Khalid
 */
public interface IDAL {
	public boolean Create(String Hadith, String Book, int Num_hadith, String Matn, String Sanad,int Sanad_Length) ;
	public PersonTO Retrieve(int Num_Hadith);
	public int Update(int Num_Hadith,int length);
	public boolean Delete(int Num_Hadith);
	public ArrayList<String> getNarratorByBook(String book);

}
