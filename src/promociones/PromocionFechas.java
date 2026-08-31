package promociones;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import clientes.Cliente;

public class PromocionFechas extends Promocion {
	private List<LocalDate> fechas = new ArrayList<>();

	public PromocionFechas(double descuento)
	{
		super(descuento);
	}

	@Override
	public boolean aplica(Cliente cliente, LocalDate fecha)
	{
		return fechas.contains(fecha);
	}

	public List<LocalDate> getFechas()
	{
		return fechas;
	}

	public void setFechas(List<LocalDate> fechas)
	{
		this.fechas = fechas;
	}

	public void addFecha(LocalDate fecha)
	{
		fechas.add(fecha);
	}
}
