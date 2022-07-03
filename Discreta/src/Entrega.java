import java.lang.AssertionError;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.Set;

/*
 * Aquesta entrega consisteix en implementar tots els mètodes annotats amb el comentari "// TO DO".
 *
 * Cada tema té el mateix pes, i l'avaluació consistirà en:
 *
 * - Principalment, el correcte funcionament de cada mètode (provant amb diferents entrades). Teniu
 *   alguns exemples al mètode `main`.
 *
 * - La neteja del codi (pensau-ho com faltes d'ortografia). L'estàndar que heu de seguir és la guia
 *   d'estil de Google per Java: https://google.github.io/styleguide/javaguide.html . No és
 *   necessari seguir-la estrictament, però ens basarem en ella per jutjar si qualcuna se'n desvia
 *   molt.
 *
 * Per com està plantejada aquesta entrega, no necessitau (ni podeu) utilitzar cap `import`
 * addicional, ni mètodes de classes que no estiguin ja importades. El que sí podeu fer és definir
 * tots els mètodes addicionals que volgueu (de manera ordenada i dins el tema que pertoqui).
 *
 * Podeu fer aquesta entrega en grups de com a màxim 3 persones, i necessitareu com a minim Java 8.
 * Per entregar, posau a continuació els vostres noms i entregau únicament aquest fitxer.
 * - Nom 1: Nicolás Sanz Tuñón
 * - Nom 2: Valentino Coppola Ferrari
 * - Nom 3: Constantino Byelov Serdiuk
 *
 * L'entrega es farà a través d'una tasca a l'Aula Digital abans de la data que se us hagui
 * comunicat i vos recomanam que treballeu amb un fork d'aquest repositori per seguir més fàcilment
 * les actualitzacions amb enunciats nous. Si no podeu visualitzar bé algun enunciat, assegurau-vos
 * que el vostre editor de texte estigui configurat amb codificació UTF-8.
 */
class Entrega {
    /*
     * Aquí teniu els exercicis del Tema 1 (Lògica).
     *
     * Els mètodes reben de paràmetre l'univers (representat com un array) i els
     * predicats adients
     * (per exemple, `Predicate<Integer> p`). Per avaluar aquest predicat, si `x` és
     * un element de
     * l'univers, podeu fer-ho com `p.test(x)`, té com resultat un booleà. Els
     * predicats de dues
     * variables són de tipus `BiPredicate<Integer, Integer>` i similarment
     * s'avaluen com
     * `p.test(x, y)`.
     *
     * En cada un d'aquests exercicis us demanam que donat l'univers i els predicats
     * retorneu `true`
     * o `false` segons si la proposició donada és certa (suposau que l'univers és
     * suficientment
     * petit com per utilitzar la força bruta)
     */
    static class Tema1 {

        /*
         * És cert que ∀x,y. P(x,y) -> Q(x) ^ R(y) ?
         * 
         */
        static boolean exercici1(int[] universe, BiPredicate<Integer, Integer> p, Predicate<Integer> q,
                Predicate<Integer> r) {

            for (int i = 0; i < universe.length; i++) { // recorremos x

                for (int j = 0; j < universe.length; j++) { // ahora recorremos la y.

                    // hacemos nuestra preposicion .
                    boolean prep = !p.test(universe[i], universe[j])
                            || (q.test(universe[i]) && r.test(universe[j]));
                    // si prep sale falso hacemos return de false.
                    if (!prep) {
                        System.out.println("Ejercicio 1: false");
                        return false;
                    }
                }
            }
            // en otro caso devolvemos true.
            System.out.println("Ejercicio 1: true");
            return true;
        }

        /*
         * És cert que ∃!x. ∀y. Q(y) -> P(x) ?
         */
        static boolean exercici2(int[] universe, Predicate<Integer> p, Predicate<Integer> q) {
            // declaramos un contador
            int contador = 0;

            for (int y = 0; y < universe.length; y++) {// para todos los valores de Y miramos todos los valores de X.
                contador = 0; // ponemos el contador a 0.
                for (int x = 0; x < universe.length; x++) {// recorremos todas las x.

                    if (!q.test(universe[y]) || p.test(universe[x])) {// si se cumple incrementamos el contador que
                                                                      // significa que el valor de la X cumple la
                                                                      // condición.
                        contador++;
                    }
                }
                // Si el contador es diferente de 1 eso siginifca que no existe una unica X para
                // toda Y, por lo tanto devolvemos False.
                if (contador != 1) {
                    System.out.println("Ejercicio 2: false");
                    return false;
                }
            }
            // Si tras recorrer todos los valores no hemos devuelto false, eso significa que
            // se cumple la preposición y devolvemos True.
            System.out.println("Ejercicio 2: true");
            return true;
        }

        /*
         * És cert que ¬(∃x. ∀y. y ⊆ x) ?
         *
         * Observau que els membres de l'univers són arrays, tractau-los com conjunts i
         * podeu suposar que cada un d'ells està ordenat de menor a major.
         */
        static boolean exercici3(int[][] universe) {
            // Declaramos variables
            int length = 0;
            int[] arr = null;
            boolean res = false;
            // Recorremos todo el universo de X y actulizamos el lenght
            for (int[] x : universe) {
                if (x.length > length) {
                    length = x.length;
                    arr = x;
                }
            }
            // Recorremos todo el rango de numeros del universo
            for (int[] x : universe) {
                for (int j = 0; j < x.length; j++) {
                    // ponemos a false
                    res = false;
                    for (int i = 0; i < length; i++) {
                        // si se cumple ponemos el resultado a true y salimoa del for
                        if ((arr[i] == x[j]) || (x.length == 0)) {
                            res = true;
                            break;
                        }
                    }
                    // Si el resultado es falso devolvemos True.
                    if (res == false) {
                        System.out.println("Ejercicio 3: true");
                        return true;
                    }
                }
            }
            // En otro caso devolvemos False.
            System.out.println("Ejercicio 3: false");
            return false;
        }

        /*
         * És cert que ∀x. ∃!y. x·y ≡ 1 (mod n) ?
         */
        static boolean exercici4(int[] universe, int n) {

            // Declaramos variables
            int contador = 0;
            boolean res = false;

            // miramos todos los valores
            for (int x = 0; x < universe.length; x++) {
                for (int y = 0; y < universe.length; y++) {
                    // ponemos el contador a 0
                    contador = 0;
                    // a congruent b mod n <=> a - b divisible entre n
                    // a és divisible entre n si el resto es == 0
                    // Si da 0 ponemos a true el resultado y aumentamos el contador
                    if (((universe[x] * universe[y]) - 1) % n == 0) {
                        res = true;
                        contador += 1;
                    } else {
                        res = false;
                    }
                }
            }

            // si el contador es diferente de 1 devolvemos false
            if (contador != 1) {
                res = false;
            }
            // en caso contrario devolvemos true.
            System.out.println("Ejercicio 4: " + res);
            return res;
        }

        /*
         * Aquí teniu alguns exemples i proves relacionades amb aquests exercicis (vegeu
         * 'main')
         */
        static void tests() {
            // Exercici 1
            // ∀x,y. P(x,y) -> Q(x) ^ R(y)

            assertThat(
                    exercici1(
                            new int[] { 2, 3, 5, 6 },
                            (x, y) -> x * y <= 4,
                            x -> x <= 3,
                            x -> x <= 3));

            assertThat(
                    !exercici1(
                            new int[] { -2, -1, 0, 1, 2, 3 },
                            (x, y) -> x * y >= 0,
                            x -> x >= 0,
                            x -> x >= 0));

            // Exercici 2
            // ∃!x. ∀y. Q(y) -> P(x) ?

            assertThat(
                    exercici2(
                            new int[] { -1, 1, 2, 3, 4 },
                            x -> x < 0,
                            x -> true));

            assertThat(
                    !exercici2(
                            new int[] { 1, 2, 3, 4, 5, 6 },
                            x -> x % 2 == 0, // x és múltiple de 2
                            x -> x % 4 == 0 // x és múltiple de 4
                    ));

            // Exercici 3
            // ¬(∃x. ∀y. y ⊆ x) ?

            assertThat(
                    exercici3(new int[][] { { 1, 2 }, { 0, 3 }, { 1, 2, 3 }, {} }));

            assertThat(
                    !exercici3(new int[][] { { 1, 2 }, { 0, 3 }, { 1, 2, 3 }, {}, { 0, 1, 2, 3 } }));

            // Exercici 4
            // És cert que ∀x. ∃!y. x·y ≡ 1 (mod n) ?

            assertThat(
                    exercici4(
                            new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
                            11));

            assertThat(
                    !exercici4(
                            new int[] { 0, 5, 7 },
                            13));
        }
    }

    /*
     * Aquí teniu els exercicis del Tema 2 (Conjunts).
     *
     * De la mateixa manera que al Tema 1, per senzillesa tractarem els conjunts com
     * arrays (sense
     * elements repetits). Per tant, un conjunt de conjunts d'enters tendrà tipus
     * int[][].
     *
     * Les relacions també les representarem com arrays de dues dimensions, on la
     * segona dimensió
     * només té dos elements. Per exemple
     * int[][] rel = {{0,0}, {1,1}, {0,1}, {2,2}};
     * i també donarem el conjunt on està definida, per exemple
     * int[] a = {0,1,2};
     *
     * Les funcions f : A -> B (on A i B son subconjunts dels enters) les
     * representam donant int[] a,
     * int[] b, i un objecte de tipus Function<Integer, Integer> que podeu avaluar
     * com f.apply(x) (on
     * x és un enter d'a i el resultat f.apply(x) és un enter de b).
     */
    static class Tema2 {
        /*
         * És `p` una partició d'`a`?
         *
         * `p` és un array de conjunts, haureu de comprovar que siguin elements d'`a`.
         * Podeu suposar que
         * tant `a` com cada un dels elements de `p` està ordenat de menor a major.
         */
        static boolean exercici1(int[] a, int[][] p) {
            for (int n = 0; n < a.length; n++) {// recorremos todos los valores de a
                // declaramos y asignamos variables
                int uno = 0;
                int econj = a[n];
                // recorremos los valores de p.
                for (int i = 0; i < p.length; i++) {
                    for (int j = 0; j < p[i].length; j++) {
                        int epart = p[i][j];// asiganmos p[i][j] a la variable de elementos de particion
                        if (econj == epart) {// si el elemento del conjunto y el elemento de la particion son iguales
                                             // añadimos uno a la variable de unico.
                            uno++;
                            if (uno > 1) {// Si un numero aparece en más de un subconjunto,no puede ser una partición
                                          // por lo tanto devolvemos false.
                                System.out.println("Ejercicio 1: false");
                                return false;
                            }
                        }

                    }
                }
                if (uno == 0) {// Si no encontramos un elemento del conjunto en los subconjuntos no puede ser
                               // una partición
                               // por lo tanto devolvemos false.
                    System.out.println("Ejercicio 1: false");
                    return false;
                }

            }
            // En caso contratio devolvemos true
            System.out.println("Ejercicio 1: true");
            return true;
        }

        /*
         * Comprovau si la relació `rel` definida sobre `a` és un ordre parcial i que
         * `x` n'és el mínim.
         *
         * Podeu soposar que `x` pertany a `a` i que `a` està ordenat de menor a major.
         */
        static boolean exercici2(int[] a, int[][] rel, int x) {

            // declaramos todos los atributos.
            boolean isRefl, isAntis, isTrans, isMin, res, isRel;
            int contador = 0;

            // Miramos si es reflexiva
            for (int ax : a) {
                for (int[] relx : rel) {
                    if (ax == relx[0] && ax == relx[1]) {
                        contador++;
                        break;
                    }
                }
            }

            // asigamos el valor
            isRefl = (contador == a.length);

            // si no es reflexiva devolvemos false
            if (!isRefl) {
                res = false;
                System.out.println("Ejercicio 2: false, no reflexiva");
                return res;
            }
            contador = 0;
            int contador1 = 0;

            // Miramos si es transitiva
            for (int[] relx : rel) {
                int xx = relx[0], yy = relx[1];
                for (int[] rely : rel) {
                    if (rely[0] == yy) {
                        int zz = rely[1];
                        contador++;
                        for (int[] relz : rel) {
                            if (relz[0] == xx && relz[1] == zz) {
                                contador1++;
                                break;
                            }
                        }
                    }

                }

            }

            // asignamos el valor
            isTrans = (contador1 == contador);

            // si no es transitiva devolvemos false.
            if (!isTrans) {
                res = false;
                System.out.println("Ejercicio 2: false, no transitiva");
                return res;
            }

            for (int[] relx : rel) {
                int xx = relx[0], yy = relx[1];
                for (int[] rely : rel) {
                    if ((rely[0] == yy && rely[1] == xx) && yy != xx) {
                        res = false;

                        return res;
                    }
                }
            }

            isAntis = true;
            int min = x;
            contador = 0;

            // miramos si es minimo
            for (int[] relx : rel) {
                if (relx[0] == min || relx[1] == min) {
                    contador++;
                }
            }

            // asiganmso el resultado.
            isMin = (contador == a.length);

            // si no lo es devolvemos false.
            if (!isMin) {
                res = false;
                System.out.println("Ejercicio 2: false, no minimo");
                return res;
            }

            for (int[] relx : rel) {
                if (!((relx[0] >= a[0] && relx[0] <= a[a.length - 1])
                        && (relx[1] >= a[0] && relx[1] <= a[a.length - 1]))) {
                    res = false;
                    System.out.println("Ejercicio 2: false");
                    return res;
                }
            }

            isRel = true;

            // devolvemos el resultado que hemos sacado, si es de orden parcial y minimo,
            // true, si no false.
            if (isRefl && isTrans && isAntis && isMin && isRel) {
                res = true;
                System.out.println("Ejercicio 2: ture, orden paricial");
                return res;
            } else {
                res = false;
                System.out.println("Ejercicio 2: false");
                return false;
            }
        }

        /*
         * Suposau que `f` és una funció amb domini `dom` i codomini `codom`. Trobau
         * l'antiimatge de `y` (ordenau el resultat de menor a major, podeu utilitzar
         * `Arrays.sort()`).Podeu suposarque `y` pertany a `codom` i que tant `dom` com
         * `codom` també estàn ordenats de menor a major.
         */
        static int[] exercici3(int[] dom, int[] codom, Function<Integer, Integer> f, int y) {
            System.out.println("Ejercicio 3: ");
            // declaramos un contador
            int contador = 0;
            // recoremos todo el dominio
            for (int i : dom) {
                // si "y" es igual a a la aplicación de la función sobre "i" aumentamos
                // contador.
                if (y == f.apply(i)) {
                    contador++;
                }
            }

            // declaramos un array respuesta donde pasamos el parametro contador por tamaño.
            int[] res = new int[contador];

            // si el contador es igual a 0 devolvemos el array vacio.
            if (contador == 0) {
                return res;
            } else {// si no lo está, ponemos los valores de "i" en el array de respuestas.
                int contador2 = 0;
                for (int i : dom) {
                    if (y == f.apply(i)) {
                        res[contador2] = i;
                    }
                }
            }

            // ordenamos el array
            Arrays.sort(res);

            // devolvemos el resultado
            for (int g = 0; g < res.length; g++) {
                System.out.print(res[g] + " ");
            }
            System.out.println();

            // hacemos return del resultado
            return res;
        }

        /*
         * Suposau que `f` és una funció amb domini `dom` i codomini `codom`. Retornau:
         * - 3 si `f` és bijectiva
         * - 2 si `f` només és exhaustiva
         * - 1 si `f` només és injectiva
         * - 0 en qualsevol altre cas
         *
         * Podeu suposar que `dom` i `codom` estàn ordenats de menor a major. Per
         * comoditat, podeu
         * utilitzar les constants definides a continuació:
         */
        static final int NOTHING_SPECIAL = 0;
        static final int INJECTIVE = 1;
        static final int SURJECTIVE = 2;
        static final int BIJECTIVE = INJECTIVE + SURJECTIVE;

        static int exercici4(int[] dom, int[] codom, Function<Integer, Integer> f) {
            boolean Inyectiva = true; // indica si la función no es inyectiva.
            boolean Exhaustiva = true; // indica si la funcion no es exhaustiva.
            int aux = 0, aux2 = 0; // declaramos dos enteros auxiliares.

            // mirar si es inyectiva
            for (int i = 0; i < dom.length; i++) {
                int x = dom[i]; // igualamos a x el valor del dominio en función de la i
                for (int j = 0; j < dom.length; j++) {
                    int y = dom[j];
                    if (i != j) {// si la i no es igual a la j, miramos si la aplicación de la funcion sobre "x"
                                 // e "y" es igual, si lo es no es inyectiva y salimos del bucle.
                        if (f.apply(x) == f.apply(y)) {
                            Inyectiva = false;
                            break;
                        }
                    }
                }
            }

            // mirar si es exhaustiva
            for (int i = 0; i < codom.length; i++) {
                int y = codom[i]; // igualamos a "y" el codominio en funcion de la "i"
                for (int j = 0; j < dom.length; j++) {
                    int x = dom[j];
                    if (f.apply(x) == y) {// si la aplicación de la función es igual a "y" aumentamos el valor de la
                                          // variable auxiliar.
                        aux++;
                    }

                }
                if (aux != 0) {// si la variable auxiliar no es 0 aumentamos el valor de la segunda auxiliar
                    aux2++;
                }
                aux = 0; // y resetamos auxiliar a 0.

            }
            // si la segunda variable auxiliar no es igual al codominio devolvemos que no es
            // exhaustiva.
            if (aux2 != codom.length) {
                Exhaustiva = false;
            }

            // ahora miramos los valores que tenemos y damos el resultado
            if (Inyectiva && Exhaustiva) {
                System.out.println("Ejercicio 4: " + BIJECTIVE);
                return BIJECTIVE;
            } else if (Inyectiva && !Exhaustiva) {
                System.out.println("Ejercicio 4: " + INJECTIVE);
                return INJECTIVE;
            } else if (!Inyectiva && Exhaustiva) {
                System.out.println("Ejercicio 4: " + SURJECTIVE);
                return SURJECTIVE;
            } else if (!Inyectiva && !Exhaustiva) {
                System.out.println("Ejercicio 4: " + NOTHING_SPECIAL);
                return NOTHING_SPECIAL;
            }

            return -1;
        }

        /*
         * Aquí teniu alguns exemples i proves relacionades amb aquests exercicis (vegeu
         * `main`)
         */
        static void tests() {
            // Exercici 1
            // `p` és una partició d'`a`?

            assertThat(
                    exercici1(
                            new int[] { 1, 2, 3, 4, 5 },
                            new int[][] { { 1, 2 }, { 3, 5 }, { 4 } }));

            assertThat(
                    !exercici1(
                            new int[] { 1, 2, 3, 4, 5 },
                            new int[][] { { 1, 2 }, { 5 }, { 1, 4 } }));

            // Exercici 2
            // és `rel` definida sobre `a` d'ordre parcial i `x` n'és el mínim?

            ArrayList<int[]> divisibility = new ArrayList<int[]>();

            for (int i = 1; i < 8; i++) {
                for (int j = 1; j <= i; j++) {
                    if (i % j == 0) {
                        // i és múltiple de j, és a dir, j|i
                        divisibility.add(new int[] { j, i });
                    }
                }
            }

            assertThat(
                    exercici2(
                            new int[] { 1, 2, 3, 4, 5, 6, 7 },
                            divisibility.toArray(new int[][] {}),
                            1));

            assertThat(
                    !exercici2(
                            new int[] { 1, 2, 3 },
                            new int[][] { { 1, 1 }, { 2, 2 }, { 3, 3 }, { 1, 2 },
                                    { 2, 3 } },
                            1));

            assertThat(
                    !exercici2(
                            new int[] { 1, 2, 3, 4, 5, 6, 7 },
                            divisibility.toArray(new int[][] {}),
                            2));

            // Exercici 3
            // calcular l'antiimatge de `y`

            assertThat(
                    Arrays.equals(
                            new int[] { 0, 2 },
                            exercici3(
                                    new int[] { 0, 1, 2, 3 },
                                    new int[] { 0, 1 },
                                    x -> x % 2, // residu de dividir entre 2
                                    0)));

            assertThat(
                    Arrays.equals(
                            new int[] {},
                            exercici3(
                                    new int[] { 0, 1, 2, 3 },
                                    new int[] { 0, 1, 2, 3, 4 },
                                    x -> x + 1,
                                    0)));

            // Exercici 4
            // classificar la funció en res/injectiva/exhaustiva/bijectiva

            assertThat(
                    exercici4(
                            new int[] { 0, 1, 2, 3 },
                            new int[] { 0, 1, 2, 3 },
                            x -> (x + 1) % 4) == BIJECTIVE);

            assertThat(
                    exercici4(
                            new int[] { 0, 1, 2, 3 },
                            new int[] { 0, 1, 2, 3, 4 },
                            x -> x + 1) == INJECTIVE);

            assertThat(
                    exercici4(
                            new int[] { 0, 1, 2, 3 },
                            new int[] { 0, 1 },
                            x -> x / 2) == SURJECTIVE);

            assertThat(
                    exercici4(
                            new int[] { 0, 1, 2, 3 },
                            new int[] { 0, 1, 2, 3 },
                            x -> x <= 1 ? x + 1 : x - 1) == NOTHING_SPECIAL);

        }
    }

    /*
     * Aquí teniu els exercicis del Tema 3 (Aritmètica).
     *
     */
    static class Tema3 {
        /*
         * Donat `a`, `b` retornau el màxim comú divisor entre `a` i `b`.
         *
         * Podeu suposar que `a` i `b` són positius.
         */
        static int exercici1(int a, int b) {
            int res = 0;
            int aux = -1;
            int major, menor;

            // determinamos cual es el mayor de los dos numeros pasados por parametro es
            // mayor.
            if (a > b) {
                major = a;
                menor = b;
            } else {
                major = b;
                menor = a;
            }

            // mientras auxiliar no sea 0 seguimos en el bucle donde sacamos el menor
            // numero.
            while (aux != 0) {
                aux = major % menor;
                major = menor;

                if (aux == 0) {// si el modulo entre el mayor y el menor da cero igualamos el resultado al
                               // menor.
                    res = menor;
                }

                menor = aux;
            }

            System.out.println("Ejercicio 1: " + res);

            // devolvemos el resultado
            return res;
        }

        /*
         * Es cert que `a``x` + `b``y` = `c` té solució?.
         *
         * Podeu suposar que `a`, `b` i `c` són positius.
         */
        static boolean exercici2(int a, int b, int c) {
            int res = 0;
            int aux = -1;
            int major, menor;
            boolean sol = false;

            // miramos cual es el numero mayor de los dos pasados por parametro
            if (a > b) {
                major = a;
                menor = b;
            } else {
                major = b;
                menor = a;
            }

            // mientras auxiliar no sea 0 seguimos en el bucle donde sacamos el menor
            // numero.
            while (aux != 0) {
                aux = major % menor;
                major = menor;

                if (aux == 0) {// si el modulo entre el mayor y el menor da cero igualamos el resultado al
                               // menor.
                    res = menor;
                }

                menor = aux;
            }

            // si el modulo entre el parametro "c" y el "resultado", da una división exacta
            // tiene solución.
            if ((c % res) == 0) {
                sol = true;
            }

            System.out.println("Ejercicio 2: " + sol);

            // devolvemos el resultado
            return sol;
        }

        /*
         * Quin es l'invers de `a` mòdul `n`?
         *
         * Retornau l'invers sempre entre 1 i `n-1`, en cas que no existeixi retornau -1
         */
        static int exercici3(int a, int n) {
            System.out.print("Ejercicio 3: ");
            // bucle donde recorremos todos los valores de "a" y "n" para ver por cual es
            // divisible, cuando lo encontremos lo devolvemos mediante un print
            for (int i = 1; i < n; i++) {
                if ((a * i) % n == 1) {// si "a" por el "indice" del for modulo "n" es igual a uno hemos encontrado el
                                       // inverso de"a modulo n"
                    // printeamos el numero y hacemos return del mismo.
                    System.out.println(i);
                    return i;
                }
            }
            // si no lo hemos encontrado se devuelve un "-1".
            System.out.println("-1");
            return -1;
        }

        /*
         * Aquí teniu alguns exemples i proves relacionades amb aquests exercicis (vegeu
         * `main`)
         */
        static void tests() {
            // Exercici 1
            // `mcd(a,b)`

            assertThat(
                    exercici1(2, 4) == 2);

            assertThat(
                    exercici1(1236, 984) == 12);

            // Exercici 2
            // `a``x` + `b``y` = `c` té solució?

            assertThat(
                    exercici2(4, 2, 2));
            assertThat(
                    !exercici2(6, 2, 1));
            // Exercici 3
            // invers de `a` mòdul `n`
            assertThat(exercici3(2, 5) == 3);
            assertThat(exercici3(2, 6) == -1);
        }
    }

    static class Tema4 {
        /*
         * Donada una matriu d'adjacencia `A` d'un graf no dirigit, retornau l'ordre i
         * la mida del graf.
         */
        static int[] exercici1(int[][] A) {
            System.out.print("Ejercicio 1: ");
            // declaramos un contador a 0.
            int contador = 0;
            // recorremos todos los nodos y miramos que orden tiene aumentando el contador
            // si en la posición "I", "J" hay una conexión.
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < A[i].length; j++) {
                    if (A[i][j] == 1) {// si en la posicion i,j del array es igual a 1 significa que hay una conexión
                                       // por ende aumentamos el contador.
                        contador++;
                    }
                }
            }
            // Devolvemos el orden y la mida del grafo
            System.out.println(A.length + ", " + (contador / 2));
            return new int[] { A.length, contador / 2 };
        }

        /*
         * Donada una matriu d'adjacencia `A` d'un graf no dirigit, digau si el graf es
         * eulerià.
         */
        static boolean exercici2(int[][] A) {
            System.out.print("Ejercicio 2: ");
            // declaramos un contador a 0.
            int contador = 0;
            // recorremos todos los nodos y miramos que orden tiene aumentando el contador
            // si en la posición I, J hay una conexión.
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < A[i].length; j++) {
                    if (A[i][j] == 1) {// si en la posicion i,j del array es igual a 1 significa que hay una conexión
                                       // por ende aumentamos el contador.
                        contador++;
                    }
                }

                // si el contador a la hora de hacer el modulo con 2 no da 0, significa que el
                // numero de conexiones no es par y por tanto no
                // es euleriano y devolvemos false.
                if (contador % 2 != 0) {
                    System.out.println("F");
                    return false;
                }
            }
            // si se ha recorrido toda la matriz y no ha saltado el error, deolvemos true
            System.out.println("T");
            return true;
        }

        /*
         * Donat `n` el número de fulles d'un arbre arrelat i `d` el nombre de fills
         * dels nodes interiors, retornau el nombre total de vèrtexos de l'arbre.
         */
        static int exercici3(int n, int d) {
            System.out.print("Ejercicio 3: ");
            // declaramos el entero de numero de nodos, y lo inicializamos a 0.
            int numNodos = 0;
            // declaramos un entero del resto y le asignamos el modulo de "n" y "d".
            int resto = n % d;
            // declaramos un contador y lo inicializamos a 1.
            int contador = 1;
            // declramos otro contador y lo inicializamos a 0.
            int countador2 = 0;
            // declaramos otro contador y lo inicializamos a 0.
            int countador3 = 0;

            // si el resto es igual a 0 igualamos el numero de nodos a el numero de hojas
            // más el numero de hojas partido el numero de hijos.
            if (resto == 0) {
                numNodos = n + (n / d);
            } else {// en caso contrario miramos esto
                if (d == 2) {
                    numNodos = n + (n - 1); // si d=2 queda un elemento solo y se pueden hacer n-1 parejas
                } else {// en caso contario
                    while (contador < n) { // el numero de itereaciones seria el numero de nodos que necesitamos
                        countador3++;
                        contador = (d * countador3) - 1; // vamos recorriendo los d nodos -1 cada vez, haciendo asi
                                                         // grupos.
                        countador2++;// aumentados contador
                    }
                    // el numero de nodos va ha hacer el contador más los nodos hoja del arbol.
                    numNodos = countador2 + n;
                }
            }

            // devolvemos el numero de nodos.
            System.out.println(numNodos);
            return numNodos;
        }

        /*
         * Donada una matriu d'adjacencia `A` d'un graf connex no dirigit, digau si el
         * graf conté algún cicle.
         */
        static boolean exercici4(int[][] A) {
            System.out.print("Ejercicio 4: ");
            // declaramos un contador y lo inicializamos a 0.
            int contador = 0;

            // recorremos todos los nodos y miramos que orden tiene aumentando el contador
            // si en la posición I, J hay una conexión.
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < A[i].length; j++) {
                    if (A[i][j] == 1) {// si en la posicion i,j del array es igual a 1 significa que hay una conexión
                                       // por ende aumentamos el contador.
                        contador++;
                    }
                }
            }
            // si el contador partido 2 es igual al tamaño de A significa que hay ciclo, si
            // no no es ciclo.
            System.out.println(contador / 2 == A.length);
            return contador / 2 == A.length;

        }

        /*
         * Aquí teniu alguns exemples i proves relacionades amb aquests exercicis (vegeu
         * `main`)
         */
        static void tests() {
            // Exercici 1
            // `ordre i mida`

            assertThat(
                    Arrays.equals(exercici1(new int[][] { { 0, 1, 0 }, { 1, 0, 1 }, { 0, 1, 0 } }),
                            new int[] { 3, 2 }));

            assertThat(
                    Arrays.equals(
                            exercici1(new int[][] { { 0, 1, 0, 1 }, { 1, 0, 1, 1 }, { 0, 1, 0, 1 }, { 1, 1, 1, 0 } }),
                            new int[] { 4, 5 }));

            // Exercici 2
            // `Es eulerià?`

            assertThat(
                    exercici2(new int[][] { { 0, 1, 1 }, { 1, 0, 1 }, { 1, 1, 0 } }));
            assertThat(
                    !exercici2(new int[][] { { 0, 1, 0 }, { 1, 0, 1 }, { 0, 1, 0 } }));

            // Exercici 3
            // `Quants de nodes té l'arbre?`

            assertThat(exercici3(5, 2) == 9);
            assertThat(exercici3(7, 3) == 10);

            // Exercici 4
            // `Conté algún cicle?`
            assertThat(
                    exercici4(new int[][] { { 0, 1, 1 }, { 1, 0, 1 }, { 1, 1, 0 } }));
            assertThat(
                    !exercici4(new int[][] { { 0, 1, 0 }, { 1, 0, 1 }, { 0, 1, 0 } }));

        }
    }

    /*
     * Aquest mètode `main` conté alguns exemples de paràmetres i dels resultats que
     * haurien de donar
     * els exercicis. Podeu utilitzar-los de guia i també en podeu afegir d'altres
     * (no els tendrem en
     * compte, però és molt recomanable).
     *
     * Podeu aprofitar el mètode `assertThat` per comprovar fàcilment que un valor
     * sigui `true`.
     */
    public static void main(String[] args) {
        System.out.println("TEMA 1");
        Tema1.tests();
        System.out.println("\n\n");

        System.out.println("TEMA 2");
        Tema2.tests();
        System.out.println("\n\n");

        System.out.println("TEMA 3");
        Tema3.tests();
        System.out.println("\n\n");

        System.out.println("TEMA 4");
        Tema4.tests();
        System.out.println("\n\n");
    }

    static void assertThat(boolean b) {
        if (!b)
            throw new AssertionError();
    }
}

// vim: set textwidth=100 shiftwidth=2 expandtab :
