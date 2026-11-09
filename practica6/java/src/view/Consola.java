package view;

import controller.PruebasEstadisticas.ResultadoChiCuadrado;
import controller.PruebasEstadisticas.ResultadoKS;

import java.util.Locale;

public class Consola {
    public void imprimirResultados(int n, double[] datos, ResultadoChiCuadrado chi, ResultadoKS ks) {
        System.out.printf(Locale.US, "%n=== Resultados detallados para N = %d ===%n", n);

        System.out.println("\n--- Secuencia de números pseudoaleatorios ---");
        for (int i = 0; i < datos.length; i++) {
            System.out.printf(Locale.US, "%.10f ", datos[i]);
            if ((i + 1) % 10 == 0) {
                System.out.println();
            }
        }
        System.out.println();

        System.out.println("\n--- Prueba Chi-cuadrado ---");
        System.out.printf(Locale.US, "%-18s%12s%12s%n", "Subintervalo", "Observada", "Esperada");

        int k = chi.frecuenciasObservadas.length;
        for (int i = 0; i < k; i++) {
            String etiqueta = String.format(
                Locale.US,
                "[%.1f, %.1f)",
                (double) i / k,
                (double) (i + 1) / k
            );
            System.out.printf(
                Locale.US,
                "%-18s%12.0f%12.2f%n",
                etiqueta,
                chi.frecuenciasObservadas[i],
                chi.frecuenciaEsperada
            );
        }

        System.out.printf(Locale.US, "Estadístico Chi-cuadrado : %.6f%n", chi.estadistico);
        System.out.printf(Locale.US, "Grados de libertad       : %d%n", chi.gradosLibertad);
        System.out.printf(Locale.US, "Valor p                  : %.6f%n", chi.valorP);
        System.out.printf(
            Locale.US,
            "Decisión (alpha=%.2f) : %s%n",
            chi.alpha,
            decision(chi.rechazarH0)
        );

        System.out.println("\n--- Prueba Kolmogorov-Smirnov ---");
        System.out.printf(Locale.US, "Estadístico D : %.6f%n", ks.estadisticoD);
        System.out.printf(Locale.US, "Valor p       : %.6f%n", ks.valorP);
        System.out.printf(
            Locale.US,
            "Decisión (alpha=%.2f) : %s%n",
            ks.alpha,
            decision(ks.rechazarH0)
        );
    }

    public void imprimirRutaGrafica(String ruta) {
        System.out.printf(Locale.US, "%nGráfica KS guardada en: %s%n", ruta);
    }

    public void imprimirTablaTiempos(int[] tamanos, double[] tiemposMs) {
        System.out.println("\n--- Tiempos de ejecución ---");
        System.out.printf(Locale.US, "%10s%28s%n", "N", "Tiempo de ejecución (ms)");

        for (int i = 0; i < tamanos.length; i++) {
            System.out.printf(Locale.US, "%10d%28.3f%n", tamanos[i], tiemposMs[i]);
        }
    }

    private String decision(boolean rechazarH0) {
        return rechazarH0 ? "Se rechaza H0" : "No se rechaza H0";
    }
}
