package BlackJacques;

import java.util.*;

public class Croupier {
    
    private ArrayList<Integer> mains;
    
    private ArrayList<String> mainstr;
    Deck paquet = new Deck();
    
    public Croupier() {
    	
    	mains = new ArrayList<Integer>(15);
    	mainstr = new ArrayList<String>(15);
    }
    
    public int total() {
		 int somme = 0;
		 boolean flag = false;
		 for (int i = 0;i<mains.size();i++) {
			 
			 if (mains.get(i) == 1) {
				 flag = true;
			 }
			 somme +=  mains.get(i);
		 }
		 if (flag && 10+somme <= 21) {
			 somme += 10;
		 }
		return somme;
   }
    public ArrayList<Integer> getMain() {
		return mains;
	}
	public ArrayList<String> getMainStr(){
		return mainstr;
	}
	
	public void addint(int card) {
		mains.add(card);
	}
	public void addstr(String card) {
	    mainstr.add(card);
	}
}