package BJ;



import java.util.*;

public class Cartes {

private Scanner alpha = new Scanner(System.in);
private ArrayList<Integer> mains;
private ArrayList<String> mainsnom;
private Joueur[] joueurs;
private Croupier croupier;
private Deck paquet;
private boolean tirer;
private int nbparticipants;
	
	public Cartes(Deck paquet) {
		
		mains = new ArrayList<Integer>(5);
		mainsnom = new ArrayList<String>(5);
		joueurs = new Joueur[6];
		croupier = new Croupier();
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
	
	
	 /*public void maindep() {
		    for (int j=0;j<nbparticipants;j++) {
	            for (int i =0;i<2;i++) {
		            mains.add(i, paquet.getPaquet().get(0));
		            mainsnom.add(i, paquet.getPaquetNom().get(0));
	    	        joueurs[j].piocher(mains);
	    	        joueurs[j].piocherStr(mainsnom);
	    	        paquet.getPaquet().remove(0);
	    	        paquet.getPaquetNom().remove(0);
	    	        
	            }
	            System.out.println("Main de " + joueurs[j].getNom() + " " + joueurs[j].getMainStr() );
	            mains.clear();
	            mainsnom.clear();
	            
		    }
	       
	 }*/
	 /*public void maindepnom() {
			
	        for (int i =0;i<2;i++) {
		        mainsnom.add(i, paquet.getPaquetNom().get(0));
	    	    System.out.println("Votre main : " + mainsnom + "\n");
	    	    paquet.getPaquetNom().remove(0);
	        }
	 }*/
	        
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
			System.out.println("La main du croupier est [" + mainsnom.get(0) + ", ?]");
			tirer = true;
			while (tirer && joueurs[i].total() < 21) {
	        	/*String reponse;
	    	    Scanner clavier = new Scanner(System.in);
	    	    System.out.print("Voulez vous tirer une carte : \n");
	    	    reponse = clavier.nextLine();*/
	        	    
	     
	     
	    	 
	             System.out.println("Au tour de " + joueurs[i].getNom());
	    	     int a;
	             a = Saisie.lireEntier("\n Voulez vous tirer une carte (rappel de votre main : " + joueurs[i].getMainStr() + " )");
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
	public ArrayList<String> getMainsNom(){
		return mainsnom;
	}
	
	
	 public void croupierdep() {
		 for (int i =0;i<2;i++) {
		        mains.add((paquet.getPaquet().get(0)));
		        mainsnom.add(paquet.getPaquetNom().get(0));
		        
	    	    
	    	    paquet.getPaquet().remove(0);
	    	    paquet.getPaquetNom().remove(0);
		 }
		 
		
		 
	 }
	 
	/* public ArrayList<String> croupierdepNom() {
		 for (int i =0;i<2;i++) {
		        mainsnom.add(i, paquet.getPaquetNom().get(0));
	    	    System.out.println("\nMain du croupier : " + mainsnom + "\n");
	    	    paquet.getPaquetNom().remove(0);
		 }
		 return mainsnom;
		 
	 }*/

	 public ArrayList<Integer> afficheMainCroupier(){
		 return mains;
	 }
	public ArrayList<String> afficheMainCroupierNom(){
			 return mainsnom;
	 }
	 public String croupiertirer() {
		 while (total() < 17) {
	    	 mains.add((paquet.getPaquet().get(0)));
		     mainsnom.add(paquet.getPaquetNom().get(0));
	    	 //mains.add(paquet.getPaquet().get(0));
		     //mainsnom.add(paquet.getPaquetNom().get(0));
		     //mainsnom.add(paquet.getPaquetNom().get(0));
		     paquet.getPaquet().remove(0);
		     paquet.getPaquetNom().remove(0);
		     System.out.println("\nMain du croupier apr�s tirage : " + mainsnom);
	     }
		 if (total() > 17) {
			 System.out.println("\nMain du croupier final : " + mainsnom);
		 }
		 return mainsnom.get(0);
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
	public void conversion() {
		for (int i = 0; i<312;i++) {
			if (paquet.getPaquet().get(i) > 10) {
				paquet.getPaquet().add(i, 10);
			}
			
			
		}
		
	}
	public ArrayList<Integer> getMain(){
		return mains;
	}
	public int getSize() {
		return mains.size();
	}
	}