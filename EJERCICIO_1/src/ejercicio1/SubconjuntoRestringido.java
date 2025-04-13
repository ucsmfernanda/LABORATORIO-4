package ejercicio1;
import java.util.*;

public class SubconjuntoRestringido {

    public static boolean puedeFormarSuma(int[] entrada) {
        int n = entrada[0];
        int objetivo = entrada[entrada.length - 1];

        List<Integer> listaOpcional = new ArrayList<>();
        int sumaObligatoria = 0;

        for (int i = 1; i < entrada.length - 1; i++) {
            int actual = entrada[i];

            // REGLA 1: Potencia de 2 → debe incluirse siempre
            if (esPotenciaDe2(actual)) {
                sumaObligatoria += actual;
                continue;
            }

            // REGLA 2: Múltiplo de 5 seguido por impar → se descarta
            if (actual % 5 == 0 && i + 1 < entrada.length - 1 && entrada[i + 1] % 2 != 0) {
                continue;
            }

            listaOpcional.add(actual);
        }

        if (sumaObligatoria > objetivo) return false;

        int sumaRestante = objetivo - sumaObligatoria;

        return puedeSumar(listaOpcional, 0, sumaRestante);
    }

    private static boolean puedeSumar(List<Integer> lista, int index, int objetivo) {
        if (objetivo == 0) return true;
        if (index == lista.size()) return false;

        return puedeSumar(lista, index + 1, objetivo - lista.get(index)) ||
               puedeSumar(lista, index + 1, objetivo);
    }

    private static boolean esPotenciaDe2(int num) {
        return num > 0 && (num & (num - 1)) == 0;
    }

    public static void main(String[] args) {
        int[] entrada1 = {5, 4, 8, 10, 3, 5, 27};     // true
        int[] entrada2 = {5, 4, 8, 10, 3, 6, 27};     // false
        int[] entrada3 = {6, 2, 16, 5, 7, 10, 33};    // true
        int[] entrada4 = {6, 2, 16, 5, 3, 10, 33};    // false
        int[] entrada5 = {4, 2, 5, 1, 6, 13};         // true

        System.out.println(puedeFormarSuma(entrada1)); // true
        System.out.println(puedeFormarSuma(entrada2)); // false
        System.out.println(puedeFormarSuma(entrada3)); // true
        System.out.println(puedeFormarSuma(entrada4)); // false
        System.out.println(puedeFormarSuma(entrada5)); // true
    }
}



