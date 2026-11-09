import os
import matplotlib.pyplot as plt


class LCGView:
    def print_table(self, values, limit=20):
        print(f"{'n':<6} | {'X(n)':<12} | {'U(n)':<12}")
        print("-" * 34)
        m = 2 ** 32
        for i in range(min(limit, len(values))):
            x = values[i]
            u = x / m
            print(f"{i:<6} | {x:<12} | {u:<12.10f}")

    def plot_sequence(self, values, sample_size=1000):
        sample = values[:sample_size]
        plt.figure(figsize=(10, 5))
        plt.plot(sample, marker="o", linestyle="-", markersize=2)
        plt.title(f"Secuencia LCG (primeras {sample_size} valores)")
        plt.xlabel("n")
        plt.ylabel("X(n)")
        plt.grid(True, alpha=0.3)
        plt.tight_layout()

        output_dir = os.path.join(os.path.dirname(os.path.dirname(os.path.abspath(__file__))), "graficas")
        os.makedirs(output_dir, exist_ok=True)
        output_path = os.path.join(output_dir, "lcg_sequence.png")
        plt.savefig(output_path)
        print(f"Grafica guardada en: {output_path}")

        plt.show(block=False)
        plt.pause(2)
        plt.close()
