package habitacion;

import java.time.LocalDate;
import java.util.List;

public class GestorDisponibilidadHabitacion {
	boolean verificar(Habitacion h, List<LocalDate> fechas)
	{
		boolean disponible = true;
		for(List<LocalDate> fechasReserva: h.fechasReservadas.values())
		{
			for(LocalDate fecha: fechas)
			{
				if(fechasReserva.contains(fecha))
				{
					System.out.println("La fecha "+fecha+" ya está reservada");
					disponible = false;
					break;
				}
			}
			if(!disponible)
			{
				break;
			}
		}
		return disponible;
	}
}
