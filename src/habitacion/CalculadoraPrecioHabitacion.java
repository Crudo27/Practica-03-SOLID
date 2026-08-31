package habitacion;

import java.time.LocalDate;
import java.util.List;

import clientes.Cliente;
import promociones.Promocion;
import promociones.Temporada;

public class CalculadoraPrecioHabitacion {
	double calcularPrecio(Habitacion h, List<LocalDate> fechas, Temporada temporada, Promocion promocion, Cliente cliente)
	{
		double precio = 0;
		for(LocalDate fecha: fechas)
		{
			double precioDia = h.precio*temporada.getFactor();
			if(promocion != null && promocion.aplica(cliente, fecha))
			{
				precioDia -= precioDia*promocion.getDescuento()/100;
			}
			precio += precioDia;
		}
		return precio;
	}
}
