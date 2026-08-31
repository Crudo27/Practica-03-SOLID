package politicas;

import habitacion.Reserva;

public class PoliticaCancelacionEstricta implements PoliticaCancelacion {
	@Override
	public boolean puedeCancelar(Reserva reserva)
	{
		return false;
	}

	@Override
	public double penalizacion(Reserva reserva)
	{
		return reserva.getPrecio();
	}
}
