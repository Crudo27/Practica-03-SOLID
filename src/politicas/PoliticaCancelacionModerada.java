package politicas;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import habitacion.Reserva;

public class PoliticaCancelacionModerada implements PoliticaCancelacion {
	@Override
	public boolean puedeCancelar(Reserva reserva)
	{
		long diferencia = ChronoUnit.DAYS.between(LocalDate.now(), reserva.getFechaCheckIn());
		return diferencia >= 3;
	}

	@Override
	public double penalizacion(Reserva reserva)
	{
		return reserva.getPrecio()*0.5;
	}
}
