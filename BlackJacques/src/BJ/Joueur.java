package BJ;


import java.util.*;

public class Joueur {
    private String nom;
    private ArrayList<Integer> mains;
    private int mise;
    private int miseSplit;
    private int banque;
    private ArrayList<String> mainstr;
    Deck paquet = new Deck();
    private boolean HasBj = false;
    private boolean HasBjSplit = false;
    private ArrayList<String> mainstrsplit;
    private ArrayList<Integer> mainsintsplit;
    private boolean hassplit = false;
    private boolean hasDouble = false;
    private boolean isABot = false;


    public Joueur() {
        banque = 100;
        mains = new ArrayList<Integer>(15);
        mainstr = new ArrayList<String>(15);
        mainsintsplit =  new ArrayList<Integer>(15);
        mainstrsplit =  new ArrayList<String>(15);
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
   
    
    public int totalMainSplit() {
    	int somme = 0;
        boolean flag = false;
        for (int i = 0;i<mainsintsplit.size();i++) {

            if (mainsintsplit.get(i) == 1) {
                flag = true;
            }
            somme +=  mainsintsplit.get(i);
        }
        if (flag && 10+somme <= 21) {
            somme += 10;
        }
       return somme;
   }

    public int getBanque() {
        return banque;
    }
    public void bust() {
        banque -= mise;
        mise = 0;
    }
    public void victoire() {
        banque += 2*mise;
        mise = 0;
       
    }
    public void victoireSplit() {
    	 banque += 2*miseSplit;
    	 miseSplit = 0;
    }
    public void defaiteSplit() {
    	 banque -= 2*miseSplit;
    	 miseSplit = 0;
    }
    public void egaliteSplit() {
    	banque += miseSplit;
    	miseSplit = 0;
    }
    public void defaite() {
        banque -= mise;
        mise = 0;
    }
    public void blackjack() {
        banque += mise * 2.5;
        mise = 0;
    }
    public void egalite() {
        banque += mise;
        mise = 0;
    }
    public void miser(int bet) {
        mise = bet;
        banque -= mise;
    }
    public void miserSplit(int bet) {
    	miseSplit = bet;
    	banque -= miseSplit;
    }
    
    public void miserDouble(int bet) {
    	mise = bet;
    	banque -= bet/2;
    }
    public int getMise() {
        return mise;
    }
    public void nomJoueur(String name) {
        nom = name;
    }
    public String getNom() {
        return nom;
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
    
    public void reinitialisation() {
        mains.clear();
        mainstr.clear();
        mainstrsplit.clear();
        mainsintsplit.clear();
        HasBj = false;
        HasBjSplit = false;
        hassplit = false;
        hasDouble = false;
        
    }
    
    public void hasBj() {
        HasBj = true;
    }
    public boolean getHasBj() {
        return HasBj;
    }
    public void hasBjSplit() {
    	HasBjSplit = true;
    }
    public boolean getHasBjSplit() {
    	return HasBjSplit;
    }
    public ArrayList<Integer> getMainSplit() {
		return mainsintsplit;
	}
    public ArrayList<String> getMainSplitstr() {
		return mainstrsplit;
	}
    public void addintSplit(int card) {
		mainsintsplit.add(card);
	}
    public void addstrSplit(String card) {
		mainstrsplit.add(card);
	}
    public void hassplit() {
		hassplit = true;
	}
    public boolean getHassplit() {
		return hassplit;
	}
    public void hasDouble() {
    	hasDouble = true;
    }
    public boolean getHasDouble() {
    	return hasDouble;
    }
    public void removeMainInt(){
    	mains.remove(1);
    }

    public void isABot(){
        isABot = true;
    }
    
    public boolean getIsABot(){
        return isABot;
    }

}