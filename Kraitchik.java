import java.util.ArrayList;
import java.lang.Math;

public class Kraitchik{
	
	/**
	 * Fonction renvoyant la liste des entiers inférieurs ou égaux à la borne et b-friables 
	 * avec la méthode du crible quadratique.
	 * @param borne borne jusqu'à laquelle on teste la b-friabilité des entiers.
	 * @param b valeur pour laquelle on teste si les entiers n'ont que des diviseurs premiers inférieurs ou égaux.
	 * @return la liste des entiers b-friables inférieurs ou égaux à la @param borne (Les entiers dont les diviseurs premiers sont <= @param b).
	 */
	public static ArrayList<Integer> cribleQuadratique(int borne, int b){
		if(borne <= 1 || b <= 1){
			System.out.println("Veuillez entrer une borne et un entier B supérieurs à 1.");
			return null;
		}
		ArrayList<Integer> bfriables = new ArrayList<>();
		int[] entiers = new int[borne];
		for(int i = 0; i < borne; i++){
			entiers[i] = i+1;
		}
		for(int i = 2; i <= b; i++){
			for(int j = i - 1; j < entiers.length; j += i){
				while(entiers[j] % i == 0){
					entiers[j] /= i;
				}
			}
		}
		for(int i = 0; i < entiers.length; i++){
			if(entiers[i] == 1){
				bfriables.add(i + 1);
			}
		}
		return bfriables;
	}

	/**
	 * Fonction testant si un entier @param n est @param b friable.
	 * @param n entier dont on veut tester la friabilité.
	 * @param b valeur pour laquelle on teste si @param n n'a que des diviseurs premiers inférieurs ou égaux.
	 * @return true si @param n est @param b friable, false sinon.
	 */
	public static boolean friable(int n, int b){
		if(n < 1 || b < 1) {
			return false;
		}
		for(int i = 2; i <= b; i++){
			while(n % i == 0){
				n /= i;
			}
		}
		return n == 1;
	}

	/**
	 * calcule la valeur du polynome de Kraitchik de paramètres @param x et @param n
	 * @param x entier d'évaluation du polynome
	 * @param n entier que l'on souhaite factoriser
	 * @return évaluation du polynome de Kraitchik en @param x pour @param n
	 */
	public static int polyKraitchik(int x, int n){
		return x * x - n;
	}

	/**
	 * Construit la liste des nombres premiers inférieurs ou égaux à @param b 
	 * avec la méthode du crible d'Eratosthène
	 * @param b borne
	 * @return la liste des nombres premiers inférieurs ou égaux à @param b
	 */
	public static ArrayList<Integer> premiersInfB(int b){
		boolean[] tab = new boolean[b + 1];
		tab[0] = false;
		tab[1] = false;
		for(int i = 2; i < b; i++){
			tab[i] = true;
		}
		int i = 2;
		while(i * i <= b){
			if(tab[i]){
				for(int j = i * i; j <= b; j += i){
					tab[j] = false;
				}
			}
			i++;
		}
		ArrayList<Integer> premiers = new ArrayList<>();
		for(int j = 0; j < b + 1; j++){
			if(tab[j]){
				premiers.add(j);
			}
		}
		return premiers;
	}

	/**
	 * Construit une liste représentant la factorisation en 
	 * nombre premiers inférieurs ou égaux à @param premier.get(premier.size()-1) de @param n
	 * dans F2
	 * @param n Nombre @param premier.get(premier.size()-1) friable à factoriser
	 * @param premiers Liste des nombres premiers inférieurs ou égaux à une certaine borne
	 * @return Liste représentant la factorisation en nombres premiers de @param n dans F2
	 */
	public static ArrayList<Integer> factoF2BFriable(int n, ArrayList<Integer> premiers){
		ArrayList<Integer> factorisationF2 = new ArrayList<>();
		int a = n;

		for(int i = 0; i < premiers.size(); i++){
			int j = 0;
			while(a % premiers.get(i) == 0){
				a /= premiers.get(i);
				j++;
			}
			factorisationF2.add(j % 2);
		}

		return factorisationF2;
	}

	/**
	 * Calcule de pgcd de @param m et @param n
	 * @param m
	 * @param n
	 * @return pgcd de @param m et @param n
	 */
	public static int pgcd(int m, int n) {
		int r;
		while (n != 0) {
		  r= m % n; m= n; n= r;
		}
		return m;
	}

	/**
	 * Vérifie si une liste ne contient que des zéros
	 * @param ligne liste dont on veut vérifier le contenu
	 * @return true si la liste ne contient que des zéros false sinon
	 */
	public static boolean ligneNulle(ArrayList<Integer> ligne){
		for(int i = 0; i < ligne.size(); i++){
			if(ligne.get(i) != 0){
				return false;
			}
		}
		return true;
	}

	/**
	 * Vérifie si une matrice contient une ligne vide
	 * @param M matrice dont on veut vérifier le contenu
	 * @return true si la matrice contient une ligne vide false sinon
	 */
	public static boolean contientLigneNulle(ArrayList<ArrayList<Integer>> M){
		for(int i = 0; i < M.size(); i++){
			if(ligneNulle(M.get(i))){
				return true;
			}
		}
		return false;
	}

	/**
	 * Multiplication de deux listes représentant des factorisations de nombres premiers dans F2,
	 * revient à additionner les lignes modulo 2
	 * @param a première ligne à multiplier
	 * @param b seconde ligne à multiplier
	 * @return résultat de @param a * @param b dans F2
	 */
	public static ArrayList<Integer> produitF2(ArrayList<Integer> a, ArrayList<Integer> b){
		if(a.size() == b.size()){
			ArrayList<Integer> resultat = new ArrayList<>();
			for(int i = 0; i < a.size(); i++){
				resultat.add((a.get(i) + b.get(i)) % 2);
			}
			return resultat;
		}
		return null;
	}

	/**
	 * Met à jour l'historique des additions de lignes effectuées sur une matrice à coefficients dans F2
	 * @param a historique de la ligne que l'on additionne à la seconde
	 * @param b historique de la ligne à laquelle on additionne la première
	 * @return nouvel historique d'opération de la seconde ligne
	 */
	public static ArrayList<Integer> majHistorique(ArrayList<Integer> a, ArrayList<Integer> b){
		ArrayList<Integer> resultat = new ArrayList<>();
		for(int i = 0; i < a.size(); i++){
			if(!b.contains(a.get(i))){
				resultat.add(a.get(i));
			}
		}
		for(int i = 0; i < b.size(); i++){
			if(!a.contains(b.get(i))){
				resultat.add(b.get(i));
			}
		}
		return resultat;
	}

	/**
	 * "échelonne" une matrice selon la méthode du pivot de Gauss jusqu'à ce qu'on obtienne une ligne nulle.
	 * La matrice n'est pas à proprement parler échelonnée car on effectue pas d'opération d'échange de ligne
	 * @param M matrice à échelonner
	 * @param historique historique d'opérations initial des lignes de la matrice
	 * @return renvoie la liste des opérations effectuées pour obtenir une ligne nulle
	 */
	public static ArrayList<Integer> pivotGauss(ArrayList<ArrayList<Integer>> M, ArrayList<ArrayList<Integer>> historique){
		int colonePivot = 0;
		ArrayList<Integer> lignesPivot = new ArrayList<>();
		while(!contientLigneNulle(M)){
			for(int i = 0; i < M.size(); i++){
				if(M.get(i).get(colonePivot) == 1 && !lignesPivot.contains(i)){
					for(int j = i + 1; j < M.size(); j++){
						if(M.get(j).get(colonePivot) == 1){
							M.set(j,produitF2(M.get(i), M.get(j)));
							historique.set(j, majHistorique(historique.get(i), historique.get(j)));
						}
					}
					lignesPivot.add(i);
				}
			}
			colonePivot++;
		}
		
		for(int i = 0; i < M.size(); i++){
			if(ligneNulle(M.get(i))){
				return historique.get(i);
			}
		}
		
		return null;
	}

	/**
	 * affiche une matrice
	 * @param M matrice à afficher
	 */
	public static void printMatrice(ArrayList<ArrayList<Integer>> M){
		for(int i = 0; i < M.size(); i++){
			for(int j = 0; j < M.get(i).size(); j++){
				System.out.print(M.get(i).get(j) + " ");
			}
			System.out.println();
		}
	}

	/**
	 * Factorise un entier @param n selon la méthode de Kraitchik
	 * (Disclaimer) du fait de la représentation des entiers en Java, 
	 * il n'est pas possible de factoriser de trop gros entiers
	 * @param n entier à factoriser
	 * @param b borne avec laquelle on teste la friabilité des résultats du polynome de Kraitchik
	 * @param piB nombre de nombres premiers inférieurs ou égaux à @param b
	 * @return renvoie deux facteurs de @param n
	 */
	public static ArrayList<Integer> factoKraitchik(int n, int b, int piB){
		ArrayList<Integer> premiers = premiersInfB(b);
		ArrayList<Integer> xi = new ArrayList<>();
		ArrayList<Integer> qi = new ArrayList<>();
		int x = Math.round(Math.round(Math.sqrt(n))) + 1;

		while(xi.size() != piB + 1){
			if(friable(polyKraitchik(x, n), b)){
				xi.add(x);
				qi.add(polyKraitchik(x, n));
			}
			x++;
		}

		ArrayList<ArrayList<Integer>> M = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < piB + 1; i++){
			M.add(factoF2BFriable(qi.get(i), premiers));
		}

		ArrayList<ArrayList<Integer>> historique = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < piB + 1; i++){
			historique.add(new ArrayList<>());
		}
		for(int i = 0; i < piB + 1; i++){
			historique.get(i).add(i);
		}

		ArrayList<Integer> carre = pivotGauss(M, historique);

		int y = 1;
		int z = 1;
		for(int i = 0; i < carre.size(); i++){
			y *= xi.get(carre.get(i));
			z *= qi.get(carre.get(i));
		}
		y = y % n;
		z = Math.round(Math.round(Math.sqrt(z))) % n;

		ArrayList<Integer> resultat = new ArrayList<>();
		resultat.add(pgcd(y - z, n));
		resultat.add(n / resultat.get(0));
		
		return resultat;
	}


	public static void main(String[] args){
		/* 
		if(args.length != 2){
			System.out.println("Le programme attend en premier une borne jusqu'à laquelle tester la B-friablilité des entiers et en second un entier B.");
			return;
		}
		int borne = Integer.valueOf(args[0]);
		int b = Integer.valueOf(args[1]);
		ArrayList<Integer> bfriables = cribleQuadratique(borne, b);
		for(int i = 0; i < bfriables.size(); i++){
			System.out.println(bfriables.get(i) + " est " + b + " friable");
		}

		System.out.println("10 est il 5-friable ? " + friable(10,5));
		System.out.println("10 est il 3-friable ? " + friable(10,3));
		*/
		ArrayList<Integer> facteurs = factoKraitchik(2041, 7, 4);
		System.out.print("2041 = ");
		for(int i = 0; i < facteurs.size() - 1; i++){
			System.out.print(facteurs.get(i) + " x ");
		}
		System.out.print(facteurs.get(facteurs.size() - 1));
		System.out.println();
	}
}