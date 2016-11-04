package drivers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BruteAutoComplete implements AutoComplete{
	private List<String> wordArray;
	
	public BruteAutoComplete()throws IOException{
		BufferedReader in = null;
		URL urlList = new URL("https://wit-computing.github.io/algorithms-2016/topic04/book-2/data/wiktionary.txt");
		wordArray = new ArrayList<String>();
		in = new BufferedReader(new InputStreamReader(urlList.openStream()));
		try {
			String inputLine;
			while ((inputLine = in.readLine()) != null)
				wordArray.add(inputLine);
		} finally {
			if (in != null)
				in.close();
		}
	}
	
	public List<String>  getWordArray(){
		return wordArray;
	}
	
	@Override
	public double weightOf(String term) {
		Term tr =new Term(term);
		return tr.getWeight();
	}
	
	@Override
	public String bestMatch(String prefix) {
		String match;
		Iterable<String> tempArray=matches(prefix, 1);
		match=tempArray.iterator().next();			
		return match;
	}

	@Override
	public Iterable<String> matches(String prefix, int k) {
		List<String> matchingWordsArray=new ArrayList<String>();
		int endIndex=prefix.length()+FIRST_TEXT_INDEX;
		int count=0;
		for (int i=0; i<getWordArray().size(); i++){
			if  (getWordArray().get(i).contains(prefix)){
				String substringMatch= getWordArray().get(i).substring(FIRST_TEXT_INDEX, endIndex);
				if ((substringMatch.equals(prefix))&&(count<k)){
					String match=getWordArray().get(i);
					matchingWordsArray.add(match);
					count++;
				}
				
			}
		}
		
		
		return matchingWordsArray;
	}

}
/*	public List<String> matchingWordList(String prefix){
List<String> matchingWordsArray=new ArrayList<String>();
int endIndex=prefix.length()+FIRST_TEXT_INDEX;
for (int i=0; i<getWordArray().size(); i++){
	if  (getWordArray().get(i).contains(prefix)){
		//System.out.println(getWordArray().get(i));
		String substringMatch= getWordArray().get(i).substring(FIRST_TEXT_INDEX, endIndex);	
		if (substringMatch.equals(prefix)){
			String match=getWordArray().get(i);
			matchingWordsArray.add(match);
		}
	}
}
return matchingWordsArray;
}*/


/*@Override
public String bestMatch(String prefix) {
String match=null;
List<String> tempArray=matchingWordList(prefix);
if (tempArray!=null){
	match=tempArray.get(0);
	for(String s : tempArray){
		if(weightOf(s) > weightOf(match)){
			match=s;
		}
	}
}	
return match;
}*/
