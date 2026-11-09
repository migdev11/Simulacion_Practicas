package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class LCGView {
    private static final long M = 1L << 32;

    public void printTable(long[] values, int limit) {
        int rows = Math.min(limit, values.length);
        System.out.printf("%-6s | %-12s | %-12s%n", "n", "X(n)", "U(n)");
        System.out.println("-".repeat(34));
        for (int i = 0; i < rows; i++) {
            long x = values[i];
            double u = (double) x / M;
            System.out.printf("%-6d | %-12d | %-12.10f%n", i, x, u);
        }
    }

    public void plotSequence(long[] values, int sampleSize) {
        int width = 800;
        int height = 400;
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = img.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.BLACK);
        g.drawString("Secuencia LCG (primeras " + sampleSize + " valores)", 20, 25);

        int n = Math.min(sampleSize, values.length);
        if (n < 2) {
            g.dispose();
            return;
        }

        long minX = Long.MAX_VALUE;
        long maxX = Long.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (values[i] < minX) minX = values[i];
            if (values[i] > maxX) maxX = values[i];
        }

        int margin = 50;
        int plotW = width - 2 * margin;
        int plotH = height - 2 * margin - 50;

        g.drawLine(margin, height - margin - 50, margin, margin);
        g.drawLine(margin, height - margin - 50, width - margin, height - margin - 50);

        g.setColor(Color.BLUE);
        double range = (maxX == minX) ? 1 : (double) (maxX - minX);
        for (int i = 0; i < n - 1; i++) {
            int x1 = margin + (int) (((double) i / (n - 1)) * plotW);
            int y1 = height - margin - 50 - (int) (((double) (values[i] - minX) / range) * plotH);
            int x2 = margin + (int) (((double) (i + 1) / (n - 1)) * plotW);
            int y2 = height - margin - 50 - (int) (((double) (values[i + 1] - minX) / range) * plotH);
            g.drawLine(x1, y1, x2, y2);
        }
        g.dispose();

        try {
            File dir = new File("../graficas");
            if (!dir.exists()) dir.mkdirs();
            File out = new File(dir, "lcg_sequence.png");
            ImageIO.write(img, "png", out);
            System.out.println("Grafica guardada en: " + out.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("No se pudo guardar la grafica: " + e.getMessage());
        }
    }
}
