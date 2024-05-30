import java.util.ArrayList;
import java.lang.Math;

public class Kraitchik{
	
	/**
	 * Fonction renvoyant la liste des entiers inférieurs ou égaux à la borne et b-friables 
	 * avec la méthode du crible quadratique.
	 * @param borne  jusqu'à laquelle on teste la b-friabilité des entiers.
	 * @param b valeur pour laquelle on teste si les entiers n'ont que des diviseurs premiers lui étant inférieurs ou égaux.
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
	 * @param b valeur pour laquelle on teste si @param n n'a que des diviseurs premiers lui étant inférieurs ou égaux.
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
		for(int i = 2; i < tab.length; i++){
			tab[i] = true;
		}
		int i = 2;
		while(i * i <= b){
			if(tab[i]){
				for(int j = i * i; j < tab.length; j += i){
					tab[j] = false;
				}
			}
			i++;
		}
		ArrayList<Integer> premiers = new ArrayList<>();
		for(int j = 0; j < tab.length; j++){
			if(tab[j]){
				premiers.add(j);
			}
		}
		return premiers;
	}

	/**
	 * Construit une liste représentant la factorisation en 
	 * puissance de nombres premiers inférieurs ou égaux à @param premiers.get(premiers.size()-1) de @param n
	 * dans F2
	 * @param n Nombre @param premier.get(premier.size()-1) friable à factoriser
	 * @param premiers Liste des nombres premiers inférieurs ou égaux à une certaine borne
	 * @return Liste représentant la factorisation en puissance de nombres premiers modulo 2 de @param n
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
	 * Calcule le pgcd de @param m et @param n
	 * @param m
	 * @param n
	 * @return pgcd de @param m et @param n
	 */
	public static int pgcd(int m, int n) {
		int r;
		while (n != 0) {
		  r = m % n; m = n; n = r;
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
	 * Détermine un vecteur du noyau de la matrice transposée de @param M selon la méthode du pivot de Gauss.
	 * La matrice n'est pas à proprement parler échelonnée car on effectue pas d'opération d'échange de ligne
	 * @param M matrice à échelonner
	 * @param historique historique d'opérations initial des lignes de la matrice
	 * @return renvoie la liste des opérations effectuées pour obtenir un vecteur du noyau
	 */
	public static ArrayList<Integer> pivotGauss(ArrayList<ArrayList<Integer>> M, ArrayList<ArrayList<Integer>> historique){
		int colonnePivot = 0;
		ArrayList<Integer> lignesPivot = new ArrayList<>();
		while(!contientLigneNulle(M)){
			for(int i = 0; i < M.size(); i++){
				if(M.get(i).get(colonnePivot) == 1 && !lignesPivot.contains(i)){
					for(int j = i + 1; j < M.size(); j++){
						if(M.get(j).get(colonnePivot) == 1){
							M.set(j,produitF2(M.get(i), M.get(j)));
							historique.set(j, majHistorique(historique.get(i), historique.get(j)));
						}
					}
					lignesPivot.add(i);
				}
			}
			colonnePivot++;
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
	 * Vérifie si ma valeur numérique d'un double est entière
	 * @param d double dont on teste la valeur
	 * @return true si le double est entier, false sinon
	 */
	public static boolean isInt(double d){
    	return d == (int) d;
	}

	/**
	 * Donne la décomposition en puissances de facteurs premiers d'un entier @param premiers.get(premiers.size()-1) friable.
	 * @param n entier dont on veut la décomposition
	 * @param premiers liste des nombres premiers dont on se sert dans la décomposition
	 * @return liste représentant la décomposition en puissances de facteurs premiers d'un entier
	 */
	public static ArrayList<Integer> decompositionBFriable(int n, ArrayList<Integer> premiers){
		ArrayList<Integer> decomposition = new ArrayList<>();
		for(int i = 0; i < premiers.size(); i++){
			decomposition.add(0);
			while(n % premiers.get(i) == 0){
				n /= premiers.get(i);
				decomposition.set(i, decomposition.get(i) + 1);
			}
		}

		return decomposition;
	}

	/**
	 * Donne la représentation en puissances de facteurs premiers d'un produit de facteurs de même friabilité, eux même sous cette représentation
	 * @param facteurs liste des facteurs dont on veut la représentation du produit
	 * @return liste représentant la décomposition en puissances de facteurs premiers du produit de facteurs
	 */
	public static ArrayList<Integer> decompositionProduitBFriable(ArrayList<ArrayList<Integer>> facteurs){
		ArrayList<Integer> decomposition = new ArrayList<>();
		for(int i = 0; i < facteurs.get(0).size(); i++){
			decomposition.add(0);
			for(int j = 0; j < facteurs.size(); j++){
				decomposition.set(i, decomposition.get(i) + facteurs.get(j).get(i));
			}
		}

		return decomposition;
	}

	/**
	 * Donne la valeur numérique de la racine carrée d'un carré représenté sous forme de puissances de facteurs premiers
	 * @param carre nombre sous forme de puissances de facteurs premiers dont on veut la valeur numérique de sa racine carrée
	 * @param premiers liste des nombres premiers utilisés dans la représentation du nombre
	 * @return la valeur numérique de la racine carrée du nombre.
	 */
	public static int recompositionRacineBFriable(ArrayList<Integer> carre, ArrayList<Integer> premiers){
		int racine = 1;
		for(int i = 0; i < carre.size(); i++){
			for(int j = 0; j < carre.get(i); j += 2){
				racine *= premiers.get(i);
			}
		}

		return racine;
	}

	/**
	 * Factorise un entier @param n selon la méthode de Kraitchik
	 * (Disclaimer) du fait de la représentation des entiers en Java, 
	 * il n'est pas possible de factoriser de trop gros entiers
	 * @param n entier à factoriser
	 * @param b borne avec laquelle on teste la friabilité des résultats du polynome de Kraitchik
	 * @return renvoie deux facteurs de @param n
	 */
	public static ArrayList<Integer> factoKraitchik(int n, int b){
		ArrayList<Integer> resultat = new ArrayList<>();
		ArrayList<Integer> premiers = premiersInfB(b);
		ArrayList<Integer> xi = new ArrayList<>();
		ArrayList<Integer> qi = new ArrayList<>();
		if(isInt(Math.sqrt(n))){
			resultat.add((int) Math.sqrt(n));
			resultat.add((int) Math.sqrt(n));
			return resultat;
		}
		int x = (int) Math.floor(Math.sqrt(n)) + 1;
		int piB = premiers.size();
		while(xi.size() != piB + 1){
            int q = polyKraitchik(x, n);
			if(friable(q, b) && !isInt(Math.sqrt(q))){
			    xi.add(x);
			    qi.add(q);
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
		ArrayList<ArrayList<Integer>> facteurs = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < carre.size(); i++){
			facteurs.add(decompositionBFriable(qi.get(carre.get(i)), premiers));
		}

		ArrayList<Integer> vCarre = decompositionProduitBFriable(facteurs);
		
		int v = recompositionRacineBFriable(vCarre, premiers);
		int u = 1;
		for(int i = 0; i < carre.size(); i++){
			u *= xi.get(carre.get(i));
		}
		u = u % n;
		v = v % n;

		resultat.add(pgcd(u + v, n));
		resultat.add(n / resultat.get(0));
		
		return resultat;
	}


	public static void main(String[] args){
		if(args.length != 2){
			System.out.println("Le programme attend en premier un entier n impair à factoriser et en second une borne B de friabilité.");
			return;
		}
		int n = Integer.valueOf(args[0]);
		int b = Integer.valueOf(args[1]);
		ArrayList<Integer> facteurs = factoKraitchik(n, b);
		System.out.print(n + " = ");
		for(int i = 0; i < facteurs.size() - 1; i++){
			System.out.print(facteurs.get(i) + " x ");
		}
		System.out.print(facteurs.get(facteurs.size() - 1));
		System.out.println();
	}
}