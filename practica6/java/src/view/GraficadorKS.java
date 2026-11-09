package view;

import controller.PruebasEstadisticas.ResultadoKS;

import javax.imageio.ImageIO;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

public class GraficadorKS {
    private static final int ANCHO = 1000;
    private static final int ALTO = 700;
    private static final int MARGEN_IZQ = 90;
    private static final int MARGEN_DER = 50;
    private static final int MARGEN_SUP = 70;
    private static final int MARGEN_INF = 80;

    private static final Color AZUL = new Color(31, 119, 180);
    private static final Color ROJO = new Color(214, 39, 40);
    private static final Color REJILLA = new Color(230, 230, 230);

    public void guardar(ResultadoKS resultado, int n, File ruta) {
        try {
            BufferedImage imagen = new BufferedImage(ANCHO, ALTO, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = imagen.createGraphics();
            g.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
            );

            g.setColor(Color.WHITE);
            g.fillRect(0, 0, ANCHO, ALTO);

            int anchoPanel = ANCHO - MARGEN_IZQ - MARGEN_DER;
            int altoPanel = ALTO - MARGEN_SUP - MARGEN_INF;

            dibujarRejilla(g, anchoPanel, altoPanel);
            dibujarEjes(g, anchoPanel, altoPanel);
            dibujarTeorica(g, anchoPanel, altoPanel);
            dibujarEmpirica(g, resultado, anchoPanel, altoPanel);
            dibujarTitulo(g, resultado, n);
            dibujarEtiquetas(g, anchoPanel, altoPanel);
            dibujarLeyenda(g, anchoPanel);

            g.dispose();

            File directorio = ruta.getParentFile();
            if (directorio != null) {
                directorio.mkdirs();
            }
            ImageIO.write(imagen, "png", ruta);
        } catch (IOException e) {
            throw new RuntimeException("No se pudo guardar la gráfica KS.", e);
        }
    }

    private void dibujarRejilla(Graphics2D g, int anchoPanel, int altoPanel) {
        g.setColor(REJILLA);
        g.setStroke(new BasicStroke(1.0f));

        for (int i = 0; i <= 10; i++) {
            int x = MARGEN_IZQ + (int) Math.round(i / 10.0 * anchoPanel);
            int y = MARGEN_SUP + (int) Math.round(i / 10.0 * altoPanel);
            g.drawLine(x, MARGEN_SUP, x, MARGEN_SUP + altoPanel);
            g.drawLine(MARGEN_IZQ, y, MARGEN_IZQ + anchoPanel, y);
        }
    }

    private void dibujarEjes(Graphics2D g, int anchoPanel, int altoPanel) {
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(1.5f));
        g.drawRect(MARGEN_IZQ, MARGEN_SUP, anchoPanel, altoPanel);
    }

    private void dibujarTeorica(Graphics2D g, int anchoPanel, int altoPanel) {
        g.setColor(ROJO);
        g.setStroke(new BasicStroke(2.0f));
        g.drawLine(
            MARGEN_IZQ,
            MARGEN_SUP + altoPanel,
            MARGEN_IZQ + anchoPanel,
            MARGEN_SUP
        );
    }

    private void dibujarEmpirica(
        Graphics2D g,
        ResultadoKS resultado,
        int anchoPanel,
        int altoPanel
    ) {
        g.setColor(AZUL);
        g.setStroke(new BasicStroke(1.6f));

        Path2D.Double camino = new Path2D.Double();
        camino.moveTo(pixelX(0.0, anchoPanel), pixelY(0.0, altoPanel));

        double acumuladaAnterior = 0.0;
        double[] x = resultado.xOrdenado;
        double[] y = resultado.cdfEmpirica;

        for (int i = 0; i < x.length; i++) {
            double px = pixelX(x[i], anchoPanel);
            camino.lineTo(px, pixelY(acumuladaAnterior, altoPanel));
            camino.lineTo(px, pixelY(y[i], altoPanel));
            acumuladaAnterior = y[i];
        }

        camino.lineTo(pixelX(1.0, anchoPanel), pixelY(acumuladaAnterior, altoPanel));
        g.draw(camino);
    }

    private void dibujarTitulo(Graphics2D g, ResultadoKS resultado, int n) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("SansSerif", Font.BOLD, 18));

        String titulo = String.format(
            Locale.US,
            "Kolmogorov-Smirnov (N = %d, D = %.6f, p = %.6f)",
            n,
            resultado.estadisticoD,
            resultado.valorP
        );

        FontMetrics metricas = g.getFontMetrics();
        g.drawString(titulo, (ANCHO - metricas.stringWidth(titulo)) / 2, 35);
    }

    private void dibujarEtiquetas(Graphics2D g, int anchoPanel, int altoPanel) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("SansSerif", Font.PLAIN, 12));
        FontMetrics metricas = g.getFontMetrics();

        for (int i = 0; i <= 10; i += 2) {
            double valor = i / 10.0;
            String texto = String.format(Locale.US, "%.1f", valor);

            int x = MARGEN_IZQ + (int) Math.round(valor * anchoPanel);
            int y = MARGEN_SUP + (int) Math.round(valor * altoPanel);

            g.drawString(
                texto,
                x - metricas.stringWidth(texto) / 2,
                MARGEN_SUP + altoPanel + 20
            );
            g.drawString(
                texto,
                MARGEN_IZQ - 12 - metricas.stringWidth(texto),
                y + 4
            );
        }

        g.setFont(new Font("SansSerif", Font.PLAIN, 15));
        metricas = g.getFontMetrics();
        String etiquetaX = "x";
        String etiquetaY = "F(x)";

        g.drawString(
            etiquetaX,
            MARGEN_IZQ + anchoPanel / 2 - metricas.stringWidth(etiquetaX) / 2,
            ALTO - 25
        );

        Graphics2D girado = (Graphics2D) g.create();
        girado.translate(30, MARGEN_SUP + altoPanel / 2);
        girado.rotate(-Math.PI / 2.0);
        FontMetrics metricasGirado = girado.getFontMetrics();
        girado.drawString(etiquetaY, -metricasGirado.stringWidth(etiquetaY) / 2, 0);
        girado.dispose();
    }

    private void dibujarLeyenda(Graphics2D g, int anchoPanel) {
        int x = MARGEN_IZQ + anchoPanel - 320;
        int y = MARGEN_SUP + 20;

        g.setStroke(new BasicStroke(2.0f));
        g.setFont(new Font("SansSerif", Font.PLAIN, 13));

        g.setColor(AZUL);
        g.drawLine(x, y, x + 30, y);
        g.setColor(Color.BLACK);
        g.drawString("Distribución empírica Fn(x)", x + 40, y + 5);

        g.setColor(ROJO);
        g.drawLine(x, y + 25, x + 30, y + 25);
        g.setColor(Color.BLACK);
        g.drawString("Distribución teórica F(x) = x", x + 40, y + 30);
    }

    private int pixelX(double valor, int anchoPanel) {
        return MARGEN_IZQ + (int) Math.round(valor * anchoPanel);
    }

    private int pixelY(double valor, int altoPanel) {
        return MARGEN_SUP + altoPanel - (int) Math.round(valor * altoPanel);
    }
}
