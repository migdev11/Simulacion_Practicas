class ControladorClima:
    def __init__(self, modelo_original, modelo_ajustado, vista):
        self.modelo_orig = modelo_original
        self.modelo_ajust = modelo_ajustado
        self.vista = vista

    def iniciar_simulacion_completa(self, horas, humedades, nubosidades, temperaturas):
        indices_orig = []
        indices_ajust = []

        self.vista.mostrar_cabecera("MODELO ORIGINAL")
        for hora, hum, nub, temp in zip(horas, humedades, nubosidades, temperaturas):
            h, n, tf, i, estado = self.modelo_orig.calcular_todo(hum, nub, temp)
            indices_orig.append(i)
            self.vista.mostrar_fila(hora, h, n, tf, i, estado)

        self.vista.mostrar_cabecera("MODELO AJUSTADO")
        for hora, hum, nub, temp in zip(horas, humedades, nubosidades, temperaturas):
            h, n, tf, i, estado = self.modelo_ajust.calcular_todo(hum, nub, temp)
            indices_ajust.append(i)
            self.vista.mostrar_fila(hora, h, n, tf, i, estado)

        self.vista.mostrar_grafica_doble(horas, indices_orig, indices_ajust)