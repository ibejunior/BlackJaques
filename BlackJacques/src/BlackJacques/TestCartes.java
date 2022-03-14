package BlackJacques;

import java.util.ArrayList;

public class TestCartes {
	public static void main(String[] args) {
    	
		Deck paquet = new Deck();
		//Deck paquetnom = new Deck();
		//paquet.generateur();
		
		paquet.creationn();
		paquet.shuffle();
    	paquet.conversion();
    	Cartes p1 = new Cartes(paquet);
    	p1.initialisation();
    	//Cartes croupier = new Cartes(paquet);
    	//Cartes croupiernom = new Cartes (paquet);
    	
    	//Méthodes qui permettent de vérifier la bonne création du paquet 
    	paquet.afficher();
    	//paquet.affichernoms();
    	
    	//ArrayList<String> b = croupier.croupierdep();
    	//croupiernom.croupierdepNom();
        //ArrayList<String> temp = p1.maindep(); // Peut �tre utile
    
    	//p1.maindepnom();
    	//p3.maindep();
    	//p4.maindep();
    	p1.croupierdep(); 
    	p1.tirerjoueur();
    	//p1.tirerjoueur(croupier.afficheMainCroupier(),croupier.total());
    	//p2.tirerjoueur(b,croupier.total());
    	//p3.tirerjoueur(croupier.afficheMainCroupier(),croupier.total());
    	//p4.tirerjoueur(croupier.afficheMainCroupier(),croupier.total());
    	//croupier.croupiertirer();  	
    	p1.croupiertirer();
    	p1.gagnant();
    }
}