package habitacion;

import servicios.ServicioComida;
import servicios.ServicioLimpieza;

public class HabitacionSuite extends Habitacion implements ServicioLimpieza, ServicioComida {
	public HabitacionSuite(int numero, String descripcion, double precio)
	{
		super(numero, descripcion, precio);
	}

	@Override
	public String getTipo()
	{
		return "Suite";
	}

	@Override
	public void solicitarLimpieza()
	{
		System.out.println("Se solicitó limpieza para la suite "+numero);
	}

	@Override
	public void solicitarComida()
	{
		System.out.println("Se solicitó comida para la suite "+numero);
	}
}
