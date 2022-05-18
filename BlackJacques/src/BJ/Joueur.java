package BJ;
import java.util.*;

/**
 * Contient toutes les informations d'un joueur
 */
public class Joueur {

    private String nom; // Chaque joueur a un nom
    private ArrayList<Integer> mains; // La main que le joueur possede 
    private int mise; // Ce que mise le joueur
    private int miseSplit; // Dans le cas ou le joueur decide de split son jeu, il doit miser une nouvelle somme independante de la premiere, dans le sens ou il peut gagner une main et en perdre une autre
    private int banque; // Ce que le joueur possede dans sa banque 
    private ArrayList<String> mainstr; // La main que le joueur possede mais sous forme de string, pratique pour l'affichage
    Deck paquet = new Deck(); 
    private boolean HasBj = false; // Boolean pour savoir quand le joueur a effectue un blackjack
    private boolean HasBjSplit = false; // Boolean pour savoir quand la main split du joueur (s'il decide de split) effectue un blackjack
    private ArrayList<String> mainstrsplit; // La main split du joueur sous forme de string, pratique pour l'affichage
    private ArrayList<Integer> mainsintsplit; // La main split du joueur
    private boolean hassplit = false; // Pour savoir quand le joueur decide de split
    private boolean hasDouble = false; // Pour savoir quand le joueur decide de doubler
    private boolean isABot = false; // Pour savoir quand le joueur est un bot
    private int win = 0; // On compte le nombre de victoires du joueur, peut etre utile pour des statistiques sur un grand nombre de parties
    private int egalite = 0; 

    // Définition du Constructeur
    public Joueur() {
        mains = new ArrayList<Integer>(15); // On suppose que le joueur n'aura jamais plus de 15 cartes (c'est d'ailleurs impossible, car il aura forcement plus de 21)
        mainstr = new ArrayList<String>(15); 
        mainsintsplit =  new ArrayList<Integer>(15);
        mainstrsplit =  new ArrayList<String>(15);
    }

    /**
     * Cette fonction permet de calculer le total de la main d'un joueur en fonction de ses cartes
     * @return le total de la main du joueur
     */
    public int total() { 
         int somme = 0;
         boolean flag = false;
         for (int i = 0;i<mains.size();i++) {
             if (mains.get(i) == 1) { // Si jamais le joueur a un As.
                 flag = true;
             }
             somme +=  mains.get(i);
         }
         if (flag && 10+somme <= 21) { // On compte l'AS comme un 1 ou un 11 selon la situation.
             somme += 10;
         }
        return somme;
    }
   
    /**
     * Cette fonction est exactement la meme que celle ci-dessus, mais elle regarde la main split du joueur si ce dernier a split.
     * @return le total de la main split du joueur
     */
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
    
    /**
     * Permet de compter le nombre de victoire du joueur
     */
    public void addWin() {
    	win += 1;
    }
    
    /**
     * Actualise la banque du joueur en cas d'une victoire
     */
     public void victoire() {
        banque += 2*mise;
        mise = 0;  
    }
    
    /**
     * Actualise la banque du joueur en cas d'une victoire avec sa main split
     */
    public void victoireSplit() {
    	 banque += 2*miseSplit;
    	 miseSplit = 0;
    }
   
    /**
     * Actualise la banque du joueur en cas d'une egalite avec sa main split
     */
    public void egaliteSplit() {
    	banque += miseSplit;
    	miseSplit = 0;
    }
    
    /**
     * Cas ou le joueur a gagné avec un blackjack, il remporte 2.5x sa mise
     */
    public void blackjack() {
    	banque += mise * 2.5 ;
        mise = 0;
    }
    
    /**
     * Actualise la banque du joueur en cas d'une egalit
     */
    public void egalite() {
        banque += mise;
        egalite += 1;
        mise = 0;
    }
    /**
     * Permet au joueur de miser
     * @param bet ce que le joueur desire miser
     */
    public void miser(int bet) {
        mise = bet;
        banque -= mise;
    }
    /**
     * Permet au joueur de miser sa main split
     * @param bet ce que le joueur desire miser
     */
    public void miserSplit(int bet) {
    	miseSplit = bet;
    	banque -= miseSplit;
    }
    /**
     * Si jamais le joueur decide de doubler
     * @param bet
     */
    public void miserDouble(int bet) {
    	mise = bet;
    	banque -= bet/2;
    }
    
    /**
     * Le nom du joueur
     * @param name
     */
    public void nomJoueur(String name) {
        nom = name;
    }
    
    /**
     * Si le joueur decide de piocher, il faut actualiser sa main
     * @param card la carte que le joueur a pioche
     */
    public void addint(int card) {
        mains.add(card);
    }
    
    /**
     * Si le joueur decide de piocher, il faut actualiser sa main
     * @param card la carte que le joueur a pioche
     */
    public void addstr(String card) {
        mainstr.add(card);
    }
    
    /**
     * Apres chaque partie, il faut remettre a 0 certaines caracteristiques du joueur
     */
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
    
    /**
     * Permet de savoir si le joueur a eu un blackjack
     */
    public void hasBj() {
        HasBj = true;
    }
    
    /**
     * Permet de savoir si le joueur a eu un blackjack sur sa main split
     */
    public void hasBjSplit() {
    	HasBjSplit = true;
    }
    
    /**
     * Si le joueur decide de piocher une carte sur sa main split
     * @param card
     */
    public void addintSplit(int card) {
		mainsintsplit.add(card);
	}
    
    
    /**
     * Si le joueur decide de piocher une carte sur sa main split
     * @param card
     */
    public void addstrSplit(String card) {
		mainstrsplit.add(card);
	}
    
    /**
     * Permet de savoir si le joueur split
     */
    public void hassplit() {
		hassplit = true;
	}
    
    /**
     * Permet de savoir si le joueur a double 
     */
    public void hasDouble() {
    	hasDouble = true;
    }
    
    /**
     * Si le joueur Split, il faut diviser sa main en deux et donc supprimer sa premiere carte 
     */
    public void removeMainInt(){
    	mains.remove(1);
    }
    
    /**
     * Permet de definir un joueur comme un bot
     */
    public void isABot(){
        isABot = true;
    }
    
    
    //Définitions des fonctions Get utilisées
    public boolean getIsABot(){
        return isABot;
    }
    
    public boolean getHassplit() {
		return hassplit;
	}
    
    public boolean getHasDouble() {
    	return hasDouble;
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
    
    public boolean getHasBj() {
        return HasBj;
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

    public int getMise() {
        return mise;
    }
    
    public int getBanque() {
        return banque;
    }
    
    public void setBanque(int depart) {
    	banque = depart;
    }
    
}