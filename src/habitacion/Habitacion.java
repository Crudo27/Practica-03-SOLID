package habitacion;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import clientes.Cliente;
import promociones.Promocion;
import promociones.Temporada;

public class Habitacion {
	int numero;
	String descripcion;
	double precio;
	boolean ocupada;
	Map<Integer, List<LocalDate>> fechasReservadas = new TreeMap<>();
	private GestorDisponibilidadHabitacion gdh = new GestorDisponibilidadHabitacion();
	private CalculadoraPrecioHabitacion cph = new CalculadoraPrecioHabitacion();

	public Habitacion(int numero, String descripcion, double precio)
	{
		this.numero = numero;
		this.descripcion = descripcion;
		this.precio = precio;
	}

	public int getNumero()
	{
		return numero;
	}

	public String getDescripcion()
	{
		return descripcion;
	}

	public double getPrecio()
	{
		return precio;
	}

	public boolean isOcupada()
	{
		return ocupada;
	}

	public void setOcupada(boolean ocupada)
	{
		this.ocupada = ocupada;
	}

	public String getTipo()
	{
		return "Habitación";
	}

	public GestorDisponibilidadHabitacion getGestor()
	{
		return gdh;
	}

	public Map<Integer, List<LocalDate>> getFechasReservadas()
	{
		return fechasReservadas;
	}

	public boolean verificar(List<LocalDate> fechas)
	{
		return gdh.verificar(this, fechas);
	}

	public double calcularPrecio(List<LocalDate> fechas, Temporada temporada, Promocion promocion, Cliente cliente)
	{
		return cph.calcularPrecio(this, fechas, temporada, promocion, cliente);
	}

	public void reservar(int codigo, List<LocalDate> fechas)
	{
		fechasReservadas.put(codigo, fechas);
	}

	public void liberarReserva(int codigo)
	{
		fechasReservadas.remove(codigo);
	}
}
