package politicas;

import habitacion.Reserva;

public interface PoliticaCancelacion {
	public abstract boolean puedeCancelar(Reserva reserva);
	public abstract double penalizacion(Reserva reserva);
}
