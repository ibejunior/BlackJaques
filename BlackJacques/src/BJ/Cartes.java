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
private int modeDeJeu;
	
	public Cartes(Deck paquet) {
		
		mainCroupier = new ArrayList<Integer>(5);
		mainNomCroupier = new ArrayList<String>(5);
		joueurs = new Joueur[6];
		this.paquet = paquet;
		tirer = true;
	}
	
	public void initialisation() {
        System.out.println("===========================\n");
        System.out.println("Choisissez un mode de jeu :\n");
        System.out.println("-1) Mode de jeu classique.\n");
        System.out.println("-2) Testez la différence entre les bots.\n");
        modeDeJeu =  alpha.nextInt();
        switch(modeDeJeu) {
        case 1:
            do {
                System.out.println("Vous avez choisis le premier mode,\n");
                System.out.println("Combien de joueurs ? (Min : 2, Max : 6)");
                nbparticipants = alpha.nextInt();


            } while (nbparticipants > 6 || nbparticipants < 0);
            joueurs = new Joueur[nbparticipants];

            for (int i =0; i<nbparticipants-1;i++) {
               String nom;
               int v = i+1;
               System.out.println("\nNom du joueur " + v);
            	nom = alpha.next();
            	joueurs[i] = new Joueur();
            	joueurs[i].nomJoueur(nom);
        	}

            //joueurs[nbparticipants-1] = new Joueur();
            //joueurs[nbparticipants-1].nomJoueur("Bot Pol");

                break;
            case 2:
                System.out.println("Testons l'efficacité des différents bots sur un grand nombre de main !\n");
				nbparticipants = 3;
				for( int i = 0; i<3; i++){
					String nom;
					int v = i+1;
					nom = "Bot" + v;
					joueurs[i] = new Joueur();
					joueurs[i].nomJoueur(nom);
					System.out.println(joueurs[i].getNom());
					joueurs[i].isABot();
				}
                break;
            default :
                System.out.println("La valeur donnée n'est pas adéquate");
                break;
            }
    }
	
	
	public void miser() {
		System.out.println("Il faut désormais miser.");
		for (int i = 0;i<nbparticipants;i++) {
			if(joueurs[i].getIsABot()){
				joueurs[i].miser(1);
				System.out.println("Le bot"+i+" a miser 1 !");
			}
			else{
				System.out.println(joueurs[i].getNom() + " quelle somme voulez vous miser ? (rappel du montant de votre banque : " + joueurs[i].getBanque() + ")");
				int mise = alpha.nextInt();
				joueurs[i].miser(mise);
				System.out.println("Votre mise est donc de " + joueurs[i].getMise());
			}
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
           
            System.out.println("Main de " + joueurs[j].getNom() + " " + joueurs[j].getMainStr() + ", votre banque est d'une valeur de " + joueurs[j].getBanque() );
	    }
       
		for (int i=0;i<nbparticipants;i++) {
			if(joueurs[i].getIsABot()){
				algoLevel1(0);
				algoLevel1(1);
				algoLevel1(2);
			}
			else{
				System.out.println("La main du croupier est [" + mainNomCroupier.get(0) + ", ?]");
				tirer = true;
				if (joueurs[i].total() == 21 ) {
  	    			 joueurs[i].hasBj();
  	    		}
				while (tirer && joueurs[i].total() < 21) {

	             	System.out.println("Au tour de " + joueurs[i].getNom());
	    	     	int a;
	             	a = Saisie.lireEntier("\n Voulez vous : \n 1-tirer une carte \n 2-Doubler \n 3-Split (seulement si vous avez deux cartes identiques) \n 4-Arrêter de tirer"
	             		+ "\n rappel de votre main : " + joueurs[i].getMainStr() + " (" + joueurs[i].total() + ")" );
	    	     	if (a == 1) {
	    	    		System.out.println("\nVotre main : " + joueurs[i].getMainStr() + "\n");
	    	    		joueurs[i].addstr(paquet.getPaquetNom().get(0));
	    	    		joueurs[i].addint(paquet.getPaquet().get(0));
	    	        	paquet.getPaquetNom().remove(0);
	    	        	paquet.getPaquet().remove(0);
	    	    		System.out.println("\nVotre main : " + joueurs[i].getMainStr() + "\n");
	    	        }
	    	     	else if (a==2) {
	    	    		System.out.println("\nVotre main : " + joueurs[i].getMainStr() + "\n");
	    	    		doubler(i);
	    	    		joueurs[i].hasDouble();
	    	    		System.out.println("\nVous avez bien doublé ! Votre mise est desormais de " + joueurs[i].getMise() + " et votre main final est " + joueurs[i].getMainStr() + " (" + joueurs[i].total() + ")");
	    	    		tirer = false;
	    	     	}
	    	     	else if (a == 3 && joueurs[i].getMain().get(0) != joueurs[i].getMain().get(1)) {
	    	    	 	System.out.println("Impossible de Split, vous n'avez pas deux cartes identiques, veuillez réessayer");
	    	    	 
	    	     	}
	    	     	else if (a==3) {
	    	    		System.out.println("\nVotre main : " + joueurs[i].getMainStr() + "\n");
	    	    		joueurs[i].addintSplit(joueurs[i].getMain().get(1));
	    				joueurs[i].addstrSplit(joueurs[i].getMainStr().get(1));
	    				joueurs[i].getMain().remove(1);
	    				joueurs[i].getMainStr().remove(1);
	    				joueurs[i].hassplit();
	    				joueurs[i].miserSplit(joueurs[i].getMise());
	    	    		System.out.println("\nVous avez décidé de split votre main ! Vous devez maintenant jouer deux mains, qui sont : " + joueurs[i].getMainStr() + " et " + joueurs[i].getMainSplitstr());
	    	    		System.out.println("Commencez à tirer pour votre première main qui est " + joueurs[i].getMainStr());
	    	    		int b = Saisie.lireEntier("\n Faites 1 pour tirer et autre chose pour arrêter de tirer cette main ");
	    	    		while (b == 1) {
	    	    			joueurs[i].addstr(paquet.getPaquetNom().get(0));
		    	    		joueurs[i].addint(paquet.getPaquet().get(0));
		    	        	paquet.getPaquetNom().remove(0);
		    	        	paquet.getPaquet().remove(0);
		    	        	if (joueurs[i].getMainStr().size() == 2 && joueurs[i].total() == 21) {
		    	        		joueurs[i].hasBj();
		    	        	}
		    	        System.out.println("\nVotre main : " + joueurs[i].getMainStr() + " (" + joueurs[i].total() + ")");
		    	        b = Saisie.lireEntier("\n Souhaitez vous retirer une nouvelle carte ? (1 si oui, autre si non)");
	    	    		}
	    	    		System.out.println("Tirez maintenant pour votre seconde main qui est " + joueurs[i].getMainSplitstr());
	    	    		b = Saisie.lireEntier("\n Faites 1 pour tirer et autre chose pour arrêter de tirer cette main ");
	    	    		while (b == 1) {
	    	    			joueurs[i].addstrSplit(paquet.getPaquetNom().get(0));
		    	    		joueurs[i].addintSplit(paquet.getPaquet().get(0));
		    	        	paquet.getPaquetNom().remove(0);
		    	        	paquet.getPaquet().remove(0);
		    	        	if (joueurs[i].getMainSplitstr().size() == 2 && joueurs[i].totalMainSplit() == 21) {
		    	        		joueurs[i].hasBjSplit();
		    	       		}
		    	        	System.out.println("\nVotre main : " + joueurs[i].getMainSplitstr()  + " (" + joueurs[i].totalMainSplit() + ")");
		    	        	b = Saisie.lireEntier("\n Souhaitez vous retirer une nouvelle carte ? (1 si oui, autre si non)");
	    	    		}	
	    	    		tirer = false;
	    	     	}
	    	     	else {
	    	    		tirer = false;
	    	    	}     
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

	 public void algoLevel1(int index){
		while(joueurs[index].total() < 17){
			joueurs[index].addint(paquet.getPaquet().get(0));
			joueurs[index].addstr(paquet.getPaquetNom().get(0));
			paquet.getPaquet().remove(0);
			paquet.getPaquetNom().remove(0);
		}
	}


	 public void gagnant() {

         for (int i=0;i<nbparticipants;i++) {
             if (joueurs[i].getHassplit()) {
            	// Cas du if pour la première main
            	 
            	 
            	 if (joueurs[i].getHasBj() && total() != 21) {
                     System.out.println(joueurs[i].getNom() + " a eu un blackjack, il remporte 1.5x sa mise ");
                     joueurs[i].blackjack();
                     System.out.println("La banque du joueur " + joueurs[i].getNom() + " est donc désormais de " + joueurs[i].getBanque());
                 }

                 else if (total() > 21 && joueurs[i].total() < 22 ) {
                     System.out.println("\nLe croupier a un score de " + total() + " " +   joueurs[i].getNom() +" � un score de " + joueurs[i].total() + " donc " + joueurs[i].getNom() +" est vainqueur");
                     joueurs[i].victoire();
                     System.out.println("La banque du joueur " + joueurs[i].getNom() + " est donc désormais de " + joueurs [i].getBanque());

                 }
                 else if (total() < 22 && total() == joueurs[i].total()) {
                     System.out.println( "\n" + joueurs[i].getNom() + "a le m�me score que le croupier "  + joueurs[i].getNom() + " r�cup�re sa mise");
                     joueurs[i].egalite();
                     System.out.println("La banque du joueur " + joueurs[i].getNom() + " est donc désormais de " + joueurs [i].getBanque());
                 }
                 else if (joueurs[i].total() > 21 ) {
                     System.out.println( "\n" + joueurs[i].getNom() + " a un score de " + joueurs[i].total() + " donc "  + joueurs[i].getNom() + " a perdu");
                     
                     System.out.println("La banque du joueur " + joueurs[i].getNom() + " est donc désormais de " + joueurs [i].getBanque());
                 }
                 else if (total() < 22 && total() < joueurs[i].total() && joueurs[i].total() < 22  ) {
                     System.out.println( "\n" + joueurs[i].getNom() + " a un score de "  + joueurs[i].total() + " et le croupier un score de " + total() + " donc "  + joueurs[i].getNom() + " est vainqueur");
                     joueurs[i].victoire();
                     System.out.println("La banque du joueur " + joueurs[i].getNom() + " est donc désormais de " + joueurs [i].getBanque());

                 }
                 else if (total() < 22 && total() > joueurs[i].total() && joueurs[i].total() < 22) {
                     System.out.println( "\n" + joueurs[i].getNom() + " a un score de "  + joueurs[i].total() + " et le croupier un score de " + total() + " donc " +  joueurs[i].getNom() + " a perdu ");
                     
                     System.out.println("La banque du joueur " + joueurs[i].getNom() + " est donc désormais de " + joueurs [i].getBanque());
                 }
            	 // Cas du if pour la deuxième main
            	 
            	 
            	 if (joueurs[i].getHasBjSplit() && total() != 21) {
                     System.out.println(joueurs[i].getNom() + " a eu un blackjack, il remporte 1.5x sa mise ");
                     joueurs[i].blackjack();
                     System.out.println("La banque du joueur " + joueurs[i].getNom() + " est donc désormais de " + joueurs[i].getBanque());
                 }

                 else if (total() > 21 && joueurs[i].totalMainSplit() < 22 ) {
                     System.out.println("\nLe croupier a un score de " + total() + " " +   joueurs[i].getNom() +" � un score de " + joueurs[i].totalMainSplit() + " donc " + joueurs[i].getNom() +" est vainqueur");
                     joueurs[i].victoireSplit();
                     System.out.println("La banque du joueur " + joueurs[i].getNom() + " est donc désormais de " + joueurs [i].getBanque());

                 }
                 else if (total() < 22 && total() == joueurs[i].totalMainSplit()) {
                     System.out.println( "\n" + joueurs[i].getNom() + "a le m�me score que le croupier "  + joueurs[i].getNom() + " r�cup�re sa mise");
                     joueurs[i].egaliteSplit();
                     System.out.println("La banque du joueur " + joueurs[i].getNom() + " est donc désormais de " + joueurs [i].getBanque());
                 }
                 else if (joueurs[i].totalMainSplit() > 21 ) {
                     System.out.println( "\n" + joueurs[i].getNom() + " a un score de " + joueurs[i].totalMainSplit() + " donc "  + joueurs[i].getNom() + " a perdu");
                     
                     System.out.println("La banque du joueur " + joueurs[i].getNom() + " est donc désormais de " + joueurs[i].getBanque());
                 }
                 else if (total() < 22 && total() < joueurs[i].totalMainSplit() && joueurs[i].totalMainSplit() < 22  ) {
                     System.out.println( "\n" + joueurs[i].getNom() + " a un score de "  + joueurs[i].totalMainSplit() + " et le croupier un score de " + total() + " donc "  + joueurs[i].getNom() + " est vainqueur");
                     joueurs[i].victoireSplit();
                     System.out.println("La banque du joueur " + joueurs[i].getNom() + " est donc désormais de " + joueurs [i].getBanque());

                 }
                 else if (total() < 22 && total() > joueurs[i].totalMainSplit() && joueurs[i].totalMainSplit() < 22) {
                     System.out.println( "\n" + joueurs[i].getNom() + " a un score de "  + joueurs[i].totalMainSplit() + " et le croupier un score de " + total() + " donc " +  joueurs[i].getNom() + " a perdu ");
                     
                     System.out.println("La banque du joueur " + joueurs[i].getNom() + " est donc désormais de " + joueurs [i].getBanque());
                 }
             }
             else if (joueurs[i].getHasBj() && total() != 21) {
                 System.out.println(joueurs[i].getNom() + " a eu un blackjack, il remporte 1.5x sa mise ");
                 joueurs[i].blackjack();
                 System.out.println("La banque du joueur " + joueurs[i].getNom() + " est donc désormais de " + joueurs[i].getBanque());
             }

             else if (total() > 21 && joueurs[i].total() < 22 ) {
                 System.out.println("\nLe croupier a un score de " + total() + " " +   joueurs[i].getNom() +" � un score de " + joueurs[i].total() + " donc " + joueurs[i].getNom() +" est vainqueur");
                 joueurs[i].victoire();
                 System.out.println("La banque du joueur " + joueurs[i].getNom() + " est donc désormais de " + joueurs [i].getBanque());

             }
             else if (total() < 22 && total() == joueurs[i].total()) {
                 System.out.println( "\n" + joueurs[i].getNom() + "a le m�me score que le croupier "  + joueurs[i].getNom() + " r�cup�re sa mise");
                 joueurs[i].egalite();
                 System.out.println("La banque du joueur " + joueurs[i].getNom() + " est donc désormais de " + joueurs [i].getBanque());
             }
             else if (joueurs[i].total() > 21 ) {
                 System.out.println( "\n" + joueurs[i].getNom() + " a un score de " + joueurs[i].total() + " donc "  + joueurs[i].getNom() + " a perdu");
                 
                 System.out.println("La banque du joueur " + joueurs[i].getNom() + " est donc désormais de " + joueurs [i].getBanque());
             }
             else if (total() < 22 && total() < joueurs[i].total() && joueurs[i].total() < 22  ) {
                 System.out.println( "\n" + joueurs[i].getNom() + " a un score de "  + joueurs[i].total() + " et le croupier un score de " + total() + " donc "  + joueurs[i].getNom() + " est vainqueur");
                 joueurs[i].victoire();
                 System.out.println("La banque du joueur " + joueurs[i].getNom() + " est donc désormais de " + joueurs [i].getBanque());

             }
             else if (total() < 22 && total() > joueurs[i].total() && joueurs[i].total() < 22) {
                 System.out.println( "\n" + joueurs[i].getNom() + " a un score de "  + joueurs[i].total() + " et le croupier un score de " + total() + " donc " +  joueurs[i].getNom() + " a perdu ");
                 
                 System.out.println("La banque du joueur " + joueurs[i].getNom() + " est donc désormais de " + joueurs [i].getBanque());
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

	public void renitialisation() {
		mainCroupier.clear();
		mainNomCroupier.clear();
		for(int i = 0;i < nbparticipants; i++) {
			joueurs[i].reinitialisation();
		}
	}
	
	public void doubler(int i) {
		joueurs[i].addint(paquet.getPaquet().get(0));
		joueurs[i].addstr(paquet.getPaquetNom().get(0));
		paquet.getPaquet().remove(0);
	    paquet.getPaquetNom().remove(0);
	    joueurs[i].miserDouble(joueurs[i].getMise()*2);
	}

	public void split(int i) {
		joueurs[i].addintSplit(joueurs[i].getMain().get(1));
		joueurs[i].addstrSplit(joueurs[i].getMainStr().get(1));
		joueurs[i].getMain().remove(1);
		joueurs[i].getMainStr().remove(1);
		joueurs[i].hassplit();

	}


	public int getModeDeJeu(){
    	return modeDeJeu;
	}


		
}