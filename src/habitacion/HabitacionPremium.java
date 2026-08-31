package habitacion;

import servicios.ServicioComida;
import servicios.ServicioLavanderia;
import servicios.ServicioLimpieza;

public class HabitacionPremium extends Habitacion implements ServicioLimpieza, ServicioComida, ServicioLavanderia {
	public HabitacionPremium(int numero, String descripcion, double precio)
	{
		super(numero, descripcion, precio);
	}

	@Override
	public String getTipo()
	{
		return "Premium";
	}

	@Override
	public void solicitarLimpieza()
	{
		System.out.println("Se solicitó limpieza para la habitación premium "+numero);
	}

	@Override
	public void solicitarComida()
	{
		System.out.println("Se solicitó comida para la habitación premium "+numero);
	}

	@Override
	public void solicitarLavanderia()
	{
		System.out.println("Se solicitó lavandería para la habitación premium "+numero);
	}
}
