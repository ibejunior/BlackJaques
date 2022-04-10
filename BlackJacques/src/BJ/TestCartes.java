

package BJ;
import java.util.ArrayList;
import java.util.*;


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
		if(p1.getModeDeJeu() == 1){
			for (int i=0;i<2;i++) {
				p1.renitialisation();
				p1.miser();
				p1.croupierdep(); 
				p1.tirerjoueur();
				p1.croupiertirer();
				p1.gagnant();
			}
		}
		else if(p1.getModeDeJeu() == 2){
			System.out.println("100 parties vont être joué pour tester les bots ! ");
			for(int i = 0; i<2; i++){
				p1.renitialisation();
				p1.miser();
				p1.croupierdep();
				p1.tirerjoueur();
				p1.croupiertirer();
				p1.gagnant();
			}
		}


    }
}