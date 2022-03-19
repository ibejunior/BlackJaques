package BJ;



import java.util.*;

public class Cartes {

private Scanner alpha = new Scanner(System.in);
private ArrayList<Integer> mainCroupier;
private ArrayList<String> mainNomCroupier;
private Joueur[] joueurs;

private Deck paquet;
private boolean tirer;
private int nbparticipants;
	
	public Cartes(Deck paquet) {
		
		mainCroupier = new ArrayList<Integer>(5);
		mainNomCroupier = new ArrayList<String>(5);
		joueurs = new Joueur[6];
		this.paquet = paquet;
		tirer = true;
	}
	
	public void initialisation() {
	    do {
		System.out.println("Combien de joueurs ? (Min : 1, Max : 6)");
		nbparticipants = alpha.nextInt();
	
	    } while (nbparticipants > 6 || nbparticipants < 0);
	    joueurs = new Joueur[nbparticipants];
	    
	    for (int i =0; i<nbparticipants;i++) {
	    	String nom;
	    	int v = i+1;
	    	System.out.println("\nNom du joueur " + v);
	    	nom = alpha.next();
	    	joueurs[i]  = new Joueur();
	    	joueurs[i].nomJoueur(nom);
	    }
	
	}
	
	
	public void generateur() {
		for (int i = 1; i<14;i++) {
			for (int j = 0;j<24;j++) {
				paquet.getPaquet().add(i); 
			}
			
		}
	}
	
	    	
	
	public void afficher() {
		for (int i = 0;i<120;i++) {
	    System.out.println(paquet.getPaquet().get(i)+ " ");
		}
	}
	
	        
	public void tirerjoueur() {
		for (int j=0;j<nbparticipants;j++) {
            for (int i =0;i<2;i++) {
	            
    	        joueurs[j].addint(paquet.getPaquet().get(0));
    	        joueurs[j].addstr(paquet.getPaquetNom().get(0));
	            
    	        paquet.getPaquet().remove(0);
    	        paquet.getPaquetNom().remove(0);
    	        
            }
           
            System.out.println("Main de " + joueurs[j].getNom() + " " + joueurs[j].getMainStr() );
	    }
       
 
		for (int i=0;i<nbparticipants;i++) {
			System.out.println("La main du croupier est [" + mainNomCroupier.get(0) + ", ?]");
			tirer = true;
			while (tirer && joueurs[i].total() < 21) {
	   	    	 
	             System.out.println("Au tour de " + joueurs[i].getNom());
	    	     int a;
	             a = Saisie.lireEntier("\n Voulez vous tirer une carte (rappel de votre main : " + joueurs[i].getMainStr() + " (" + joueurs[i].total() + ")" );
	    	     if (a == 1) {
	    	    	 System.out.print("\nVotre main : " + joueurs[i].getMainStr() + "\n");
	    	    	
	    	    	joueurs[i].addstr(paquet.getPaquetNom().get(0));
	    	    	joueurs[i].addint(paquet.getPaquet().get(0));
	    	        paquet.getPaquetNom().remove(0);
	    	        paquet.getPaquet().remove(0);
	    	    	
	    	        
	    	        System.out.print("\nVotre main : " + joueurs[i].getMainStr() + "\n");
	    	        }
	    	    if (a != 1) {
	    	    	tirer = false;
	    	    }   
	        
	     }
	    }
	       
	 }
	public ArrayList<String> getmainNomCroupier(){
		return mainNomCroupier;
	}
	
	
	 public void croupierdep() {
		 for (int i =0;i<2;i++) {
		        mainCroupier.add((paquet.getPaquet().get(0)));
		        mainNomCroupier.add(paquet.getPaquetNom().get(0));
		        
	    	    
	    	    paquet.getPaquet().remove(0);
	    	    paquet.getPaquetNom().remove(0);
		 }
	 }
	 

	 public ArrayList<Integer> afficheMainCroupier(){
		 return mainCroupier;
	 }
	public ArrayList<String> afficheMainCroupierNom(){
			 return mainNomCroupier;
	 }
	 public String croupiertirer() {
		 while (total() < 17) {
	    	 mainCroupier.add((paquet.getPaquet().get(0)));
		     mainNomCroupier.add(paquet.getPaquetNom().get(0));
		     paquet.getPaquet().remove(0);
		     paquet.getPaquetNom().remove(0);
		     System.out.println("\nMain du croupier apr�s tirage : " + mainNomCroupier);
	     }
		 if (total() > 17) {
			 System.out.println("\nMain du croupier final : " + mainNomCroupier);
		 }
		 return mainNomCroupier.get(0);
	 }
	 public void gagnant() {
		 
		 for (int i=0;i<nbparticipants;i++) {
		    
		     if (total() > 21 && joueurs[i].total() < 22 ) {
			     System.out.println("\nLe croupier � un score de " + total() + " " +   joueurs[i].getNom() +" � un score de " + joueurs[i].total() + " donc " + joueurs[i].getNom() +" est vainqueur");
		     }
		     else if (total() < 22 && total() == joueurs[i].total()) {
			     System.out.println( "\n" + joueurs[i].getNom() + "a le m�me score que le croupier "  + joueurs[i].getNom() + " r�cup�re sa mise");
		     }
		     else if (joueurs[i].total() > 21 ) {
			     System.out.println( "\n" + joueurs[i].getNom() + " a un score de " + joueurs[i].total() + " donc "  + joueurs[i].getNom() + " a perdu");
		     }
		     else if (total() < 22 && total() < joueurs[i].total() && joueurs[i].total() < 22  ) {
			     System.out.println( "\n" + joueurs[i].getNom() + " a un score de "  + joueurs[i].total() + " et le croupier un score de " + total() + " donc "  + joueurs[i].getNom() + " est vainqueur");
		     }
		     else if (total() < 22 && total() > joueurs[i].total() && joueurs[i].total() < 22) {
			     System.out.println( "\n" + joueurs[i].getNom() + " a un score de "  + joueurs[i].total() + " et le croupier un score de " + total() + " donc " +  joueurs[i].getNom() + " a perdu ");
		     }
	     }
	 }
	 public int total() {
		 int somme = 0;
		 boolean flag = false;
		 for (int i = 0;i<mainCroupier.size();i++) {
			 
			 if (mainCroupier.get(i) == 1) {
				 flag = true;
			 }
			 somme +=  mainCroupier.get(i);
		 }
		 if (flag && 10+somme <= 21) {
			 somme += 10;
		 }
		return somme;
	}
	public void conversion() {
		for (int i = 0; i<312;i++) {
			if (paquet.getPaquet().get(i) > 10) {
				paquet.getPaquet().add(i, 10);
			}
			
			
		}
		
	}
	public ArrayList<Integer> getMain(){
		return mainCroupier;
	}
	public int getSize() {
		return mainCroupier.size();
	}
	}