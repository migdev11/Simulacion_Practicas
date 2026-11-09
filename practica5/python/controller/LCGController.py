from model.LCGModel import LCGModel
from view.LCGView import LCGView


class LCGController:
    def __init__(self, model=None, view=None):
        self.model = model or LCGModel()
        self.view = view or LCGView()

    def run_simulation(self, seed, n=10000):
        self.model.reset(seed)
        values = self.model.generate(n)
        return values

    def change_seed_and_sample(self, new_seed, sample_size=20):
        self.model.reset(new_seed)
        values = self.model.generate(sample_size)
        return values
