package BJ;


import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	private ArrayList<Integer> jeu;
	private ArrayList<String> jeunoms;
		
		public Deck() {
			jeu = new ArrayList<Integer>(312);
			jeunoms = new ArrayList<String>(312);
		}
		public void generateur() {
			for (int i = 1; i<14;i++) {
				for (int j = 0;j<24;j++) {
					jeu.add(i); 
				}
				
			}
		}
		public void afficher() {
			for (int i = 0;i<312;i++) {
		    System.out.println(jeu.get(i)+ " ");
			}
		}
		
		public void affichernoms() {
			for (int i = 0;i<312;i++) {
		    System.out.println(jeunoms.get(i)+ " ");
			}
		}
		public int getSize() {
			return jeu.size();
		}
		public void conversion() {
			for (int i = 0; i<312;i++) {
				if (jeu.get(i) > 10) {
					jeu.set(i, 10);
				}
				
				
			}
		}
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