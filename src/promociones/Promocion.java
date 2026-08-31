package promociones;

import java.time.LocalDate;

import clientes.Cliente;

public abstract class Promocion {
	protected double descuento;

	public Promocion(double descuento)
	{
		this.descuento = descuento;
	}

	public double getDescuento()
	{
		return descuento;
	}

	public void setDescuento(double descuento)
	{
		this.descuento = descuento;
	}

	public abstract boolean aplica(Cliente cliente, LocalDate fecha);
}
