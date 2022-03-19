package BJ;


import java.util.*;

public class Joueur {
    private String nom;
    private ArrayList<Integer> mains;
    private int mise;
    private int banque;
    private ArrayList<String> mainstr;
    Deck paquet = new Deck();
    private boolean HasBj = false;

    public Joueur() {
        banque = 100;
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
    }
    public void miser(int bet) {
        mise = bet;
        banque -= mise;
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
    }
    
    public void hasBj() {
        HasBj = true;
    }
    public boolean getHasBj() {
        return HasBj;
    }
}