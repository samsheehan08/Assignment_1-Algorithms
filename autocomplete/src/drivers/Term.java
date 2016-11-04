package drivers;
import java.util.Comparator;


public class Term implements Comparable<Term>{

	private String term;
	private double weight;
	private String[] splitString;
	private String prefix;
	
	
	public Term (String inputLine){		
			splitString = inputLine.split("[	]");
			weight=Double.parseDouble(splitString[0]);		
			term=splitString[1];
	}
	public Term(){
		
	}
	
	public String getTerm(){
		return term;
	}
	public double getWeight(){
		return weight;		
	}
	
	public void setPrefix(String prefix){
		this.prefix=prefix;
	}
	
	public String toString(){
		return term + weight;
	}
	
	@Override
	public int compareTo(Term t) throws NullPointerException{
		int sortTerm=this.term.compareTo(t.term);
		return sortTerm;
	}
	public static Comparator<Term> weightComparator
	= new Comparator<Term>() {

		public int compare(Term terms, Term t) {
			double sort=t.getWeight()-terms.getWeight();
			int sortTerm=(int) sort;
			return sortTerm;
		}
	};
	
	public static Comparator<Term> searchComparator
	= new Comparator<Term>() {

		public int compare(Term terms, Term t) {
			int size=t.prefix.length();
			int sortTerm=terms.getTerm().substring(0, size).compareTo(t.prefix);
			return sortTerm;
		}
	};

	public static Comparator<String> prefixComparator
	= new Comparator<String>() {

		public int compare(String terms, String prefix) {
			int sortTerm=-1;
			if (terms.startsWith(prefix))
			sortTerm=terms.compareTo(prefix);
			return sortTerm;
		}
	};
}	

	
