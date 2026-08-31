package app;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import clientes.Cliente;
import controlador.ControladorHotel;
import habitacion.Habitacion;
import habitacion.HabitacionEstandar;
import habitacion.HabitacionPremium;
import habitacion.HabitacionSuite;
import habitacion.Reserva;
import notificaciones.EnviadorCorreo;
import notificaciones.EnviadorSMS;
import notificaciones.NotificadorReserva;
import personal.PersonalLimpieza;
import politicas.PoliticaCancelacionEstricta;
import politicas.PoliticaCancelacionFlexible;
import politicas.PoliticaCancelacionModerada;
import promociones.PromocionClienteFrecuente;
import promociones.PromocionFechas;
import promociones.Temporada;

public class Main {
	public static void main(String[] args)
	{
		NotificadorReserva notificador = new NotificadorReserva(new EnviadorCorreo());
		ControladorHotel hotel = new ControladorHotel(notificador);

		Habitacion h1 = new HabitacionEstandar(101, "Habitación individual", 120);
		Habitacion h2 = new HabitacionSuite(201, "Suite con sala", 220);
		Habitacion h3 = new HabitacionPremium(301, "Habitación premium", 350);
		hotel.registrarHabitacion(h1);
		hotel.registrarHabitacion(h2);
		hotel.registrarHabitacion(h3);

		Cliente c1 = new Cliente(1, "Ana Torres", "ana@gmail.com", true);
		Cliente c2 = new Cliente(2, "Luis Medina", "999888777", false);
		hotel.registrarCliente(c1);
		hotel.registrarCliente(c2);

		PersonalLimpieza p1 = new PersonalLimpieza(1, "Rosa Quispe");
		hotel.registrarPersonal(p1);

		Temporada normal = new Temporada(1);
		Temporada alta = new Temporada(1.3);
		PromocionFechas promocionFinSemana = new PromocionFechas(20);
		promocionFinSemana.addFecha(LocalDate.now().plusDays(5));
		PromocionClienteFrecuente promocionFrecuente = new PromocionClienteFrecuente(15);

		List<LocalDate> fechas1 = new ArrayList<>();
		fechas1.add(LocalDate.now().plusDays(5));
		fechas1.add(LocalDate.now().plusDays(6));
		Reserva r1 = hotel.reservar(101, 1, fechas1, normal, promocionFinSemana, new PoliticaCancelacionFlexible());

		List<LocalDate> fechas2 = new ArrayList<>();
		fechas2.add(LocalDate.now().plusDays(10));
		fechas2.add(LocalDate.now().plusDays(11));
		Reserva r2 = hotel.reservar(201, 1, fechas2, alta, null, new PoliticaCancelacionModerada());

		List<LocalDate> fechas3 = new ArrayList<>();
		fechas3.add(LocalDate.now().plusDays(15));
		Reserva r3 = hotel.reservar(301, 1, fechas3, normal, promocionFrecuente, new PoliticaCancelacionEstricta());

		List<LocalDate> fechas4 = new ArrayList<>();
		fechas4.add(LocalDate.now().plusDays(5));
		hotel.reservar(101, 2, fechas4, normal, null, new PoliticaCancelacionFlexible());

		hotel.consultarHistorialCliente(1);
		hotel.asignarLimpieza(1, 201);
		hotel.consultarCargaPersonal(1);
		hotel.solicitarLimpieza(101);
		hotel.solicitarComida(201);
		hotel.solicitarLavanderia(301);
		hotel.generarInformeOcupacion(LocalDate.now(), LocalDate.now().plusDays(20));
		hotel.generarInformeIngresos(LocalDate.now(), LocalDate.now().plusDays(20));

		if(r1 != null)
		{
			hotel.checkIn(r1.getCodigo());
			hotel.checkOut(101);
			hotel.cancelar(r1.getCodigo());
		}
		if(r2 != null)
		{
			hotel.cancelar(r2.getCodigo());
		}
		if(r3 != null)
		{
			hotel.cancelar(r3.getCodigo());
		}

		NotificadorReserva sms = new NotificadorReserva(new EnviadorSMS());
		sms.notificar("Canal SMS disponible para nuevas reservas");
	}
}
