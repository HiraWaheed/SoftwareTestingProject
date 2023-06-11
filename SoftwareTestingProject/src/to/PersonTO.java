package to;
/**
 * @author Maha Khalid, Rameen Hamid, Hira Waheed
 */
public class PersonTO {
	
		private String Hadith;
		private String Book;
		private int Num_hadith;
		private String Matn;
		private String Sanad;
		private int Sanad_Length;

		public PersonTO(String hadith, String book, int num_hadith, String matn, String sanad, int sanad_Length) {
					this.Hadith=hadith;
					this.Book=book;
					this.Num_hadith=num_hadith;
					this.Matn=matn;
					this.Sanad=sanad;
					this.Sanad_Length=sanad_Length;
		}

		public PersonTO(int Num_Hadith)
		{
			this.Num_hadith=Num_Hadith;
		}

		public int getNum_Hadith()
		{
			return Num_hadith;
		}
		
		public int getSanad_Length()
		{
			return Sanad_Length;
		}
		
		public String get_Hadith()
		{
			return Hadith;
		}
		
		public String get_Book()
		{
			return Book;
		}
		
		public String get_Matn()
		{
			return Matn;
		}
		
		public String get_Sanad()
		{
			return Sanad;
		}

		public void setHadith(String Hadith) {
			this.Hadith=Hadith;
		}

		public void setBook(String Book) {
			// TODO Auto-generated method stub
			this.Book=Book;
			
		}

		public void setNum_hadith(int Num_hadith) {
			// TODO Auto-generated method stub
			this.Num_hadith=Num_hadith;
			
		}

		public void setMatn(String Matn) {
			// TODO Auto-generated method stub
			this.Matn=Matn;
			
		}

		public void setSanad(String Sanad) {
			// TODO Auto-generated method stub
			this.Sanad=Sanad;
			
		}

		public void setSanadLength(int Sanad_Length) {
			// TODO Auto-generated method stub
			this.Sanad_Length=Sanad_Length;
			
		}

		
	

}


