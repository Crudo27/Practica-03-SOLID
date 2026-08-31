package informes;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import habitacion.Habitacion;
import habitacion.Reserva;

public class GeneradorInformes {
	public void generarOcupacion(Collection<Habitacion> habitaciones, LocalDate inicio, LocalDate fin)
	{
		Map<String, Integer> ocupacion = new TreeMap<>();
		for(Habitacion h: habitaciones)
		{
			int dias = 0;
			for(java.util.List<LocalDate> fechas: h.getFechasReservadas().values())
			{
				for(LocalDate fecha: fechas)
				{
					if(!fecha.isBefore(inicio) && !fecha.isAfter(fin))
					{
						dias++;
					}
				}
			}
			ocupacion.put(h.getTipo(), ocupacion.getOrDefault(h.getTipo(), 0)+dias);
		}
		for(String tipo: ocupacion.keySet())
		{
			System.out.println(tipo+": "+ocupacion.get(tipo)+" días reservados");
		}
	}

	public void generarIngresos(Collection<Reserva> reservas, LocalDate inicio, LocalDate fin)
	{
		double ingresos = 0;
		for(Reserva reserva: reservas)
		{
			if(!reserva.isCancelada() && !reserva.getFechaCheckIn().isBefore(inicio) && !reserva.getFechaCheckIn().isAfter(fin))
			{
				ingresos += reserva.getPrecio();
			}
		}
		System.out.println("Ingresos del periodo: "+ingresos);
	}
}
