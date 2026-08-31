package habitacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import clientes.Cliente;
import politicas.PoliticaCancelacion;
import promociones.Promocion;
import promociones.Temporada;

public class Reserva {
	private static int codigoReserva = 0;
	private int codigo;
	private Habitacion habitacion;
	private Cliente cliente;
	private List<LocalDate> fechas = new ArrayList<>();
	private Temporada temporada;
	private Promocion promocion;
	private PoliticaCancelacion pc;
	private double precio;
	private boolean cancelada;
	private boolean checkIn;

	public Reserva(Habitacion habitacion, Cliente cliente, List<LocalDate> fechas, Temporada temporada, Promocion promocion, PoliticaCancelacion pc, double precio)
	{
		codigoReserva++;
		this.codigo = codigoReserva;
		this.habitacion = habitacion;
		this.cliente = cliente;
		this.fechas = new ArrayList<>(fechas);
		this.temporada = temporada;
		this.promocion = promocion;
		this.pc = pc;
		this.precio = precio;
	}

	public int getCodigo()
	{
		return codigo;
	}

	public Habitacion getHabitacion()
	{
		return habitacion;
	}

	public Cliente getCliente()
	{
		return cliente;
	}

	public List<LocalDate> getFechas()
	{
		return fechas;
	}

	public LocalDate getFechaCheckIn()
	{
		return fechas.get(0);
	}

	public Temporada getTemporada()
	{
		return temporada;
	}

	public Promocion getPromocion()
	{
		return promocion;
	}

	public double getPrecio()
	{
		return precio;
	}

	public boolean isCancelada()
	{
		return cancelada;
	}

	public boolean isCheckIn()
	{
		return checkIn;
	}

	public void setCheckIn(boolean checkIn)
	{
		this.checkIn = checkIn;
	}

	public boolean cancelar()
	{
		if(cancelada)
		{
			System.out.println("La reserva "+codigo+" ya está cancelada");
			return false;
		}
		if(!pc.puedeCancelar(this))
		{
			System.out.println("La política seleccionada no permite cancelar la reserva "+codigo);
			return false;
		}
		double penalizacion = pc.penalizacion(this);
		habitacion.liberarReserva(codigo);
		cancelada = true;
		System.out.println("Reserva "+codigo+" cancelada. Penalización: "+penalizacion);
		return true;
	}
}
