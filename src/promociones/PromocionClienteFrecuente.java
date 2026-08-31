package promociones;

import java.time.LocalDate;

import clientes.Cliente;

public class PromocionClienteFrecuente extends Promocion {
	public PromocionClienteFrecuente(double descuento)
	{
		super(descuento);
	}

	@Override
	public boolean aplica(Cliente cliente, LocalDate fecha)
	{
		return cliente != null && cliente.isFrecuente();
	}
}
