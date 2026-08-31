package clientes;

import java.util.ArrayList;
import java.util.List;

import habitacion.Reserva;

public class Cliente {
	private int codigo;
	private String nombre;
	private String contacto;
	private boolean frecuente;
	private List<Reserva> historial = new ArrayList<>();

	public Cliente(int codigo, String nombre, String contacto, boolean frecuente)
	{
		this.codigo = codigo;
		this.nombre = nombre;
		this.contacto = contacto;
		this.frecuente = frecuente;
	}

	public int getCodigo()
	{
		return codigo;
	}

	public String getNombre()
	{
		return nombre;
	}

	public String getContacto()
	{
		return contacto;
	}

	public boolean isFrecuente()
	{
		return frecuente;
	}

	public void setFrecuente(boolean frecuente)
	{
		this.frecuente = frecuente;
	}

	public List<Reserva> getHistorial()
	{
		return historial;
	}

	public void agregarReserva(Reserva reserva)
	{
		historial.add(reserva);
	}
}
