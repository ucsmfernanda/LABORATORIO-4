package actividad4;
import java.util.*;

 //Clase que representa los límites (índices inicial y final) de un subarreglo.
class Limits {
    int prim, ult;

    Limits(int prim, int ult) {
        this.prim = prim;
        this.ult = ult;
    }

     //Calcula la longitud del subarreglo representado por los límites.
      
    int length() {
        return ult - prim + 1;
    }
}

 //Clase que representa un subarreglo dentro de un arreglo original, usando límites.

class SubArray {
    int[] array;
    Limits limits;

    SubArray(int[] array, int prim, int ult) {
        this.array = array;
        this.limits = new Limits(prim, ult);
    }
    //Devuelve la longitud del subarreglo.
    
    int length() {
        return limits.length();
    }
     //Devuelve el elemento en la posición i relativa al subarreglo.
     
    int get(int i) {
        return array[limits.prim + i];
    }

     //Obtiene la mediana del subarreglo (elemento en la posición central).

    int getMedian() {
        int mid = limits.prim + (limits.ult - limits.prim) / 2;
        return array[mid];
    }
}

 //Clase que administra un conjunto de subarreglos mediante una cola de prioridad, ordenando por longitud de mayor a menor.
class SetVectors {
    PriorityQueue<SubArray> queue;

    SetVectors() {
        // Ordenamos los subarreglos por su longitud, de mayor a menor.
        queue = new PriorityQueue<>((a, b) -> b.length() - a.length());
    }

     //Inserta un subarreglo en el conjunto si no está vacío.
    
    void insert(SubArray sub) {
        if (sub.length() > 0)
            queue.add(sub);
    }

    //Extrae el subarreglo más largo del conjunto.
    
    SubArray extractMax() {
        return queue.poll();
    }

     //Verifica si el conjunto está vacío.
    
    boolean isEmpty() {
        return queue.isEmpty();
    }

     //Devuelve la longitud del subarreglo más largo en el conjunto.
    
    int maxLength() {
        return queue.isEmpty() ? 0 : queue.peek().length();
    }
}

 //Clase principal que contiene el método para encontrar la moda usando la técnica de divide y vencerás.
public class Moda3Solver {

     //Encuentra la moda de un arreglo de enteros.
  
    public static int moda3(int[] array) {
        SetVectors homogeneos = new SetVectors();   // Subarreglos con todos los elementos iguales
        SetVectors heterogeneos = new SetVectors(); // Subarreglos con elementos distintos

        // Insertamos el arreglo completo como punto de partida
        heterogeneos.insert(new SubArray(array, 0, array.length - 1));

        // Iteramos mientras el subarreglo más largo de heterogéneos
        // sea mayor que el más largo de homogéneos
        while (!heterogeneos.isEmpty() &&
               heterogeneos.maxLength() > homogeneos.maxLength()) {

            SubArray p = heterogeneos.extractMax();
            int mediana = p.getMedian(); // Obtenemos la mediana del subarreglo

            // Dividimos los elementos en tres grupos: <, = y > que la mediana
            List<Integer> menores = new ArrayList<>();
            List<Integer> iguales = new ArrayList<>();
            List<Integer> mayores = new ArrayList<>();

            for (int i = 0; i < p.length(); i++) {
                int val = p.get(i);
                if (val < mediana) menores.add(val);
                else if (val == mediana) iguales.add(val);
                else mayores.add(val);
            }

            // Insertamos los nuevos subarreglos en los conjuntos correspondientes
            if (!menores.isEmpty())
                heterogeneos.insert(new SubArray(arrayFromList(menores), 0, menores.size() - 1));
            if (!mayores.isEmpty())
                heterogeneos.insert(new SubArray(arrayFromList(mayores), 0, mayores.size() - 1));
            if (!iguales.isEmpty())
                homogeneos.insert(new SubArray(arrayFromList(iguales), 0, iguales.size() - 1));
        }

        // La moda será el primer elemento del subarreglo homogéneo más largo
        if (!homogeneos.isEmpty()) {
            return homogeneos.extractMax().get(0);
        }

        return -1; // Caso en que no se encuentra la moda
    }

     //Convierte una lista de enteros en un arreglo de enteros.

    private static int[] arrayFromList(List<Integer> list) {
        return list.stream().mapToInt(i -> i).toArray();
    }

     //Método principal para probar el algoritmo.
 
    public static void main(String[] args) {
        int[] arreglo = {4, 3, 1, 2, 2, 2, 3, 3, 3, 3, 5, 6};
        int moda = moda3(arreglo);
        System.out.println("La moda es: " + moda);
    }
}

