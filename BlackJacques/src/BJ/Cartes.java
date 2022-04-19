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

            for (int i =0; i<nbparticipants;i++) {
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
					nom = "Bot " + v;
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
				joueurs[i].miser(2);
				System.out.println("Le bot"+i+" a misé 2 !");
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
				if (i == 0) {
					algoLevel1(0);
					
				}
				else if (i == 1) {
					algoLevel2(1);
				}
				else {
					algoLevel3(2);
				}
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
		     System.out.println("\nMain du croupier apr�s tirage : " + mainCroupier);
	     }
		 if (total() >= 17) {
			 System.out.println("\nMain du croupier final : " + mainNomCroupier);
		 }
		 return mainNomCroupier.get(0);
	 }

	public void algoLevel1(int index){
		if (joueurs[index].total() == 21) {
			joueurs[index].hasBj();
		}
		while(joueurs[index].total() < 17){
			joueurs[index].addint(paquet.getPaquet().get(0));
			joueurs[index].addstr(paquet.getPaquetNom().get(0));
			paquet.getPaquet().remove(0);
			paquet.getPaquetNom().remove(0);
		}
	}
	 
	public void algoLevel2(int index) {
		boolean Draw = true;
		if (joueurs[index].total() == 21) {
			joueurs[index].hasBj();
		}
		while(Draw) {
			// Cas où le croupier à un deux comme main de départ
			if (mainCroupier.get(0) == 2) {
				// Cas où le joueur a une main du type (As,Trois), (As,Dix), mais pas (As,As)
				if ((joueurs[index].getMainStr().get(0) == "As" || joueurs[index].getMainStr().get(1) == "As") && (joueurs[index].getMainStr().get(0) != "As" || joueurs[index].getMainStr().get(1) != "As")){
					if(joueurs[index].total() < 18) {
						tirerBot(index);
					}
					else {
						Draw = false;
					}
						 
				}
				else if (joueurs[index].total() < 13) {
					tirerBot(index);
				}
				 
				else {
					Draw = false;
				}
			}// Endif 2 main de départ
			// Cas où le croupier à un trois comme main de départ
			else if (mainCroupier.get(0) == 3) {
				// Cas où le joueur a une main du type (As,Trois), (As,Dix), mais pas (As,As)
				if ((joueurs[index].getMainStr().get(0) == "As" || joueurs[index].getMainStr().get(1) == "As") && (joueurs[index].getMainStr().get(0) != "As" || joueurs[index].getMainStr().get(1) != "As")){
					if(joueurs[index].total() < 19) {
						tirerBot(index);
					}
					else {
						Draw = false;
					}
						 
				}
				else if (joueurs[index].total() < 13) {
					tirerBot(index);
				}
				 
				else {
					Draw = false;
				}
			}// Endif 3 main de départ
			// Cas où le croupier à un quatre,cinq ou six comme main de départ
			else if (mainCroupier.get(0) == 4 || mainCroupier.get(0) == 5 || mainCroupier.get(0) == 6) {
				// Cas où le joueur a une main du type (As,Trois), (As,Dix), mais pas (As,As)
				if ((joueurs[index].getMainStr().get(0) == "As" || joueurs[index].getMainStr().get(1) == "As") && (joueurs[index].getMainStr().get(0) != "As" || joueurs[index].getMainStr().get(1) != "As")){
					if(joueurs[index].total() < 19) {
						tirerBot(index);
					}
					else {
						Draw = false;
					}
						 
				}
				else if (joueurs[index].total() < 12) {
					tirerBot(index);
				}
				 
				else {
					Draw = false;
				}
			}// Endif 4, 5 ou 6 main de départ
			// Cas où le croupier à un sept ou un huit comme main de départ
			else if (mainCroupier.get(0) == 7 || mainCroupier.get(0) == 8) {
				// Cas où le joueur a une main du type (As,Trois), (As,Dix), mais pas (As,As)
				if ((joueurs[index].getMainStr().get(0) == "As" || joueurs[index].getMainStr().get(1) == "As") && (joueurs[index].getMainStr().get(0) != "As" || joueurs[index].getMainStr().get(1) != "As")){
					if(joueurs[index].total() < 18) {
						tirerBot(index);
					}
					else {
						Draw = false;
					}
						 
				}
				else if (joueurs[index].total() < 17) {
					tirerBot(index);
				}
				 
				else {
					Draw = false;
				}
			}// Endif 7 ou 8 main de départ
			// Cas où le croupier à un neuf,dix, ou As comme main de départ
			else if (mainCroupier.get(0) == 9 || mainCroupier.get(0) == 10 || mainCroupier.get(0) == 11 || mainCroupier.get(0) == 1) {
				// Cas où le joueur a une main du type (As,Trois), (As,Dix), mais pas (As,As)
				if ((joueurs[index].getMainStr().get(0) == "As" || joueurs[index].getMainStr().get(1) == "As") && (joueurs[index].getMainStr().get(0) != "As" || joueurs[index].getMainStr().get(1) != "As")){
					if(joueurs[index].total() < 19) {
						tirerBot(index);
					}
					else {
						Draw = false;
					}
						 
				}
				else if (joueurs[index].total() < 17) {
					tirerBot(index);
				}
				 
				else {
					Draw = false;
				}
			}// Endif  main de départ
		}
	}

	public void algoLevel3(int index){
		//Quand le Croupier a l'as, on tire si < 17, Si on a AS / 6 ou AS / 7
		if (joueurs[index].total() == 21) {
			joueurs[index].hasBj();
		}
		if(mainCroupier.get(0) == 1 || mainCroupier.get(0) == 11){
			if(((getValCartes(index,0) == "As" || getValCartes(index, 1) == "As") && (getValCartes(index,0) == "Sept" || getValCartes(index, 1) == "Sept")) || ((getValCartes(index,0) == "As" || getValCartes(index, 1) == "As") && (getValCartes(index,0) == "Six" || getValCartes(index, 1) == "Six"))){
				tirerBot(index);
				boucleTirer(index,17);
			}
			else {
				boucleTirer(index,17);
			}
		}//Fin croupier As
		//Quand le croupier a 10 on tire si <17 ou si A 6 ou si A 7 et on split si As As puis tirer si < 17
		else if(mainCroupier.get(0) == 10){
			if(getValCartes(index, 0) == "As" && getValCartes(index, 1) == "As"){
				split(index);
				
				tirerBot(index);
				tirerBotSplit(index);
				if (joueurs[index].totalMainSplit() == 21 ) {
					joueurs[index].hasBjSplit();
				}
				if(joueurs[index].total() == 21) {
					joueurs[index].hasBj();
				}
					
			}
			else if(((getValCartes(index,0) == "As" || getValCartes(index, 1) == "As") && (getValCartes(index,0) == "Sept" || getValCartes(index, 1) == "Sept")) || ((getValCartes(index,0) == "As" || getValCartes(index, 1) == "As") && (getValCartes(index,0) == "Six" || getValCartes(index, 1) == "Six"))){
				tirerBot(index);
				boucleTirer(index,17);
			}
			else{
				boucleTirer(index,17);
			}
		}//Fin Croupier 10
		// Croupier a 9 
		else if(mainCroupier.get(0) == 9){
			if(joueurs[index].total() == 10 || joueurs[index].total() == 11){
				doubler(index);
				joueurs[index].hasDouble();
			}
			//Avec Deux As quand on split, on ne tire qu'une carte ! 
			else if(getValCartes(index, 0) == "As" && getValCartes(index, 1) == "As"){
				split(index);
				tirerBot(index);
				tirerBotSplit(index);
				if (joueurs[index].totalMainSplit() == 21 ) {
					joueurs[index].hasBjSplit();
				}
				if(joueurs[index].total() == 21) {
					joueurs[index].hasBj();
				}
			}
			//Avec 8 8 ou 9 9 on tire quand total < 17
			else if((getValCartes(index, 0) == "Neuf" && getValCartes(index, 1) == "Neuf") || (getValCartes(index, 0) == "Huit" && getValCartes(index, 1) == "Huit")){
				split(index);
				tirerBot(index);
				tirerBotSplit(index);
				if (joueurs[index].totalMainSplit() == 21 ) {
					joueurs[index].hasBjSplit();
				}
				if(joueurs[index].total() == 21) {
					joueurs[index].hasBj();
				}
				boucleTirer(index,17);
				while(joueurs[index].totalMainSplit() < 17){
					tirerBotSplit(index);
				}
			}
			// Avec As 7 ou As 6 on tire quand meme
			else if(((getValCartes(index,0) == "As" || getValCartes(index, 1) == "As") && (getValCartes(index,0) == "Sept" || getValCartes(index, 1) == "Sept")) || ((getValCartes(index,0) == "As" || getValCartes(index, 1) == "As") && (getValCartes(index,0) == "Six" || getValCartes(index, 1) == "Six"))){
				tirerBot(index);
				boucleTirer(index,17);
			}
			else{
				boucleTirer(index,17);
			}
		}//Fin croupier 9
		//Croupier a 8
		else if(mainCroupier.get(0) == 8){
			if(joueurs[index].total() == 10 || joueurs[index].total() == 11){
				doubler(index);
				joueurs[index].hasDouble();
			}
			//Avec Deux As quand on split, on ne tire qu'une carte ! 
			else if(getValCartes(index, 0) == "As" && getValCartes(index, 1) == "As"){
				split(index);
				tirerBot(index);
				tirerBotSplit(index);
				if (joueurs[index].totalMainSplit() == 21 ) {
					joueurs[index].hasBjSplit();
				}
				if(joueurs[index].total() == 21) {
					joueurs[index].hasBj();
				}
			}
			//Avec 8 8 ou 9 9 split puis on tire quand total < 17
			else if((getValCartes(index, 0) == "Neuf" && getValCartes(index, 1) == "Neuf") || (getValCartes(index, 0) == "Huit" && getValCartes(index, 1) == "Huit")){
				split(index);
				tirerBot(index);
				tirerBotSplit(index);
				if (joueurs[index].totalMainSplit() == 21 ) {
					joueurs[index].hasBjSplit();
				}
				if(joueurs[index].total() == 21) {
					joueurs[index].hasBj();
				}
				boucleTirer(index,17);
				while(joueurs[index].totalMainSplit() < 17){
					tirerBotSplit(index);
				}
			}
			// Avec As 6 on tire quand meme
			else if((getValCartes(index,0) == "As" || getValCartes(index, 1) == "As") && (getValCartes(index,0) == "Six" || getValCartes(index, 1) == "Six")){
				tirerBot(index);
				boucleTirer(index,17);
			}
			else{
				boucleTirer(index,17);
			}
		}//Fin croupier 8
		//Le croupier a 7
		else if(mainCroupier.get(0) == 7){
			// Si on a 10 ou 11 on double
			if(joueurs[index].total() == 10 || joueurs[index].total() == 11){
				doubler(index);
				joueurs[index].hasDouble();
			}
			//As As on split et on tire 1 carte
			else if(getValCartes(index, 0) == "As" && getValCartes(index, 1) == "As"){
				split(index);
				tirerBot(index);
				tirerBotSplit(index);
				if (joueurs[index].totalMainSplit() == 21 ) {
					joueurs[index].hasBjSplit();
				}
				if(joueurs[index].total() == 21) {
					joueurs[index].hasBj();
				}
			}
			//8 8 / 7 7 / 3 3 / 2 2 , on split puis tire quand total < 17
			else if((getValCartes(index, 0) == "Huit" && getValCartes(index, 1) == "Huit") || (getValCartes(index, 0) == "Sept" && getValCartes(index, 1) == "Sept") ||  (getValCartes(index, 0) == "Trois" && getValCartes(index, 1) == "Trois") || (getValCartes(index, 0) == "Deux" && getValCartes(index, 1) == "Deux")){
				split(index);
				tirerBot(index);
				tirerBotSplit(index);
				if (joueurs[index].totalMainSplit() == 21 ) {
					joueurs[index].hasBjSplit();
				}
				if(joueurs[index].total() == 21) {
					joueurs[index].hasBj();
				}
				boucleTirer(index,17);
				while(joueurs[index].totalMainSplit() < 17){
					tirerBotSplit(index);
				}
			}
			// As 6 on tire 1 fois puis on tire si total < 17
			else if((getValCartes(index,0) == "As" || getValCartes(index, 1) == "As") && (getValCartes(index,0) == "Six" || getValCartes(index, 1) == "Six")){
				tirerBot(index);
				boucleTirer(index,17);
			}
			// On tira si total < 17
			else{
				boucleTirer(index,17);
			}
		}// Fin croupier 7
		
		// Si le croupier a 6
		else if (mainCroupier.get(0) == 6 || mainCroupier.get(0) == 5) {
			if(getValCartes(index, 0) == "As" && getValCartes(index, 1) == "As"){
				split(index);
				tirerBot(index);
				tirerBotSplit(index);
				if (joueurs[index].totalMainSplit() == 21 ) {
					joueurs[index].hasBjSplit();
				}
				if(joueurs[index].total() == 21) {
					joueurs[index].hasBj();
				}
			}
			else if((getValCartes(index, 0) == "Neuf" && getValCartes(index, 1) == "Neuf") || (getValCartes(index, 0) == "Huit" && getValCartes(index, 1) == "Huit") || (getValCartes(index, 0) == "Sept" && getValCartes(index, 1) == "Sept") || (getValCartes(index, 0) == "Six" && getValCartes(index, 1) == "Six") ||(getValCartes(index, 0) == "Quatre" && getValCartes(index, 1) == "Quatre") || (getValCartes(index, 0) == "Trois" && getValCartes(index, 1) == "Trois") || (getValCartes(index, 0) == "Deux" && getValCartes(index, 1) == "Deux")) {
				split(index);
				tirerBot(index);
				tirerBotSplit(index);
				if (joueurs[index].totalMainSplit() == 21 ) {
					joueurs[index].hasBjSplit();
				}
				if(joueurs[index].total() == 21) {
					joueurs[index].hasBj();
				}
				boucleTirer(index,17);
				while(joueurs[index].totalMainSplit() < 17){
					tirerBotSplit(index);
				}
			}
			else if (joueurs[index].total() == 9 || joueurs[index].total() == 10 || joueurs[index].total() == 11) {
				doubler(index);
				joueurs[index].hasDouble();
			}
			else if((getValCartes(index,0) == "As" || getValCartes(index, 1) == "As") && (getValCartes(index,0) == "Sept" || getValCartes(index, 1) == "Sept") || (getValCartes(index,0) == "As" || getValCartes(index, 1) == "As") && (getValCartes(index,0) == "Six" || getValCartes(index, 1) == "Six")){
				doubler(index);
				joueurs[index].hasDouble();
			}
			else if((getValCartes(index,0) == "As" || getValCartes(index, 1) == "As") && (getValCartes(index,0) == "Cinq" || getValCartes(index, 1) == "Cinq") || (getValCartes(index,0) == "As" || getValCartes(index, 1) == "As") && (getValCartes(index,0) == "Quatre" || getValCartes(index, 1) == "Quatre")){
				doubler(index);
				joueurs[index].hasDouble();
			}
			else if((getValCartes(index,0) == "As" || getValCartes(index, 1) == "As") && (getValCartes(index,0) == "Trois" || getValCartes(index, 1) == "Trois") || (getValCartes(index,0) == "As" || getValCartes(index, 1) == "As") && (getValCartes(index,0) == "Deux" || getValCartes(index, 1) == "Deux")){
				doubler(index);
				joueurs[index].hasDouble();
			}
			else {
				boucleTirer(index,9);
			}
				
		}

		/* ===================(Partie extraite de l'algo Niveau 2)==================== */
		//Le croupier a 2 
		else if (mainCroupier.get(0) == 2) {
			// Cas où le joueur a une main du type (As,Trois), (As,Dix), mais pas (As,As)
			if ((joueurs[index].getMainStr().get(0) == "As" || joueurs[index].getMainStr().get(1) == "As") && (joueurs[index].getMainStr().get(0) != "As" || joueurs[index].getMainStr().get(1) != "As")){
				boucleTirer(index, 18);
			}
			boucleTirer(index, 13);
		}// Endif 2 main de départ
		// Cas où le croupier à un trois comme main de départ
		else if (mainCroupier.get(0) == 3) {
			// Cas où le joueur a une main du type (As,Trois), (As,Dix), mais pas (As,As)
			if ((joueurs[index].getMainStr().get(0) == "As" || joueurs[index].getMainStr().get(1) == "As") && (joueurs[index].getMainStr().get(0) != "As" || joueurs[index].getMainStr().get(1) != "As")){
				boucleTirer(index, 19);
			}
			boucleTirer(index, 13);
		}// Endif 3 main de départ
		// Cas où le croupier à un quatre,cinq ou six comme main de départ
		else if (mainCroupier.get(0) == 4) {
			// Cas où le joueur a une main du type (As,Trois), (As,Dix), mais pas (As,As)
			if ((joueurs[index].getMainStr().get(0) == "As" || joueurs[index].getMainStr().get(1) == "As") && (joueurs[index].getMainStr().get(0) != "As" || joueurs[index].getMainStr().get(1) != "As")){
				boucleTirer(index, 19);
			}
			boucleTirer(index, 12);
		}// Endif 4, 5 ou 6 main de départ
	}//Fin de l'algo de niveau 3

	//Méthodes pour avoir la valeur de la carte
	public String getValCartes (int indexJ, int indexCartes){
		return joueurs[indexJ].getMainStr().get(indexCartes);
	}
	 
	public void tirerBot(int index) {
		joueurs[index].addint(paquet.getPaquet().get(0));
		joueurs[index].addstr(paquet.getPaquetNom().get(0));
		paquet.getPaquet().remove(0);
		paquet.getPaquetNom().remove(0);
	}

	public void tirerBotSplit(int index){
		joueurs[index].addstrSplit(paquet.getPaquetNom().get(0));
		joueurs[index].addintSplit(paquet.getPaquet().get(0));
		paquet.getPaquetNom().remove(0);
		paquet.getPaquet().remove(0);
	}

	public void boucleTirer(int index, int valMax){
		while(joueurs[index].total() < valMax){
			tirerBot(index);
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
		joueurs[i].miserSplit(joueurs[i].getMise());

	}


	public int getModeDeJeu(){
    	return modeDeJeu;
	}


		
}