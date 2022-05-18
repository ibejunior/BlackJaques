package BJ;
import java.util.ArrayList;
import java.io.IOException;
import java.util.*;

/**
 * Classe qui contient le main
 * Permet par exemple de generer 100 000 parties entre les 3 bots principaux.
 */
public class TestCartes {
	public static void main(String[] args) throws IOException {
		Deck paquet = new Deck();  
		Deck paquetnom = new Deck();
		Cartes p1 = new Cartes(paquet);  
		
		paquet.generateur(); // Generation d'un paquet de 312 cartes 
    	paquet.shuffle();  // On melange le paquet
    	paquetnom = paquet;
    	paquetnom.creation(); // On genere un paquet avec le nom des cartes, identiques au precedent
    	paquet.conversion(); // On convertit les tetes en 10
    	p1.initialisation();  
		if (p1.getModeDeJeu() == 1){  // Cas du mode de jeu joueur VERSUS IAS
			for (int i=0;i<10;i++) {
				p1.renitialisation();  // On vide la main du joueur
				p1.miser();  // Le joueur mise
				p1.croupierDepart(); 
				p1.tirerjoueur(); // Le joueur tire avant le croupier
				p1.croupierTirer();  // Le croupier tire
				p1.gagnant();  // On regarde le resultat du joueur
			}
		}
		else if(p1.getModeDeJeu() == 2){
			System.out.println("100 000 parties vont etre jouees pour tester les bots ! ");  /* Nous estimons que 100 000 parties permet d'avoir un resultat avec peu d'aleatoire pour bien differencier
			la difference de niveau entre les bots. */
			for(int i = 0; i<100000; i++){ 
				if(i%20 == 0){ // On reinitialise le paquet toutes les 20 parties car c'est ce que font la plupart des casinos aujourd'hui pour eviter le comptage de cartes.
					paquet.reinitialisation();
					paquet.generateur();
					paquet.shuffle();
					paquetnom = paquet;
					paquetnom.creation();
					paquet.conversion();
				}
				p1.renitialisation();
				p1.miser();
				p1.croupierDepart();
				p1.tirerjoueur();
				p1.croupierTirer();
				p1.gagnant();
			}
			p1.afficheJoueurPourcentage(); // Permet de comparer les resultats finaux des differentes IAS.
		}
    }
}