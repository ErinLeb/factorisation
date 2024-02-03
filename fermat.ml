let rec factorisation_fermat n r s fact =
    if r = n then n::fact
    else if Float.is_integer (Float.sqrt (Int.to_float s)) then 
        let a, b = r + Float.to_int (Float.sqrt (Int.to_float s)), r - Float.to_int (Float.sqrt (Int.to_float s)) in
        let ra, rb = Float.to_int (Float.floor (Float.sqrt (Int.to_float a))), Float.to_int (Float.floor (Float.sqrt (Int.to_float b))) in
        let sa, sb = ra * ra - a, rb * rb - b in
        (if a = n then n::fact
        else factorisation_fermat a ra sa (factorisation_fermat b rb sb fact))
    else let nr = r + 1 in
    factorisation_fermat n nr (nr * nr - n) fact

(* Factorise l'entier n par la puissance de 2 maximale puis factorise le reste avec la méthode de fermat *)
let rec factorisation_pair n fact =
    if n mod 2 = 0 && n > 1 then factorisation_pair (n/2) (2::fact)
    else if n < 1 then n::fact
    else if n = 1 then fact
    else let r = Float.to_int (Float.floor (Float.sqrt (Int.to_float n))) in let s = r * r - n in
    factorisation_fermat n r s fact

(* Affiche le produit d'une liste d'entier *)
let rec affiche_facteurs fact = 
    match fact with
    |[] -> ()
    |h::[] -> print_int h
    |h::t -> print_int h; print_string " x "; affiche_facteurs t