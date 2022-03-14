

package BJ;
import java.util.ArrayList;

public class TestCartes {
	public static void main(String[] args) {
    	
		Deck paquet = new Deck();
		Deck paquetnom = new Deck();
    	
		paquet.generateur();
    	paquet.shuffle();
    	paquetnom = paquet;
    	paquet.conversion();
    	paquetnom.creation();
    	Cartes p1 = new Cartes(paquet);
    	p1.initialisation();
    	
    	
    	
    	//Cartes croupier = new Cartes(paquet);
    	//Cartes croupiernom = new Cartes (paquet);
    	
    	
    	
    	paquetnom.affichernoms();
    	
    	paquet.afficher();
    	//ArrayList<String> b = croupier.croupierdep();
    	//croupiernom.croupierdepNom();
    	//p1.maindep();
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