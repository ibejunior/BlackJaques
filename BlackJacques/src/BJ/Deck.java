package BJ;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Cette classe permet la generation du deck avec lequel nous allons jouer.
 */
public class Deck {
	private ArrayList<Integer> jeu;
	private ArrayList<String> jeunoms;
		/**
		 * Constructeur de la classe deck
		 */
		public Deck() {
			jeu = new ArrayList<Integer>(312); // On va generer un deck de 312 cartes
			jeunoms = new ArrayList<String>(312);
		}
		/**
		 * Permet la generation du paquet, en ajoutant toutes les cartes selons leurs valeurs
		 */
		public void generateur() { 
			for (int i = 1; i<14;i++) {
				for (int j = 0;j<24;j++) {
					jeu.add(i);
				}
			}
		}
		/**
		 * Reinitialise le jeu
		 */
		public void reinitialisation() {
			jeu.clear();
			jeunoms.clear();
		}
		/**
		 * Convertit les tetes en 10, leurs valeurs dans le jeu du Blackjack
		 */
		public void conversion() {
			for (int i = 0; i<312;i++) {
				if (jeu.get(i) > 10) {
					jeu.set(i, 10);
				}
			}
		}
		/**
		 * Creation du des string pour le paquet 
		 */
		public void creation() {
			for (int i = 0; i<312;i++) {
				if (jeu.get(i) == 1) {
					jeunoms.add("As");
				}
				else if (jeu.get(i) == 2) {
					jeunoms.add("Deux");
				}
				else if (jeu.get(i) == 3) {
					jeunoms.add("Trois");
				}
				else if (jeu.get(i) == 4) {
					jeunoms.add("Quatre");
				}
				else if (jeu.get(i) == 5) {
					jeunoms.add("Cinq");
				}
				else if (jeu.get(i) == 6) {
					jeunoms.add("Six");
				}
				else if (jeu.get(i) == 7) {
					jeunoms.add("Sept");
				}
				else if (jeu.get(i) == 8) {
					jeunoms.add("Huit");
				}
				else if (jeu.get(i) == 9) {
					jeunoms.add("Neuf");
				}
				else if (jeu.get(i) == 10) {
					jeunoms.add("Dix");
				}
				else if (jeu.get(i) == 11) {
					jeunoms.add("Valet");
				}
				else if (jeu.get(i) == 12) {
					jeunoms.add("Dame");
				}
				else if (jeu.get(i) == 13) {
					jeunoms.add("Roi");
				}
			}
		}
		
		/**
		 * Permet de melanger le jeu
		 */
		public void shuffle() {
			Collections.shuffle(jeu);
		}
		
		public ArrayList<Integer> getPaquet() {
			return jeu;
		}
		
		public ArrayList<String> getPaquetNom(){
			return jeunoms;
		}
		
}	