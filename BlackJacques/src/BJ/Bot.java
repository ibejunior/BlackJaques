package BJ;

import java.util.ArrayList;

public class Bot {
    private ArrayList<Integer> main;
    private int mise;
    private int banque;
    private ArrayList<String> mainstr;
    Deck paquet = new Deck();
    
    public Bot() {
    	banque = 100;
    	mise = 1;
    	main = new ArrayList<Integer>(15);
    	mainstr = new ArrayList<String>(15);
    }
    
    public int total() {
		 int somme = 0;
		 boolean flag = false;
		 for (int i = 0;i<main.size();i++) {
			 
			 if (main.get(i) == 1) {
				 flag = true;
			 }
			 somme +=  main.get(i);
		 }
		 if (flag && 10+somme <= 21) {
			 somme += 10;
		 }
		return somme;
    }
    
    //Valeur de la BR 
    public int getBanque() {
    	return banque;
    }
    //Méthode en cas d'égalite
	public void bust() {
		banque -= mise;
		mise = 0;
	}
	//Méthode en cas de victoire
	public void victoire() {
		banque += mise;
		mise = 0;
	}
	//Méthode en cas de défaite
	public void defaite() {
		banque -= mise;
		mise = 0;
	}
	//Méthode en cas de BlackJack
	public void blackjack() {
		banque += mise * 1.5;
		mise = 0;
	}
	//Méthode pour miser
	public void miser(int bet) {
		mise = bet;
	}

	//Getter
	public ArrayList<Integer> getMain() {
		return main;
	}
	public ArrayList<String> getMainStr(){
		return mainstr;
	}
	
	//AjouterCartes
	public void addint(int card) {
		main.add(card);
	}
	public void addstr(String card) {
	    mainstr.add(card);
	}
}
