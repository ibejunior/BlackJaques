package BJ;



import java.io.IOException;
import java.util.*;
/**
 * 
 * @author arthu
 *
 */
public class Cartes {

	private Scanner alpha = new Scanner(System.in);
	private ArrayList<Integer> mainCroupier;
	private ArrayList<String> mainNomCroupier;
	private Joueur[] joueurs;
	private Deck paquet;
	private boolean tirer;
	private int nbparticipants;
	private int modeDeJeu;
	
	/**
	 * Constructeur de la classe Cartes
	 * @param paquet
	 */
	public Cartes(Deck paquet) {
		mainCroupier = new ArrayList<Integer>(5);
		mainNomCroupier = new ArrayList<String>(5);
		joueurs = new Joueur[6];
		this.paquet = paquet;
		tirer = true;
	}
	
	/**
	 * Permet d'initialiser le jeu, on choisi le mode de jeu voulu,
	 * Soit le mode classique ou l'utilisateur va pouvoir jouer au 
	 * blackjack, soit le second mode, ou on va réaliser 100 000
	 * parties pour tester l'efficacite des differentes IA 
	 * implementees.
	 * @throws IOException
	 */
	public void initialisation() throws IOException {
        boolean afaitSonChoix = false;
		System.out.println("===========================\n");
        System.out.println("Choisissez un mode de jeu : \n");
        System.out.println("-1) Mode de jeu classique.\n");
        System.out.println("-2) Testez la différence entre les bots.\n");
        do {
        	try {	
        		modeDeJeu =  alpha.nextInt();
        		afaitSonChoix = true;
        		switch(modeDeJeu) {
        		case 1:
        			do {
        				System.out.println("Vous avez choisi le premier mode :\n");
        				nbparticipants = 4;
        			} while (nbparticipants > 6 || nbparticipants < 0);
        			joueurs = new Joueur[nbparticipants];     
        			String name;
        			System.out.println("\nQuel est votre nom ? ");
        			name = alpha.next();
        			joueurs[3] = new Joueur();
        			joueurs[3].nomJoueur(name);
        			joueurs[0] = new Joueur(); // creation du bot de niveau 1
        			joueurs[0].nomJoueur("IA de niveau 1");
        			joueurs[1] = new Joueur(); // creation du bot de niveau 2
        			joueurs[1].nomJoueur("IA de niveau 2");
        			joueurs[2] = new Joueur(); // creation du bot de niveau 3
        			joueurs[2].nomJoueur("IA de niveau 3");
        			joueurs[0].isABot();
        			joueurs[1].isABot();
        			joueurs[2].isABot();
        			for (int i = 0;i<nbparticipants;i++) {
        				joueurs[i].setBanque(100);
        			}
        			break;
                //Fin du cas 1
                //Début du cas 2
        		case 2:
        			System.out.println("Testons l'efficacite des differents bots sur un grand nombre de main !\n");
        			nbparticipants = 3;
        			for( int i = 0; i<3; i++){
        				String nom;
        				int v = i+1;
        				nom = "Bot " + v;
        				joueurs[i] = new Joueur();
        				joueurs[i].nomJoueur(nom);
        				System.out.println(joueurs[i].getNom());
        				joueurs[i].isABot();
        				joueurs[i].setBanque(100000);
        			}//Fin du for
        			break;
                //Fin du cas 2
                //Début du cas par defaut
        		default :
        			System.out.println("La valeur donnee n'est pas adequate.");
        			afaitSonChoix = false;
                	break;
                //Fin du cas par defaut
        		}//Fin du Switch
        	
        	}catch (InputMismatchException e) {
    			System.out.println("La valeur donnee n'est pas adequate.");
    			alpha.nextLine();
        	}//Fin du Try/catch
        }while(!afaitSonChoix);//Fin du do/while
    }//Fin de la methode initialisation 
	
	
	/**
	 * Methode permettant de demander la mise que 
	 * l'utilisateur va parier, et d'initialiser
	 * la mise d'un bot a la valeur 2
	 */
	public void miser() {
		System.out.println("Il faut désormais miser.");
		for (int i = 0;i<nbparticipants;i++) {
			if(joueurs[i].getIsABot()){
				joueurs[i].miser(2);
				System.out.println("Le bot"+i+" a misé 2 !");
			}
			else{
				System.out.println(joueurs[i].getNom() + " vous misez 2 car la mise n'a aucune incidence sur les performances globales (en terme de % de réussite) et le but et de comparer les IA");
				joueurs[i].miser(2);
				System.out.println("Votre mise est donc de " + joueurs[i].getMise());
			}
		}
	}
	
	/**
	 * Methode permettant de generer un paquet 
	 */
	public void generateur() {
		for (int i = 1; i<14;i++) {
			for (int j = 0;j<24;j++) {
				paquet.getPaquet().add(i); 
			}
		}
	}   	
	
	/**
	 *         
	 * @throws IOException
	 */
	public void tirerjoueur() throws IOException{
		for (int j=0;j<nbparticipants;j++) {
            for (int i =0;i<2;i++) {
	            joueurs[j].addint(paquet.getPaquet().get(0));
    	        joueurs[j].addstr(paquet.getPaquetNom().get(0));
	            paquet.getPaquet().remove(0);
    	        paquet.getPaquetNom().remove(0); 
            }//Fin du for i
            System.out.println("Main de " + joueurs[j].getNom() + " " + joueurs[j].getMainStr() + ", votre banque est d'une valeur de " + joueurs[j].getBanque() );
	    }//Fin du for j
       
		
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
			}//Fin du if joueurs[i].getIsABot()
			
			else{
				boolean afaitSonChoix = false;
				System.out.println("La main du croupier est [" + mainNomCroupier.get(0) + ", ?]");
				tirer = true;
				if (joueurs[i].total() == 21 ) {
					joueurs[i].hasBj();
  	    		}
				do {
					try {
						while (tirer && joueurs[i].total() < 21) {
							System.out.println("Au tour de " + joueurs[i].getNom());
							int choix;
							System.out.println("\n Voulez vous : \n 1-tirer une carte \n 2-Doubler \n 3-Split (seulement si vous avez deux cartes identiques) \n 4-Arreter de tirer"
									+ "\n rappel de votre main : " + joueurs[i].getMainStr() + " (" + joueurs[i].total() + ")" );
							choix = alpha.nextInt();
							afaitSonChoix = true;
							switch(choix) {
							case 1 :
								System.out.println("\nVotre main : " + joueurs[i].getMainStr() + "\n");
								joueurs[i].addstr(paquet.getPaquetNom().get(0));
								joueurs[i].addint(paquet.getPaquet().get(0));
								paquet.getPaquetNom().remove(0);
								paquet.getPaquet().remove(0);
								System.out.println("\nVotre main : " + joueurs[i].getMainStr() + "\n");
								break;
								//Fin du cas 1
							//Debut du cas 2
							case 2 :
								System.out.println("\nVotre main : " + joueurs[i].getMainStr() + "\n");
								doubler(i);
								joueurs[i].hasDouble();
								System.out.println("\nVous avez bien double ! Votre mise est desormais de " + joueurs[i].getMise() + " et votre main final est " + joueurs[i].getMainStr() + " (" + joueurs[i].total() + ")");
								tirer = false;
								break;
								//Fin du cas 2
							//Debut du cas 3
							case 3 :
								if (joueurs[i].getMain().get(0) != joueurs[i].getMain().get(1)) {
									System.out.println("Impossible de Split, vous n'avez pas deux cartes identiques, veuillez reessayer");
									afaitSonChoix = false;
								}//Fin du if cas 3
								else{
									System.out.println("\nVotre main : " + joueurs[i].getMainStr() + "\n");
									joueurs[i].addintSplit(joueurs[i].getMain().get(1));
									joueurs[i].addstrSplit(joueurs[i].getMainStr().get(1));
									joueurs[i].getMain().remove(1);
									joueurs[i].getMainStr().remove(1);
									joueurs[i].hassplit();
									joueurs[i].miserSplit(joueurs[i].getMise());
									System.out.println("\nVous avez decide de split votre main ! Vous devez maintenant jouer deux mains, qui sont : " + joueurs[i].getMainStr() + " et " + joueurs[i].getMainSplitstr());
									System.out.println("Commencez a tirer pour votre premiere main qui est " + joueurs[i].getMainStr());
									int b = Saisie.lireEntier("\n Faites 1 pour tirer et autre chose pour arreter de tirer cette main ");
									while (b == 1 && joueurs[i].total() <= 21) {
										joueurs[i].addstr(paquet.getPaquetNom().get(0));
										joueurs[i].addint(paquet.getPaquet().get(0));
										paquet.getPaquetNom().remove(0);
										paquet.getPaquet().remove(0);
										if (joueurs[i].getMainStr().size() == 2 && joueurs[i].total() == 21) {
											joueurs[i].hasBj();
										}
										System.out.println("\nVotre main : " + joueurs[i].getMainStr() + " (" + joueurs[i].total() + ")");
										b = Saisie.lireEntier("\n Souhaitez vous retirer une nouvelle carte ? (1 si oui, autre si non)");
									}//Fin du while cas 3 
									System.out.println("Tirez maintenant pour votre seconde main qui est " + joueurs[i].getMainSplitstr());
									b = Saisie.lireEntier("\n Faites 1 pour tirer et autre chose pour arreter de tirer cette main ");
									while (b == 1 && joueurs[i].totalMainSplit() <= 21) {
										joueurs[i].addstrSplit(paquet.getPaquetNom().get(0));
										joueurs[i].addintSplit(paquet.getPaquet().get(0));
										paquet.getPaquetNom().remove(0);
										paquet.getPaquet().remove(0);
										if (joueurs[i].getMainSplitstr().size() == 2 && joueurs[i].totalMainSplit() == 21) {
											joueurs[i].hasBjSplit();
										}
										System.out.println("\nVotre main : " + joueurs[i].getMainSplitstr()  + " (" + joueurs[i].totalMainSplit() + ")");
										b = Saisie.lireEntier("\n Souhaitez vous retirer une nouvelle carte ? (1 si oui, autre si non)");
									}//Fin du deuxieme while cas 3
									tirer = false;
								}//Fin du else cas 3 
								break;
								//Fin du cas 3
							//Debut du cas 4	             	
							case 4:
								tirer = false;
								break;
								//Fin du cas 4
							//Debut du cas par defaut
							default :
								System.out.println("Veuillez renseigner une valeur adequate");
								afaitSonChoix = false;
							}//Fin du cas par defaut
						}//Fin du while principal
					} catch (InputMismatchException e) {
	        			System.out.println("Veuillez renseigner une valeur adéquate");
	        			alpha.nextLine();
	        		}//Fin du try/catch
				}while(!afaitSonChoix);// Fin du do/while
			}//Fin du else principal
	    }//Fin du for i
	}//Fin de la methode tirerJoueur
	
	/**
	 * 
	 */
	public void croupierDepart() {
		for (int i =0;i<2;i++) {
			mainCroupier.add((paquet.getPaquet().get(0)));
		    mainNomCroupier.add(paquet.getPaquetNom().get(0));
		    paquet.getPaquet().remove(0);
		    paquet.getPaquetNom().remove(0);
		}
	}
	
	
	/**
	 * 
	 * @return
	 */
	public String croupierTirer() {
		System.out.println("La main du croupier avant tirage est : " + mainNomCroupier + "( " + total() + ")");
		while (total() < 17) {
	    	mainCroupier.add((paquet.getPaquet().get(0)));
		    mainNomCroupier.add(paquet.getPaquetNom().get(0));
		    paquet.getPaquet().remove(0);
		    paquet.getPaquetNom().remove(0);
		    System.out.println("\nMain du croupier apres tirage : " + mainNomCroupier +  "( " + total() + ")");
	    }
		if (total() >= 17) {
			System.out.println("\nMain du croupier final : " + mainNomCroupier);
		}
		return mainNomCroupier.get(0);
	}

	/**
	 * 
	 * @param index
	 */
	public void algoLevel1(int index){
		if (joueurs[index].total() == 21) {
			joueurs[index].hasBj();
		}
		boucleTirer(index, 17);
	}
	
	/**
	 * 
	 * @param index
	 */
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
	}//Fin de la methode algoLevel2

	/**
	 * 
	 * @param index
	 */
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
		
		// Si le croupier a 6 ou 5
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
				boucleTirer(index,12);
				while(joueurs[index].totalMainSplit() < 12){
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
				boucleTirer(index,12);
			}
				
		}//Fin croupier 5 ou 6
		//Si le croupier a 4 
		else if (mainCroupier.get(0) == 4){
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
			else if((getValCartes(index, 0) == "Neuf" && getValCartes(index, 1) == "Neuf") || (getValCartes(index, 0) == "Huit" && getValCartes(index, 1) == "Huit") || (getValCartes(index, 0) == "Sept" && getValCartes(index, 1) == "Sept") || (getValCartes(index, 0) == "Six" && getValCartes(index, 1) == "Six") || (getValCartes(index, 0) == "Trois" && getValCartes(index, 1) == "Trois") || (getValCartes(index, 0) == "Deux" && getValCartes(index, 1) == "Deux")) {
				split(index);
				tirerBot(index);
				tirerBotSplit(index);
				if (joueurs[index].totalMainSplit() == 21 ) {
					joueurs[index].hasBjSplit();
				}
				if(joueurs[index].total() == 21) {
					joueurs[index].hasBj();
				}
				boucleTirer(index,12);
				while(joueurs[index].totalMainSplit() < 12){
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
			else if ((getValCartes(index, 0) == "As" || getValCartes(index, 1) == "As") && (getValCartes(index, 0) == "Trois" || (getValCartes(index, 1) == "Trois"))){
				tirerBot(index);
			}
			else if ((getValCartes(index, 0) == "As" || getValCartes(index, 1) == "As") && (getValCartes(index, 0) == "Deux" || (getValCartes(index, 1) == "Deux"))){
				tirerBot(index);
				if(joueurs[index].total() == 14){
					tirerBot(index);
				}
			}
			else {
				boucleTirer(index,12);
			}
		}//Fin croupier a 4
		//Si le croupier a 3
		else if (mainCroupier.get(0) == 3){
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
			else if((getValCartes(index, 0) == "Neuf" && getValCartes(index, 1) == "Neuf") || (getValCartes(index, 0) == "Huit" && getValCartes(index, 1) == "Huit") || (getValCartes(index, 0) == "Sept" && getValCartes(index, 1) == "Sept") || (getValCartes(index, 0) == "Six" && getValCartes(index, 1) == "Six") || (getValCartes(index, 0) == "Trois" && getValCartes(index, 1) == "Trois") || (getValCartes(index, 0) == "Deux" && getValCartes(index, 1) == "Deux")) {
				split(index);
				tirerBot(index);
				tirerBotSplit(index);
				if (joueurs[index].totalMainSplit() == 21 ) {
					joueurs[index].hasBjSplit();
				}
				if(joueurs[index].total() == 21) {
					joueurs[index].hasBj();
				}
				boucleTirer(index,13);
				while(joueurs[index].totalMainSplit() < 13){
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
			else if ((getValCartes(index, 0) == "As" || getValCartes(index, 1) == "As") && (getValCartes(index, 0) == "Cinq" || (getValCartes(index, 1) == "Cinq"))){
				tirerBot(index);
				boucleTirer(index, 17);
			}
			else if ((getValCartes(index, 0) == "As" || getValCartes(index, 1) == "As") && (getValCartes(index, 0) == "Quatre" || (getValCartes(index, 1) == "Quatre"))){
				tirerBot(index);
				boucleTirer(index, 17);
			}
			else if ((getValCartes(index, 0) == "As" || getValCartes(index, 1) == "As") && (getValCartes(index, 0) == "Trois" || (getValCartes(index, 1) == "Trois"))){
				tirerBot(index);
				boucleTirer(index, 17);
			}
			else if ((getValCartes(index, 0) == "As" || getValCartes(index, 1) == "As") && (getValCartes(index, 0) == "Deux" || (getValCartes(index, 1) == "Deux"))){
				tirerBot(index);
				boucleTirer(index, 17);
			}
			else {
				boucleTirer(index, 13);
			}
		}//Fin croupier 3
		//Si le croupier a 2
		else if (mainCroupier.get(0) == 2){
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
			else if((getValCartes(index, 0) == "Neuf" && getValCartes(index, 1) == "Neuf") || (getValCartes(index, 0) == "Huit" && getValCartes(index, 1) == "Huit") || (getValCartes(index, 0) == "Sept" && getValCartes(index, 1) == "Sept") || (getValCartes(index, 0) == "Six" && getValCartes(index, 1) == "Six") || (getValCartes(index, 0) == "Trois" && getValCartes(index, 1) == "Trois") || (getValCartes(index, 0) == "Deux" && getValCartes(index, 1) == "Deux")) {
				split(index);
				tirerBot(index);
				tirerBotSplit(index);
				if (joueurs[index].totalMainSplit() == 21 ) {
					joueurs[index].hasBjSplit();
				}
				if(joueurs[index].total() == 21) {
					joueurs[index].hasBj();
				}
				boucleTirer(index,13);
				while(joueurs[index].totalMainSplit() < 13){
					tirerBotSplit(index);
				}
			}
			else if (joueurs[index].total() == 10 || joueurs[index].total() == 11) {
				doubler(index);
				joueurs[index].hasDouble();
			}
			else if ((getValCartes(index, 0) == "As" || getValCartes(index, 1) == "As") && (getValCartes(index, 0) == "Six" || (getValCartes(index, 1) == "Six"))){
				tirerBot(index);
				boucleTirer(index, 18);
			}
			else if ((getValCartes(index, 0) == "As" || getValCartes(index, 1) == "As") && (getValCartes(index, 0) == "Cinq" || (getValCartes(index, 1) == "Cinq"))){
				tirerBot(index);
				boucleTirer(index, 18);
			}
			else if ((getValCartes(index, 0) == "As" || getValCartes(index, 1) == "As") && (getValCartes(index, 0) == "Quatre" || (getValCartes(index, 1) == "Quatre"))){
				tirerBot(index);
				boucleTirer(index, 18);
			}
			else if ((getValCartes(index, 0) == "As" || getValCartes(index, 1) == "As") && (getValCartes(index, 0) == "Trois" || (getValCartes(index, 1) == "Trois"))){
				tirerBot(index);
				boucleTirer(index, 18);
			}
			else if ((getValCartes(index, 0) == "As" || getValCartes(index, 1) == "As") && (getValCartes(index, 0) == "Deux" || (getValCartes(index, 1) == "Deux"))){
				tirerBot(index);
				boucleTirer(index, 18);
			}
			else {
				boucleTirer(index, 13);
			}
		}//Fin croupier 2
	}//Fin de la methode algoLevel3

	/**
	 * 
	 * @param index
	 */
	public void tirerBot(int index) {
		joueurs[index].addint(paquet.getPaquet().get(0));
		joueurs[index].addstr(paquet.getPaquetNom().get(0));
		paquet.getPaquet().remove(0);
		paquet.getPaquetNom().remove(0);
	}

	/**
	 * 
	 * @param index
	 */
	public void tirerBotSplit(int index){
		joueurs[index].addstrSplit(paquet.getPaquetNom().get(0));
		joueurs[index].addintSplit(paquet.getPaquet().get(0));
		paquet.getPaquetNom().remove(0);
		paquet.getPaquet().remove(0);
	}

	/**
	 * 
	 * @param index
	 * @param valMax
	 */
	public void boucleTirer(int index, int valMax){
		while(joueurs[index].total() < valMax){
			tirerBot(index);
		}
	}

	public void gagnant() {
        for (int i=0;i<nbparticipants;i++) {
        	if (joueurs[i].getHassplit()) {
            // Cas du if pour la première main
        		System.out.println("\n" + joueurs[i].getNom() + " possède la première main :" + joueurs[i].getMainStr() + "(" + joueurs[i].total() + ")");
        		System.out.println("\n" + joueurs[i].getNom() + " possède la seconde main :" + joueurs[i].getMainSplitstr() + "(" + joueurs[i].totalMainSplit() + ")");
            	if (joueurs[i].getHasBj() && total() != 21) {
                    System.out.println(joueurs[i].getNom() + " a eu un blackjack, il remporte 1.5x sa mise ");
                    joueurs[i].blackjack();
                    System.out.println("La banque du joueur " + joueurs[i].getNom() + " est donc désormais de " + joueurs[i].getBanque());
                    joueurs[i].addWin();
                }
            	else if (total() > 21 && joueurs[i].total() < 22 ) {
                    System.out.println("\nLe croupier a un score de " + total() + " " +   joueurs[i].getNom() +" a un score de " + joueurs[i].total() + " donc " + joueurs[i].getNom() +" est vainqueur");
                    joueurs[i].victoire();
                    System.out.println("La banque du joueur " + joueurs[i].getNom() + " est donc désormais de " + joueurs [i].getBanque());
                    joueurs[i].addWin();
                }
                else if (total() < 22 && total() == joueurs[i].total()) {
                    System.out.println( "\n" + joueurs[i].getNom() + "a le meme score que le croupier "  + joueurs[i].getNom() + " recupere sa mise");
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
                    joueurs[i].addWin();
                }
                else if (total() < 22 && total() > joueurs[i].total() && joueurs[i].total() < 22) {
                	System.out.println( "\n" + joueurs[i].getNom() + " a un score de "  + joueurs[i].total() + " et le croupier un score de " + total() + " donc " +  joueurs[i].getNom() + " a perdu ");
                    System.out.println("La banque du joueur " + joueurs[i].getNom() + " est donc désormais de " + joueurs [i].getBanque());
                }
            	// Cas du if pour la deuxième main 
            	if (joueurs[i].getHasBjSplit() && total() != 21) {
                    System.out.println(joueurs[i].getNom() + " a eu un blackjack, il remporte 1.5x sa mise ");
                    joueurs[i].blackjack();
                    joueurs[i].addWin();
                    System.out.println("La banque du joueur " + joueurs[i].getNom() + " est donc désormais de " + joueurs[i].getBanque());
                }
            	else if (total() > 21 && joueurs[i].totalMainSplit() < 22 ) {
                    System.out.println("\nLe croupier a un score de " + total() + " " +   joueurs[i].getNom() +" a un score de" + joueurs[i].totalMainSplit() + " donc " + joueurs[i].getNom() +" est vainqueur");
                    joueurs[i].victoireSplit();
                    joueurs[i].addWin();
                    System.out.println("La banque du joueur " + joueurs[i].getNom() + " est donc désormais de " + joueurs [i].getBanque());
                }
                else if (total() < 22 && total() == joueurs[i].totalMainSplit()) {
                	System.out.println( "\n" + joueurs[i].getNom() + "a le meme score que le croupier "  + joueurs[i].getNom() + " recupere sa mise");
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
                    joueurs[i].addWin();
                }
                else if (total() < 22 && total() > joueurs[i].totalMainSplit() && joueurs[i].totalMainSplit() < 22) {
                    System.out.println( "\n" + joueurs[i].getNom() + " a un score de "  + joueurs[i].totalMainSplit() + " et le croupier un score de " + total() + " donc " +  joueurs[i].getNom() + " a perdu ");   
                    System.out.println("La banque du joueur " + joueurs[i].getNom() + " est donc désormais de " + joueurs [i].getBanque());
                }
            }//Fin du if principal
            else if (joueurs[i].getHasBj() && total() != 21) {
            	System.out.println("\n" + joueurs[i].getNom() + " possède la main :" + joueurs[i].getMainStr() + "(" + joueurs[i].total() + ")");
            	System.out.println(joueurs[i].getNom() + " a eu un blackjack, il remporte 1.5x sa mise ");
                joueurs[i].blackjack();
                System.out.println("La banque du joueur " + joueurs[i].getNom() + " est donc désormais de " + joueurs[i].getBanque());
                joueurs[i].addWin();
            }
            else if (total() > 21 && joueurs[i].total() < 22 ) {
            	System.out.println("\n" + joueurs[i].getNom() + " possède la main :" + joueurs[i].getMainStr() + "(" + joueurs[i].total() + ")");
            	System.out.println("\nLe croupier a un score de " + total() + " " +   joueurs[i].getNom() +" a un score " + joueurs[i].total() + " donc " + joueurs[i].getNom() +" est vainqueur");
                joueurs[i].victoire();
                System.out.println("La banque du joueur " + joueurs[i].getNom() + " est donc désormais de " + joueurs [i].getBanque());
                joueurs[i].addWin();
            }
            else if (total() < 22 && total() == joueurs[i].total()) {
            	System.out.println("\n" + joueurs[i].getNom() + " possède la main :" + joueurs[i].getMainStr() + "(" + joueurs[i].total() + ")");
            	System.out.println( "\n" + joueurs[i].getNom() + "a le meme score que le croupier "  + joueurs[i].getNom() + " recupere sa mise");
                joueurs[i].egalite();
                System.out.println("La banque du joueur " + joueurs[i].getNom() + " est donc désormais de " + joueurs [i].getBanque());
            }
            else if (joueurs[i].total() > 21 ) {
            	System.out.println("\n" + joueurs[i].getNom() + " possède la main :" + joueurs[i].getMainStr() + "(" + joueurs[i].total() + ")");
            	System.out.println( "\n" + joueurs[i].getNom() + " a un score de " + joueurs[i].total() + " donc "  + joueurs[i].getNom() + " a perdu");
                System.out.println("La banque du joueur " + joueurs[i].getNom() + " est donc désormais de " + joueurs [i].getBanque());
            }
            else if (total() < 22 && total() < joueurs[i].total() && joueurs[i].total() < 22  ) {
            	System.out.println("\n" + joueurs[i].getNom() + " possède la main :" + joueurs[i].getMainStr() + "(" + joueurs[i].total() + ")");
            	System.out.println( "\n" + joueurs[i].getNom() + " a un score de "  + joueurs[i].total() + " et le croupier un score de " + total() + " donc "  + joueurs[i].getNom() + " est vainqueur");
                joueurs[i].victoire();
                System.out.println("La banque du joueur " + joueurs[i].getNom() + " est donc désormais de " + joueurs [i].getBanque());
                joueurs[i].addWin();
            }
            else if (total() < 22 && total() > joueurs[i].total() && joueurs[i].total() < 22) {
            	System.out.println("\n" + joueurs[i].getNom() + " possède la main :" + joueurs[i].getMainStr() + "(" + joueurs[i].total() + ")");
            	System.out.println( "\n" + joueurs[i].getNom() + " a un score de "  + joueurs[i].total() + " et le croupier un score de " + total() + " donc " +  joueurs[i].getNom() + " a perdu ");
                System.out.println("La banque du joueur " + joueurs[i].getNom() + " est donc désormais de " + joueurs [i].getBanque());
            }
        }//Fin du for i
    }//Fin de la methode gagnant
	
	/**
	 * 
	 * @return
	 */
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
	
	/**
	 * 
	 */
	
	/*
	public void conversion() {
		for (int i = 0; i<312;i++) {
			if (paquet.getPaquet().get(i) > 10) {
				paquet.getPaquet().add(i, 10);
			}
		}
	}
	*
	*/
	
	/**
	 * 
	 */
	public void renitialisation() {
		mainCroupier.clear();
		mainNomCroupier.clear();
		for(int i = 0;i < nbparticipants; i++) {
			joueurs[i].reinitialisation();
		}
	}
	
	/**
	 * 
	 * @param i
	 */
	public void doubler(int i) {
		joueurs[i].addint(paquet.getPaquet().get(0));
		joueurs[i].addstr(paquet.getPaquetNom().get(0));
		paquet.getPaquet().remove(0);
	    paquet.getPaquetNom().remove(0);
	    joueurs[i].miserDouble(joueurs[i].getMise()*2);
	}

	/**
	 * 
	 * @param i
	 */
	public void split(int i) {
		joueurs[i].addintSplit(joueurs[i].getMain().get(1));
		joueurs[i].addstrSplit(joueurs[i].getMainStr().get(1));
		joueurs[i].getMain().remove(1);
		joueurs[i].getMainStr().remove(1);
		joueurs[i].hassplit();
		joueurs[i].miserSplit(joueurs[i].getMise());

	}
	
	/**
	 * 
	 */
	public void afficheJoueurPourcentage() {
		for (int i = 0;i<nbparticipants;i++) {
			double pourcentage = (joueurs[i].getBanque()/(200000.000)) * 100;
			System.out.println("\n Le joueur " + joueurs[i].getNom() + " à eu un taux de rendement de " + pourcentage*2 + "% sur 100 000 parties");
		}
	}

	/**
	 * 
	 * @return
	 */
	public int getModeDeJeu(){
    	return modeDeJeu;
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<String> getmainNomCroupier(){
		return mainNomCroupier;
	}
	
	/**
	 * 
	 * @param indexJ
	 * @param indexCartes
	 * @return
	 */
	public String getValCartes (int indexJ, int indexCartes){
		return joueurs[indexJ].getMainStr().get(indexCartes);
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Integer> getMain(){
		return mainCroupier;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getSize() {
		return mainCroupier.size();
	}
	
}