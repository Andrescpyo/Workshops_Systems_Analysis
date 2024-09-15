package workshop_1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Workshop {
    public static void main(String[] args) throws IOException {
        // Parámetros del programa
        int númeroSecuencias = 1000000;
        int tamañoSecuencia = 100;
        double probabilidadA = 0.25;
        double probabilidadC = 0.25;
        double probabilidadG = 0.25;
        double probabilidadT = 0.25;
        String archivoSalidaTodas = "secuencias_todas.txt";
        String archivoSalidaFiltradas = "secuencias_filtradas.txt";
        int tamañoSecuenciaBuscar = 6; // tamaño de la secuencia a buscar

        // Iniciar tiempo de ejecución total
        long startTimeTotal = System.nanoTime();

        // Crear secuencias genéticas artificiales
        String[] secuencias = new String[númeroSecuencias];
        for (int i = 0; i < númeroSecuencias; i++) {
            secuencias[i] = generarSecuencia(tamañoSecuencia, probabilidadA, probabilidadC, probabilidadG, probabilidadT);
        }

        // Guardar todas las secuencias en archivo
        try (PrintWriter writer = new PrintWriter(new FileWriter(archivoSalidaTodas))) {
            for (String secuencia : secuencias) {
                writer.println(secuencia);
            }
        }

        // Calcular entropía promedio
        double sumaEntropía = 0;
        for (String secuencia : secuencias) {
            sumaEntropía += calcularEntropía(secuencia);
        }
        double entropíaPromedio = sumaEntropía / secuencias.length;

        // Filtrar secuencias con entropía mayor o igual a la entropía promedio
        Map<String, Long> secuenciasFiltradas = new HashMap<>();
        for (String secuencia : secuencias) {
            double entropía = calcularEntropía(secuencia);
            if (entropía >= (entropíaPromedio)) {
                secuenciasFiltradas.put(secuencia, 1L); // o contar la frecuencia si es necesario
            }
        }

        // Guardar secuencias filtradas en archivo
        try (PrintWriter writer = new PrintWriter(new FileWriter(archivoSalidaFiltradas))) {
            for (Map.Entry<String, Long> entry : secuenciasFiltradas.entrySet()) {
                writer.println(entry.getKey());
            }
        }

        // Leer archivo y buscar la secuencia de caracteres de tamaño s que más se repite
        Map<String, Long> frecuenciasSecuencias = new HashMap<>();
        long startTime = System.nanoTime();  // Comenzar el cronometraje
        try (BufferedReader reader = new BufferedReader(new FileReader(archivoSalidaFiltradas))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                for (int i = 0; i <= linea.length() - tamañoSecuenciaBuscar; i++) {
                    String secuencia = linea.substring(i, i + tamañoSecuenciaBuscar);
                    frecuenciasSecuencias.put(secuencia, frecuenciasSecuencias.getOrDefault(secuencia, 0L) + 1);
                }
            }
        }
        long endTime = System.nanoTime();  // detener el cronometraje
        long elapsedTime = endTime - startTime;  // Calcular el tiempo transcurrido en nanosegundos.
        double elapsedTimeSeconds = (double) elapsedTime / 1_000_000_000;  // Convertir a segundos.

        // Encontrar secuencia más frecuente
        String secuenciaMasFrecuente = null;
        long frecuenciaMaxima = 0;
        for (Map.Entry<String, Long> entry : frecuenciasSecuencias.entrySet()) {
            if (entry.getValue() > frecuenciaMaxima) {
                frecuenciaMaxima = entry.getValue();
                secuenciaMasFrecuente = entry.getKey();
            }
        }

        // Mostrar resultados
        System.out.println("\nSecuencia más frecuente de tamaño " + tamañoSecuenciaBuscar + ": " + secuenciaMasFrecuente);
        System.out.println("Frecuencia: Aparece " + frecuenciaMaxima + " veces en el archivo de secuencias filtradas");
        System.out.println("Entropía promedio: " + entropíaPromedio);
        System.out.println("Tiempo de búsqueda del motif: " + String.format("%.3f", elapsedTimeSeconds) + " segundos");  // Print elapsed time in seconds

        // Mostrar tiempo de ejecución total
        long endTimeTotal = System.nanoTime();
        long elapsedTimeTotal = endTimeTotal - startTimeTotal;
        double elapsedTimeTotalSeconds = (double) elapsedTimeTotal / 1_000_000_000;
        System.out.println("Tiempo de ejecución total: " + String.format("%.3f", elapsedTimeTotalSeconds) + " segundos\n");
    }

    private static String generarSecuencia(int tamaño, double probabilidadA, double probabilidadC, double probabilidadG, double probabilidadT) {
        // Genera una secuencia genética artificial de tamaño 's' con las probabilidades dadas
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < tamaño; i++) {
            double aleatorio = random.nextDouble();
            if (aleatorio < probabilidadA) {
                sb.append('A');
            } else if (aleatorio < probabilidadA + probabilidadC) {
                sb.append('C');
            } else if (aleatorio < probabilidadA + probabilidadC + probabilidadG) {
                sb.append('G');
            } else {
                sb.append('T');
            }
        }
        return sb.toString();
    }

    private static double calcularEntropía(String secuencia) {
        // Calcular la entropía de una secuencia genética
        int[] frecuencias = new int[4]; // A, C, G, T
        for (char nucleótido : secuencia.toCharArray()) {
            switch (nucleótido) {
                case 'A' -> frecuencias[0]++;
                case 'C' -> frecuencias[1]++;
                case 'G' -> frecuencias[2]++;
                case 'T' -> frecuencias[3]++;
            }
        }
        double entropía = 0;
        for (int frecuencia : frecuencias) {
            if (frecuencia > 0) {
                double probabilidad = (double) frecuencia / secuencia.length();
                entropía -= probabilidad * Math.log(probabilidad) / Math.log(2);
            }
        }
        return entropía;
    }
}