package controller;

import java.util.Arrays;

public class PruebasEstadisticas {
    private static final double EPS = 1e-15;
    private static final double FPMIN = 1e-300;
    private static final double FPMAX = 1e300;

    private static final double[] LANCZOS = {
        676.5203681218851,
        -1259.1392167224028,
        771.32342877765313,
        -176.61502916214059,
        12.507343278686905,
        -0.13857109526572012,
        9.9843695780195716e-6,
        1.5056327351493116e-7
    };

    public static class ResultadoChiCuadrado {
        public final double[] frecuenciasObservadas;
        public final double frecuenciaEsperada;
        public final double estadistico;
        public final int gradosLibertad;
        public final double valorP;
        public final double alpha;
        public final boolean rechazarH0;

        public ResultadoChiCuadrado(
            double[] frecuenciasObservadas,
            double frecuenciaEsperada,
            double estadistico,
            int gradosLibertad,
            double valorP,
            double alpha,
            boolean rechazarH0
        ) {
            this.frecuenciasObservadas = frecuenciasObservadas;
            this.frecuenciaEsperada = frecuenciaEsperada;
            this.estadistico = estadistico;
            this.gradosLibertad = gradosLibertad;
            this.valorP = valorP;
            this.alpha = alpha;
            this.rechazarH0 = rechazarH0;
        }
    }

    public static class ResultadoKS {
        public final double estadisticoD;
        public final double valorP;
        public final double alpha;
        public final boolean rechazarH0;
        public final double[] xOrdenado;
        public final double[] cdfEmpirica;
        public final double[] cdfTeorica;

        public ResultadoKS(
            double estadisticoD,
            double valorP,
            double alpha,
            boolean rechazarH0,
            double[] xOrdenado,
            double[] cdfEmpirica,
            double[] cdfTeorica
        ) {
            this.estadisticoD = estadisticoD;
            this.valorP = valorP;
            this.alpha = alpha;
            this.rechazarH0 = rechazarH0;
            this.xOrdenado = xOrdenado;
            this.cdfEmpirica = cdfEmpirica;
            this.cdfTeorica = cdfTeorica;
        }
    }

    public static ResultadoChiCuadrado pruebaChiCuadrado(double[] datos, int k, double alpha) {
        int n = datos.length;
        if (n == 0) {
            throw new IllegalArgumentException("La muestra no puede estar vacía.");
        }

        double[] frecuenciasObservadas = new double[k];
        int[] conteos = new int[k];

        for (double valor : datos) {
            int indice = (int) Math.floor(valor * k);
            if (indice < 0) {
                indice = 0;
            } else if (indice >= k) {
                indice = k - 1;
            }
            conteos[indice]++;
        }

        double frecuenciaEsperada = (double) n / k;
        double estadistico = 0.0;

        for (int i = 0; i < k; i++) {
            frecuenciasObservadas[i] = conteos[i];
            double diferencia = frecuenciasObservadas[i] - frecuenciaEsperada;
            estadistico += (diferencia * diferencia) / frecuenciaEsperada;
        }

        int gradosLibertad = k - 1;
        double valorP = chiCuadradoValorP(estadistico, gradosLibertad);
        boolean rechazarH0 = valorP < alpha;

        return new ResultadoChiCuadrado(
            frecuenciasObservadas,
            frecuenciaEsperada,
            estadistico,
            gradosLibertad,
            valorP,
            alpha,
            rechazarH0
        );
    }

    public static ResultadoKS pruebaKS(double[] datos, double alpha) {
        int n = datos.length;
        if (n == 0) {
            throw new IllegalArgumentException("La muestra no puede estar vacía.");
        }

        double[] xOrdenado = datos.clone();
        Arrays.sort(xOrdenado);

        double[] cdfEmpirica = new double[n];
        double[] cdfTeorica = new double[n];

        double dMas = 0.0;
        double dMenos = 0.0;

        for (int i = 1; i <= n; i++) {
            double empirica = (double) i / n;
            double teorica = xOrdenado[i - 1];

            cdfEmpirica[i - 1] = empirica;
            cdfTeorica[i - 1] = teorica;

            double diferenciaMas = empirica - teorica;
            double diferenciaMenos = teorica - (double) (i - 1) / n;

            if (diferenciaMas > dMas) {
                dMas = diferenciaMas;
            }
            if (diferenciaMenos > dMenos) {
                dMenos = diferenciaMenos;
            }
        }

        double estadisticoD = Math.max(dMas, dMenos);
        double valorP = ksValorP(estadisticoD, n);
        boolean rechazarH0 = valorP < alpha;

        return new ResultadoKS(
            estadisticoD,
            valorP,
            alpha,
            rechazarH0,
            xOrdenado,
            cdfEmpirica,
            cdfTeorica
        );
    }

    private static double chiCuadradoValorP(double estadistico, int gradosLibertad) {
        if (estadistico <= 0.0) {
            return 1.0;
        }
        return gammaQ(gradosLibertad / 2.0, estadistico / 2.0);
    }

    private static double gammaQ(double a, double x) {
        if (x <= 0.0) {
            return 1.0;
        }
        if (x < a + 1.0) {
            return 1.0 - gammaPSerie(a, x);
        }
        return gammaQFraccion(a, x);
    }

    private static double gammaPSerie(double a, double x) {
        double ap = a;
        double suma = 1.0 / a;
        double termino = suma;

        for (int i = 0; i < 10000; i++) {
            ap += 1.0;
            termino *= x / ap;
            suma += termino;
            if (Math.abs(termino) < Math.abs(suma) * EPS) {
                break;
            }
        }

        return suma * Math.exp(-x + a * Math.log(x) - logGamma(a));
    }

    private static double gammaQFraccion(double a, double x) {
        double b = x + 1.0 - a;
        double c = FPMAX;
        double d = 1.0 / b;
        double h = d;

        for (int i = 1; i < 10000; i++) {
            double an = -i * (i - a);
            b += 2.0;
            d = an * d + b;
            if (Math.abs(d) < FPMIN) {
                d = FPMIN;
            }
            c = b + an / c;
            if (Math.abs(c) < FPMIN) {
                c = FPMIN;
            }
            d = 1.0 / d;
            double delta = d * c;
            h *= delta;
            if (Math.abs(delta - 1.0) < EPS) {
                break;
            }
        }

        return Math.exp(-x + a * Math.log(x) - logGamma(a)) * h;
    }

    private static double logGamma(double x) {
        if (x < 0.5) {
            return Math.log(Math.PI / Math.sin(Math.PI * x)) - logGamma(1.0 - x);
        }

        x -= 1.0;
        double a = 0.99999999999980993;
        for (int i = 0; i < LANCZOS.length; i++) {
            a += LANCZOS[i] / (x + i + 1.0);
        }

        double t = x + LANCZOS.length - 0.5;
        return 0.5 * Math.log(2.0 * Math.PI)
            + (x + 0.5) * Math.log(t)
            - t
            + Math.log(a);
    }

    private static double ksValorP(double estadisticoD, int n) {
        if (n <= 0 || estadisticoD <= 0.0) {
            return 1.0;
        }

        double raizN = Math.sqrt(n);
        double lambda = (raizN + 0.12 + 0.11 / raizN) * estadisticoD;

        if (lambda <= 0.0) {
            return 1.0;
        }

        double valorP;

        if (lambda < 0.5) {
            double suma = 0.0;
            for (int j = 1; j < 1000; j++) {
                double impar = 2.0 * j - 1.0;
                double termino = Math.exp(
                    -(impar * impar * Math.PI * Math.PI) / (8.0 * lambda * lambda)
                );
                suma += termino;
                if (termino < 1e-16) {
                    break;
                }
            }
            double cdf = Math.sqrt(2.0 * Math.PI) / lambda * suma;
            valorP = 1.0 - cdf;
        } else {
            double suma = 0.0;
            double signo = 1.0;

            for (int j = 1; j < 1000; j++) {
                double termino = Math.exp(-2.0 * j * j * lambda * lambda);
                suma += signo * termino;
                if (termino < 1e-16) {
                    break;
                }
                signo = -signo;
            }

            valorP = 2.0 * suma;
        }

        if (valorP < 0.0) {
            return 0.0;
        }
        if (valorP > 1.0) {
            return 1.0;
        }
        return valorP;
    }
}
