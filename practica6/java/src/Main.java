import controller.PruebasEstadisticas;
import controller.PruebasEstadisticas.ResultadoChiCuadrado;
import controller.PruebasEstadisticas.ResultadoKS;
import model.GeneradorLCG;
import view.Consola;
import view.GraficadorKS;

import java.io.File;

public class Main {
    private static final int[] VALORES_N = {1000, 10000, 100000, 1000000};
    private static final int N_DETALLE = 10000;
    private static final double ALPHA = 0.05;

    public static void main(String[] args) {
        Consola consola = new Consola();

        int[] tamanos = new int[VALORES_N.length];
        double[] tiemposMs = new double[VALORES_N.length];

        for (int i = 0; i < VALORES_N.length; i++) {
            int n = VALORES_N[i];
            GeneradorLCG generador = new GeneradorLCG();

            long inicio = System.nanoTime();
            double[] datos = generador.generar(n);
            ResultadoChiCuadrado chi = PruebasEstadisticas.pruebaChiCuadrado(datos, 10, ALPHA);
            ResultadoKS ks = PruebasEstadisticas.pruebaKS(datos, ALPHA);
            long fin = System.nanoTime();

            tamanos[i] = n;
            tiemposMs[i] = (fin - inicio) / 1_000_000.0;

            if (n == N_DETALLE) {
                consola.imprimirResultados(n, datos, chi, ks);
                File rutaGrafica = new File("graficas", "ks_N" + n + ".png");
                new GraficadorKS().guardar(ks, n, rutaGrafica);
                consola.imprimirRutaGrafica(rutaGrafica.getAbsolutePath());
            }
        }

        consola.imprimirTablaTiempos(tamanos, tiemposMs);
    }
}
