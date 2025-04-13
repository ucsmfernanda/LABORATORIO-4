package ejercicio2;
import java.util.Random;

public class QuickSelect {

     //Metodo principal que recibe el arreglo y el valor k
     //Devuelve el k-esimo elemento más pequeño del arreglo (sin ordenarlo completamente)
    
    public static int seleccionarElemento(int[] arreglo, int k) {
        // Llamamos al método auxiliar con índices del arreglo y k-1 (porque el arreglo es base 0)
        return seleccionarElemento(arreglo, 0, arreglo.length - 1, k - 1);
    }

     //Metodo recursivo que implementa la logica de QuickSelect.
     //Divide y busca en la parte relevante del arreglo.
     
    private static int seleccionarElemento(int[] arreglo, int izquierdo, int derecho, int k) {
        // Caso base: si hay un solo elemento, lo devolvemos
        if (izquierdo == derecho) {
            return arreglo[izquierdo];
        }

        // Elegimos un pivote y particionamos el arreglo
        int indicePivote = particionar(arreglo, izquierdo, derecho);

        // Si el indice del pivote es justo el que queremos, devolvemos el valor
        if (k == indicePivote) {
            return arreglo[k];
        } 
        // Si k esta en la parte izquierda, seguimos buscando alli
        else if (k < indicePivote) {
            return seleccionarElemento(arreglo, izquierdo, indicePivote - 1, k);
        } 
        // Si k esta en la parte derecha, buscamos en esa parte
        else {
            return seleccionarElemento(arreglo, indicePivote + 1, derecho, k);
        }
    }

    /**
     * Particiona el arreglo usando un pivote aleatorio.
     * Todos los elementos menores al pivote se van a la izquierda, los mayores se quedan a la derecha.
     * Devuelve la posición final del pivote.
     */
    private static int particionar(int[] arreglo, int izquierdo, int derecho) {
        // Elegimos un índice aleatorio como pivote
        int indicePivote = new Random().nextInt(derecho - izquierdo + 1) + izquierdo;
        int valorPivote = arreglo[indicePivote];

        // Movemos el pivote al final del arreglo temporalmente
        intercambiar(arreglo, indicePivote, derecho);

        int indiceAlmacenamiento = izquierdo;

        // Recorremos el arreglo y movemos a la izquierda los elementos menores al pivote
        for (int i = izquierdo; i < derecho; i++) {
            if (arreglo[i] < valorPivote) {
                intercambiar(arreglo, i, indiceAlmacenamiento);
                indiceAlmacenamiento++;
            }
        }

        // Finalmente, colocamos el pivote en su posición final
        intercambiar(arreglo, indiceAlmacenamiento, derecho);

        // Devolvemos la posicion donde quedo el pivote
        return indiceAlmacenamiento;
    }
    
     //Metodo auxiliar para intercambiar dos elementos del arreglo
    
    private static void intercambiar(int[] arreglo, int i, int j) {
        int temporal = arreglo[i];
        arreglo[i] = arreglo[j];
        arreglo[j] = temporal;
    }

     //Metodo main para probar el algoritmo con los casos dados
     
    public static void main(String[] args) {
        int[] caso1 = {4, 2, 7, 10, 4, 17};
        int[] caso2 = {4, 2, 7, 10, 4, 1, 6};
        int[] caso3 = {4, 2, 7, 1, 4, 6};
        int[] caso4 = {9, 2, 7, 1, 7};

        System.out.println(seleccionarElemento(caso1, 3)); //  4
        System.out.println(seleccionarElemento(caso2, 5)); //  6
        System.out.println(seleccionarElemento(caso3, 1)); //  1
        System.out.println(seleccionarElemento(caso4, 4)); //  7
    }
}

