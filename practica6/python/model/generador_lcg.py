class GeneradorLCG:
    def __init__(self, semilla=12345, a=1664525, c=1013904223, m=2 ** 32):
        self.semilla = semilla
        self.a = a
        self.c = c
        self.m = m

    def generar(self, n):
        if n <= 0:
            return []

        valores = [0.0] * n
        x = self.semilla
        a = self.a
        c = self.c
        m = self.m

        for i in range(n):
            x = (a * x + c) % m
            valores[i] = x / m

        self.semilla = x
        return valores
