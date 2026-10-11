class ModeloClima:
    def __init__(self, peso_h=0.5, peso_n=0.3, peso_tf=0.2):
        self.peso_h = peso_h
        self.peso_n = peso_n
        self.peso_tf = peso_tf

    def sacar_tf(self, temperatura):
        if temperatura <= 10:
            return 1.00
        elif temperatura >= 28:
            return 0.10
        else:
            return round(1.0 - (temperatura - 10) * 0.05, 2)

    def ver_estado(self, indice):
        if indice < 0.40:
            return "Sin lluvia"
        elif 0.40 <= indice < 0.60:
            return "Baja posibilidad"
        elif 0.60 <= indice < 0.75:
            return "Lluvia probable"
        else:
            return "Lluvia"

    def calcular_todo(self, humedad_porcentaje, nubosidad_porcentaje, temperatura):
        h = humedad_porcentaje / 100.0
        n = nubosidad_porcentaje / 100.0
        tf = self.sacar_tf(temperatura)

        i = (self.peso_h * h) + (self.peso_n * n) + (self.peso_tf * tf)
        estado_clima = self.ver_estado(i)

        return h, n, tf, i, estado_clima