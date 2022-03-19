

package BJ;
import java.util.ArrayList;

public class TestCartes {
	public static void main(String[] args) {
    	
		//Génère un paquet avec que des entiers
		Deck paquet = new Deck();
		
		//Paquet d'affichage
		Deck paquetnom = new Deck();
    	
		paquet.generateur();
    	paquet.shuffle();
    	paquetnom = paquet;
    	paquetnom.creation();
    	paquet.conversion();

    	Cartes p1 = new Cartes(paquet);
    	
    	p1.initialisation();
    	p1.miser();
    	/* Méthodes pour vérifier la bonne distribution du paquet
    	paquetnom.affichernoms();
    	paquet.afficher();
		*/
		
    	p1.croupierdep(); 
    	p1.tirerjoueur();
    	
    	p1.croupiertirer();
    	p1.gagnant();
    }
}