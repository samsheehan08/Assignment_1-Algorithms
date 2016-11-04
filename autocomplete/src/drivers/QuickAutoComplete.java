package drivers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuickAutoComplete implements AutoComplete {
	private List<Term> terms;
	
	public QuickAutoComplete() throws IOException{
		terms=new ArrayList<Term>();
		List<String> s =new ArrayList<String>();
		BufferedReader in = null;
		URL urlList = new URL("https://wit-computing.github.io/algorithms-2016/topic04/book-2/data/wiktionary.txt");
		in = new BufferedReader(new InputStreamReader(urlList.openStream()));
		try {
			String inputLine;			
			while ((inputLine = in.readLine()) != null){
				s.add(inputLine);								
			}
		} finally {
			if (in != null)
				in.close();
		}
		for(int i=1;i<s.size();i++)		{
			terms.add(new Term(s.get(i)));
		}		
			Collections.sort(terms);
	}
	
	
	public List<Term>  getTerms(){
		return terms;
	}

	@Override
	public double weightOf(String term) {
		Term tr =new Term(term);
		return tr.getWeight();
	}

	@Override
	public String bestMatch(String prefix) {
		Iterable<String> tempArray=matches(prefix, 1);			
		return tempArray.iterator().next();
	}

	@Override
	public Iterable<String> matches(String prefix, int k) {
		List<Term> matchingWordsArray=new ArrayList<Term>();
		Term t=new Term();
		t.setPrefix(prefix);
		int index=Collections.binarySearch(terms, t, Term.searchComparator);
		for(int i=0;i<k;i++){
			matchingWordsArray.add(terms.get(index));
			index++;
		}
		Collections.sort(matchingWordsArray, Term.weightComparator);
		List<String> temp=new ArrayList<String>();
		for(Term tr:matchingWordsArray)
		temp.add(tr.getTerm());
		return temp;
		/*List<String> matchingWordsArray1=new ArrayList<String>();
		for(Term tr:matchingWordsArray) matchingWordsArray1.add(tr.getTerm());
		int index2=Collections.binarySearch(matchingWordsArray1, prefix, Term.prefixComparator);
		System.out.println(matchingWordsArray1);*/
	}

}
