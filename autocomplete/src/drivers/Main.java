package drivers;

import java.util.Scanner;
public class Main {	

	private Scanner input;
	public Main(){
		input=new Scanner(System.in);
	}
	
	
	public static void main(String[] args){
		Main m =new Main();
		try{
			//BruteAutoComplete br= new BruteAutoComplete();
			QuickAutoComplete qu=new QuickAutoComplete();
			m.startBruteForce(qu);
		}
		catch(Exception e){
			System.out.println("No internet connection!!");
		}
	
	}
	
	private void startBruteForce(/*BruteAutoComplete br*/ QuickAutoComplete qu){
		System.out.println(qu.getTerms());
		//System.out.println(br.getWordArray());
		System.out.println("Enter search term:");
		String searchString=input.nextLine();
  		//System.out.println(br.bestMatch(searchString));
  		System.out.println(qu.matches(searchString, 5));
  		//System.out.println(br.weightOf(br.bestMatch(searchString)));*/
  		//Term tr =new Term(br.bestMatch(searchString));
  		//System.out.println(tr);
		
	}


}
