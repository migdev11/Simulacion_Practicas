import controller.LCGController;
import model.LCGModel;
import view.LCGView;

public class Main {
    public static void main(String[] args) {
        LCGModel model = new LCGModel(12345L);
        LCGView view = new LCGView();
        LCGController controller = new LCGController(model, view);

        System.out.println("=== Simulacion LCG con semilla X0 = 12345 ===");
        long[] values = controller.runSimulation(12345L, 10000);
        view.printTable(values, 20);

        System.out.println("\n=== Cambio de semilla a X0 = 987654321 ===");
        long[] newValues = controller.changeSeedAndSample(987654321L, 20);
        view.printTable(newValues, 20);

        System.out.println("\n=== Generando grafica ===");
        view.plotSequence(values, 1000);
    }
}
