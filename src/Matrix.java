/*
    Classe Matrix, dedicada a realitzar operacions amb matrius
 */

public class Matrix {

    /*
        Variable on s'emmagatzemarà el resultat final a retornar, sense inicialitzar
     */
    static double[][] result;

    /*
        Funció trace. És la encarregada de realitzar la traça de la matriu,
        suma els components de la diagonal principal d'una matriu
     */
    static double trace(double[][] mat) {

        //Declaram el que serà el resultat final de l'operació
        int suma = 0;

        //Recorrem la matriu diagonalment i el vam sumant
        for (int i = 0; i < mat.length; i++) {
            suma += mat[i][i];
        }
        //Retornam el resultat
        return suma;
    }

    /*
        Funció add. És la encarregada de realitzar la suma entre dues matrius, per a que això sigui possible,
        les dues matrius han de tenir la mateixa dimensió.
     */
    static double[][] add(double[][] mat1, double[][] mat2) {

        //Comprovam que les dues matrius tenguin la mateixa dimensió
        if (!(mat1.length == mat2.length && mat1[0].length == mat2[0].length)) {
            System.out.println("Las dimensiones tienen que ser iguales");
            return null;
        }

        /*
            Inicialitzam la variable result, la qual tindrà la mateixa dimensió que els dos components
            que ens passen.
         */
        result = new double[mat1.length][mat1[0].length];

        /*
            Recorrem les matrius, i afegim el valor sumat a la nova matriu
         */
        for (int i = 0; i < mat1.length; i++) {

            for (int j = 0; j < mat1[0].length; j++) {
                result[i][j] = mat1[i][j] + mat2[i][j];
            }

        }


        //Retornam el resultat final

        return result;
    }

    /*
        Funció mult. És la encarregada de fer la multiplicació entre dos matrius compatibles.
     */
    static double[][] mult(double[][] mat1, double[][] mat2) {

        /*
            Feim la comprovació de la compatibilitat de les dues matrius. Per a que dues matrius
            siguin compatibles, el tamany horitzontal de la primera matriu, ha de ser igual
            al tamany vertical de la segona.
         */

        if (mat1[0].length != mat2.length) {
            System.out.println("Aquestes matrius no són compatibles");
            return null;
        }

        /*
            Declaram el tamany de la matriu resultant. El qual serà una combinació dels costats més grans
            de les dues matrius, per tant, feim les comprovacions adients.
         */

        if (mat1.length > mat2.length) {
            if (mat1[0].length > mat2[0].length) {
                result = new double[mat1.length][mat1[0].length];
            } else {
                result = new double[mat1.length][mat2[0].length];
            }
        } else {
            if (mat1[0].length > mat2[0].length) {
                result = new double[mat2.length][mat1[0].length];
            } else {
                result = new double[mat2.length][mat2[0].length];
            }
        }


        /*
            Una vegada hem definit el resultat final, hem de fer els càlculs corresponents.
            Per tant, recorrem la matriu del resultat, i li anirem afegint el resultat de les operacions
            entre les altres dos matrius
         */
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                for (int k = 0; k < mat2[0].length; k++) {

                    result[i][j] += (mat1[i][k] * mat2[k][j]);


                }
            }
        }

        //Retornam el resultat obtingut
        return result;
    }

    /*
        Aquesta funció és la encarregada de realitzar potències de matrius.
     */
    static double[][] power(double[][] mat, int p) {

        /*
            La matriu resultant comencarà amb el mateix valor que ens passen, i s'anirà
            multiplicant per si mateixa
         */
        result = mat;

        /*
            En el cas de que volguem elevar la matriu a 0, qualsevol nombre elevat a 0 és 1,
            per tant la matriu resultant haurà de tenir el determinant com a valor 1.
            Una matriu que té valor 1, a totes les posicions de la seva diagonal, tindrà un 1, i a la
            resta de posicions, tindrà un 0
        */

        if (p == 0) {
            for (int i = 0; i < mat.length; i++) {
                for (int j = 0; j < mat[0].length; j++) {
                    result[i][j] = 0;
                }
            }
            for (int i = 0; i < mat.length; i++) {
                result[i][i] = 1;
            }

            return result;
        }

        /*
            En cas de que ens passin un nombre negatiu, retornarem un valor null
         */
        if (p < 0) {
            System.out.println("El nombre ha ser més gran o igual a 0");
            return null;
        }

        /*
            Per fer l'operació, multiplicarem la matriu amb el seu valor original, tantes vegades com ens demanin

         */
        for (int i = 1; i < p; i++) {

            result = mult(result, mat);
        }

        //Retornam la matriu resultant
        return result;
    }

    /*
        Aquesta és la funció encarregada de fer la divisió entre dues matrius.
        El procediment per realitzar aquesta funció, és fer la multiplicació de la primera matriu,
        per la matriu inversa de la segona.
     */
    static double[][] div(double[][] mat1, double[][] mat2) {

        /*
            Feim la comprovació de que la inversa de la segona matriu sigui 0, si és així, no podrem
            continuar operant, ja que no podem dividir entre 0
         */
        if (invert(mat2) == null) {
            System.out.println("No podem dividir entre 0");
            return null;
        } else {
            return mult(mat1, invert(mat2));
        }

    }

    /*
        Aquesta funció obté una matriu a partir d'un altre matriu,
        emprant les coordenades inicials i finals
     */
    static double[][] submatrix(double[][] mat, int x1, int y1, int x2, int y2) {

        /*
            Definim les dimensions de la matriu resultant, que seràn el resultat de fer la diferència entre
            les coordenades que ens passen
         */

        int difX = x2 - x1 + 1;
        int difY = y2 - y1 + 1;

        result = new double[difY][difX];

        /*
            En aquest bucle recorrerem dues matrius a la vegada, per tant, a cada bucle inicialitzam dues variables,
            una per a cada matriu, la qual anirem recorrent cada una al punt que li toca
         */
        for (int i = y1, y = 0; i <= y2; i++, y++) {
            for (int j = x1, x = 0; j <= x2; j++, x++) {
                result[y][x] = mat[i][j];
            }
        }

        //Retornam la matriu resultant
        return result;
    }

    /*
        Aquesta funció és la encarregada de multiplicar una matriu per un nombre enter.
        El procediment d'aquesta funció és multiplicar cada component de la matriu per el
        nombre enter que ens passin.
     */
    static double[][] mult(double[][] mat, double n) {

        //La matriu tindrà la mateixa mesura que la matriu original, per tant, la definim
        result = new double[mat.length][mat[0].length];

        /*
            Recorrem la matriu, i cada component de la mateixa, el multiplicam pel nombre que ens passen
         */
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                result[i][j] = mat[i][j] * n;
            }
        }
        //Retornam la matriu resultant
        return result;
    }

    /*
        Aquesta funció té l'objectiu d'obtenir la matriu inversa d'una matriu.

        El procediment per obtenir la matriu inversa ve seguida de quatre passes :

        1. Calcular el determinant de la matriu original.
        2. Treure la matriu de cofactors de la matriu.
        3. Fer la transposició de la matriu de cofactors.
        4. Dividir cada component de la matriu transposada pel determinant de la matriu inicial

     */
    static double[][] invert(double[][] mat) {

        //Calculam el determinant de la matriu inicial
        double det = determinant(mat);

        //Si el determinant de la matriu és 0, no té inversa, per tant retornam null
        if (det == 0) {
            System.out.println("Aquesta matriu no té matriu inversa");
            return null;
        }

        //Treim la matriu de cofactors
        double[][] res = getCofactor(mat);

        //Transposam la matriu
        res = transpose(res);

        //Dividim cada element de la matriu pel determinant de la matriu inicial
        for (int i = 0; i < res[0].length; i++) {
            for (int j = 0; j < res.length; j++) {
                res[j][i] /= det;
            }
        }

        //Retornam el valor final
        return res;
    }

    /*
        Aquesta funció és la encarregada d'obtenir la matriu de cofactors d'una matriu
     */
    public static double[][] getCofactor(double[][] mat) {

        //Començam per definir la matriu que retornarem, la qual tindrà les dimensions de la que ens donen
        double[][] cof = new double[mat.length][mat[0].length];

        //Aquesta matriu l'haurem de recòrrer
        for (int i = 0; i < mat[0].length; i++) {
            for (int j = 0; j < mat.length; j++) {

                /*
                    Per assignar el valor a la posició de la matriu, haurem de fer una cridada a
                    getMinor, per per el càlcul corresponent del determinant que li toca. Aquest valor
                    l'introduïrem de manera positiva o negativa segons si és a una posició parell o senar
                 */
                double x = determinant(getMinor(mat, j, i));

                if ((i + j) % 2 == 0) {

                    cof[j][i] = x;
                } else {
                    cof[j][i] = -x;

                }

            }
        }

        //Una vegada obtingut la matriu resultant, la retornam
        return cof;
    }

    /*
       Aquesta funció té l'objectiu de treure una matriu més petita, a partir d'un altre més gran.
       La diferència entre aquesta i "submatrix", és que en aquesta funció, crearem una matriu "eliminant"
       una fila i una col·lumna
     */
    static double[][] getMinor(double[][] mat, int x, int y) {

        //La matriu resultant serà d'una dimensió més petit que l'original
        result = new double[mat[0].length - 1][mat.length - 1];

        //Cream un array per emmagatemar els valors de la matriu per després poder assignar-los al resultat
        double[] valoresArray = new double[result[0].length * result.length];

        /*
            Recorrem la matriu inicial, anirem afegint els valors a l'array sempre que no coincideixi amb les posicions
            que ens han passat, per això, cream a més un int contador que s'incrementarà sempre que s'afegueixi un valor
        */
        for (int i = 0, cont = 0; i < mat.length; i++) {

            for (int j = 0; j < mat[0].length; j++) {

                if (i == x || j == y) {

                } else {
                    valoresArray[cont] = mat[i][j];
                    cont++;
                }

            }

        }

        //Una vegada que tenim els valors a afegir, introduïm els valors de l'array a la matriu resultant
        for (int i = 0, cont = 0; i < result[0].length; i++) {
            for (int j = 0; j < result.length; j++, cont++) {
                result[i][j] = valoresArray[cont];
            }
        }

        //Retornam el resultat
        return result;
    }

    /*
        Aquesta funció és la encarregada de treure el determinant d'una matriu quadrada.
        Aquesta funció retorna un double, no una matriu.
     */
    static double determinant(double[][] mat) {

        /*
            Comprovam que la matriu sigui quadrada, en cas de que no ho sigui,
            tornam un valor Not A Number
         */
        if (mat.length != mat[0].length) {
            System.out.println("No es pot calcular, no és una matriu quadrada");
            return Double.NaN;
        }

        //Inicialitzam el resultat, que serà un double.
        double resultado = 0;

        //En cas de que la matriu nomès tengui un valor, retornarem el mateix valor
        if (mat.length == 1) {
            return mat[0][0];
        }

        /*
            En el cas de que la matriu tengui una dimensió de 2x2, cridarem a una altra funció creada
            especialment per aquest cas. Totes les matrius passaran per aquesta funció per a poder funcionar
            correctament.
         */
        if (mat.length == 2) {
            return det2x2(mat);
        }

        /*
            Farem ús de la recursivitat per a poder obtenir el determinant, ja que hem d'anar reduïnt les matrius
            que ens passen, per tant, anirem cridant a la mateixa funció fins que aconseguim una matriu de 2x2 i
            poder operar.
         */
        for (int i = 0; i < mat.length; i++) {

            if (i % 2 == 0) {
                resultado += mat[0][i] * determinant(getMinor(mat, 0, i));
            } else {
                resultado -= mat[0][i] * determinant(getMinor(mat, 0, i));

            }

        }

        //Retornam el resultat obtingut
        return resultado;
    }

    /*
        Aquesta funció és la encarregada de fer els càlculs per treure el determinant d'una
        funció amb dimensió 2x2
     */
    static double det2x2(double[][] mat) {

        /*
            Per obtenir el determinant d'una matriu 2x2, haurem de fer multiplicacions en creu,
            i després fer la diferència entre els dos nombres
         */
        return mat[0][0] * mat[1][1] - mat[0][1] * mat[1][0];

    }

    /*
        L'objectiu d'aquesta funció, és canviar el sentit de la matriu. És a dir, canviar les posicions
        de les files per les columnes
     */
    static double[][] transpose(double[][] mat) {

        /*
            La matriu resultant tindrà les dimensions girades de la matriu original
         */
        result = new double[mat[0].length][mat.length];

        /*
            Anirem assignant els valors a la nova matriu invertint la posició
         */
        for (int i = 0; i < mat.length; i++) {

            for (int j = 0; j < mat[0].length; j++) {

                result[j][i] = mat[i][j];

            }

        }

        //Retornam la matriu resultant
        return result;
    }

    /*
        Aquesta funció és la encarregada de comprovar si una matriu ñes o no ortogonal.
        El procediment per saber si és ortogonal, és quan el determinant de la inversa de la matriu,
        és igual al determinant de la mateixa matriu trasposada.
     */
    static boolean isOrtho(double[][] mat) {
        return determinant(invert(mat)) == determinant(transpose(mat));
    }

    /*
        Aquesta funció és la encarregada de realitzar el test de Cramer.
        El test de Cramer és un procediment emprat per desxifrar incògnites a equacions
     */
    static double[] cramer(double[][] mat) {

        /*
            Definim diferents variables per a emmagatzemar dades.

            A res emmagatzemarem el resultat de les incògnitez al final de la funció.

            A cofs emmagatzemarem els operands de les incógnites de l'operació ( sense en tenir en compte signes).

            A inc emmagatzemarem el "resultat" de les equacions, que sempre està al final de la matriu.


         */
        double[] res = new double[mat[0].length - 1];

        double[][] cofs = new double[mat.length][mat[0].length - 1];

        double[][] inc = new double[mat.length][1];


        for (int i = 0; i < cofs.length; i++) {
            for (int j = 0; j < cofs[0].length; j++) {
                cofs[i][j] = mat[i][j];
            }
        }

        for (int i = 0; i < inc.length; i++) {
            inc[i][0] = mat[i][mat[0].length - 1];
        }

        double detA = determinant(cofs);
        if (detA == 0) return null;


        for (int i = 0; i < cofs.length; i++) {

            double[][] mat2 = new double[cofs.length][cofs[0].length];

            for (int j = 0; j < mat2.length; j++) {
                for (int k = 0; k < mat2[0].length; k++) {
                    mat2[j][k] = cofs[j][k];
                }
            }


            for (int j = 0; j < cofs[0].length; j++) {


                mat2[j][i] = inc[j][0];

            }
            System.out.println("Matriz con incognita " + i);
            printMat(mat2);
            System.out.println();
            res[i] = determinant(mat2) / detA;
        }

        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }

        return res;
    }

    // Funció que mostra una matriu per pantalla
    static void printMat(double[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                System.out.printf("%06.2f ", mat[i][j]);
            }
            System.out.println();
        }
    }
}
