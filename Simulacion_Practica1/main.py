from modelo import ModeloClima
from vista import VistaClima
from controlador import ControladorClima

# Datos sacados de la tabla de la practica
lista_horas = ['06:00', '08:00', '10:00', '12:00', '14:00', '16:00', '18:00', '20:00', '22:00']
lista_humedad = [65, 70, 68, 60, 75, 85, 92, 88, 80]
lista_nubosidad = [40, 50, 45, 30, 70, 85, 95, 90, 75]
lista_temp = [14, 16, 18, 22, 20, 18, 16, 17, 15]

mod_original = ModeloClima(peso_h=0.5, peso_n=0.3, peso_tf=0.2)
mod_ajustado = ModeloClima(peso_h=0.3, peso_n=0.5, peso_tf=0.2)

mi_vista = VistaClima()

mi_controlador = ControladorClima(mod_original, mod_ajustado, mi_vista)

mi_controlador.inicSiar_simulacion_completa(lista_horas, lista_humedad, lista_nubosidad, lista_temp)