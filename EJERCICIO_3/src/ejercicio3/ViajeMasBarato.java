package ejercicio3;
public class ViajeMasBarato {

    // Metodo para calcular la matriz de costos minimos entre todos los pares de embarcaderos
    public static int[][] calcularCostosMinimos(int[][] tarifas) {
        int n = tarifas.length;
        int[][] costos = new int[n][n];

        // Recorremos todos los pares de embarcaderos (i, j)
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (i == j) {
                    costos[i][j] = 0; // Costo de ir de i a i es 0
                } else {
                    int minimo = tarifas[i][j]; // Se toma inicialmente el costo directo
                    for (int k = i + 1; k < j; k++) {
                        int costoConEscala = tarifas[i][k] + costos[k][j];
                        minimo = Math.min(minimo, costoConEscala);
                    }
                    costos[i][j] = minimo;
                }
            }
        }
        return costos;
    }

    // Metodo para imprimir la matriz de resultados
    public static void imprimirMatriz(int[][] matriz) {
        for (int[] fila : matriz) {
            for (int valor : fila) {
                System.out.print((valor == Integer.MAX_VALUE ? "INF" : valor) + "\t");
            }
            System.out.println();
        }
    }

    // Metodo main con ejemplo de uso
    public static void main(String[] args) {
        // Ejemplo de matriz triangular superior de tarifas
        int[][] tarifas = {
            {0, 3, 1, 6, 0},
            {0, 0, 1, 2, 4},
            {0, 0, 0, 1, 2},
            {0, 0, 0, 0, 1},
            {0, 0, 0, 0, 0}
        };

        int[][] costos = calcularCostosMinimos(tarifas);

        System.out.println("Matriz de costos mínimos:");
        imprimirMatriz(costos);
    }
}
