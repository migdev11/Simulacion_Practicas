from controller.LCGController import LCGController
from model.LCGModel import LCGModel
from view.LCGView import LCGView


def main():
    model = LCGModel(seed=12345)
    view = LCGView()
    controller = LCGController(model=model, view=view)

    print("=== Simulacion LCG con semilla X0 = 12345 ===")
    values = controller.run_simulation(seed=12345, n=10000)
    view.print_table(values, limit=20)

    print("\n=== Cambio de semilla a X0 = 987654321 ===")
    new_values = controller.change_seed_and_sample(new_seed=987654321, sample_size=20)
    view.print_table(new_values, limit=20)

    try:
        view.plot_sequence(values, sample_size=1000)
    except Exception as e:
        print(f"No se pudo renderizar la grafica: {e}")


if __name__ == "__main__":
    main()
