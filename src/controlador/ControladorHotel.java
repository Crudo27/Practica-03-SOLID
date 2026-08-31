package controlador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import clientes.Cliente;
import habitacion.Habitacion;
import habitacion.Reserva;
import informes.GeneradorInformes;
import notificaciones.NotificadorReserva;
import personal.PersonalLimpieza;
import politicas.PoliticaCancelacion;
import promociones.Promocion;
import promociones.Temporada;
import servicios.ServicioComida;
import servicios.ServicioLavanderia;
import servicios.ServicioLimpieza;

public class ControladorHotel {
	private Map<Integer, Habitacion> habitaciones = new TreeMap<>();
	private Map<Integer, Cliente> clientes = new TreeMap<>();
	private Map<Integer, PersonalLimpieza> personal = new TreeMap<>();
	private Map<Integer, Reserva> reservas = new TreeMap<>();
	private NotificadorReserva notificador;
	private GeneradorInformes informes = new GeneradorInformes();

	public ControladorHotel(NotificadorReserva notificador)
	{
		this.notificador = notificador;
	}

	public void registrarHabitacion(Habitacion habitacion)
	{
		habitaciones.put(habitacion.getNumero(), habitacion);
	}

	public void registrarCliente(Cliente cliente)
	{
		clientes.put(cliente.getCodigo(), cliente);
	}

	public void registrarPersonal(PersonalLimpieza trabajador)
	{
		personal.put(trabajador.getCodigo(), trabajador);
	}

	public List<Habitacion> consultarDisponibilidad(List<LocalDate> fechas)
	{
		List<Habitacion> disponibles = new ArrayList<>();
		for(Habitacion h: habitaciones.values())
		{
			if(h.verificar(fechas))
			{
				disponibles.add(h);
			}
		}
		return disponibles;
	}

	public Reserva reservar(int numeroHabitacion, int codigoCliente, List<LocalDate> fechas, Temporada temporada, Promocion promocion, PoliticaCancelacion politica)
	{
		Habitacion h = habitaciones.get(numeroHabitacion);
		Cliente cliente = clientes.get(codigoCliente);
		if(h == null || cliente == null)
		{
			System.out.println("No se encontró la habitación o el cliente");
			return null;
		}
		if(fechas == null || fechas.isEmpty())
		{
			System.out.println("Debe indicar al menos una fecha");
			return null;
		}
		if(!h.verificar(fechas))
		{
			return null;
		}
		double precio = h.calcularPrecio(fechas, temporada, promocion, cliente);
		Reserva reserva = new Reserva(h, cliente, fechas, temporada, promocion, politica, precio);
		h.reservar(reserva.getCodigo(), fechas);
		cliente.agregarReserva(reserva);
		reservas.put(reserva.getCodigo(), reserva);
		System.out.println("Reserva "+reserva.getCodigo()+" creada por "+precio);
		notificador.notificar("Reserva "+reserva.getCodigo()+" confirmada para "+cliente.getNombre());
		return reserva;
	}

	public void cancelar(int codigo)
	{
		Reserva reserva = reservas.get(codigo);
		if(reserva == null)
		{
			System.out.println("Reserva no encontrada");
			return;
		}
		if(reserva.cancelar())
		{
			notificador.notificar("La reserva "+codigo+" fue cancelada");
		}
	}

	public void checkIn(int codigoReserva)
	{
		Reserva reserva = reservas.get(codigoReserva);
		if(reserva == null || reserva.isCancelada())
		{
			System.out.println("No se puede realizar el check-in");
			return;
		}
		reserva.getHabitacion().setOcupada(true);
		reserva.setCheckIn(true);
		System.out.println("Habitación "+reserva.getHabitacion().getNumero()+" asignada a "+reserva.getCliente().getNombre());
	}

	public void checkOut(int numeroHabitacion)
	{
		Habitacion h = habitaciones.get(numeroHabitacion);
		if(h == null)
		{
			System.out.println("Habitación no encontrada");
			return;
		}
		h.setOcupada(false);
		System.out.println("Habitación "+numeroHabitacion+" liberada");
	}

	public void consultarHistorialCliente(int codigoCliente)
	{
		Cliente cliente = clientes.get(codigoCliente);
		if(cliente == null)
		{
			System.out.println("Cliente no encontrado");
			return;
		}
		for(Reserva reserva: cliente.getHistorial())
		{
			System.out.println("Reserva "+reserva.getCodigo()+" - habitación "+reserva.getHabitacion().getNumero()+" - "+reserva.getPrecio());
		}
	}

	public void asignarLimpieza(int codigoPersonal, int numeroHabitacion)
	{
		PersonalLimpieza trabajador = personal.get(codigoPersonal);
		Habitacion h = habitaciones.get(numeroHabitacion);
		if(trabajador == null || h == null)
		{
			System.out.println("No se encontró el personal o la habitación");
			return;
		}
		trabajador.asignarHabitacion(numeroHabitacion);
		System.out.println(trabajador.getNombre()+" fue asignado a la habitación "+numeroHabitacion);
	}

	public void consultarCargaPersonal(int codigoPersonal)
	{
		PersonalLimpieza trabajador = personal.get(codigoPersonal);
		if(trabajador == null)
		{
			System.out.println("Personal no encontrado");
			return;
		}
		System.out.println(trabajador.getNombre()+" tiene "+trabajador.getCargaTrabajo()+" habitaciones asignadas");
	}

	public void solicitarLimpieza(int numeroHabitacion)
	{
		Habitacion h = habitaciones.get(numeroHabitacion);
		if(h instanceof ServicioLimpieza)
		{
			((ServicioLimpieza)h).solicitarLimpieza();
			return;
		}
		System.out.println("La habitación no cuenta con servicio de limpieza");
	}

	public void solicitarComida(int numeroHabitacion)
	{
		Habitacion h = habitaciones.get(numeroHabitacion);
		if(h instanceof ServicioComida)
		{
			((ServicioComida)h).solicitarComida();
			return;
		}
		System.out.println("La habitación no cuenta con servicio de comida");
	}

	public void solicitarLavanderia(int numeroHabitacion)
	{
		Habitacion h = habitaciones.get(numeroHabitacion);
		if(h instanceof ServicioLavanderia)
		{
			((ServicioLavanderia)h).solicitarLavanderia();
			return;
		}
		System.out.println("La habitación no cuenta con servicio de lavandería");
	}

	public void generarInformeOcupacion(LocalDate inicio, LocalDate fin)
	{
		informes.generarOcupacion(habitaciones.values(), inicio, fin);
	}

	public void generarInformeIngresos(LocalDate inicio, LocalDate fin)
	{
		informes.generarIngresos(reservas.values(), inicio, fin);
	}
}
