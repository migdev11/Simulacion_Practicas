class LCGModel:
    A = 1664525
    C = 1013904223
    M = 2 ** 32

    def __init__(self, seed=12345):
        self._seed = seed

    @property
    def seed(self):
        return self._seed

    @seed.setter
    def seed(self, value):
        self._seed = value

    def next_value(self):
        self._seed = (self.A * self._seed + self.C) % self.M
        return self._seed

    def generate(self, n):
        values = []
        for _ in range(n):
            values.append(self.next_value())
        return values

    def normalize(self, value):
        return value / self.M

    def reset(self, seed):
        self._seed = seed
