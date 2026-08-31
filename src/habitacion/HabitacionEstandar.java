package habitacion;

import servicios.ServicioLimpieza;

public class HabitacionEstandar extends Habitacion implements ServicioLimpieza {
	public HabitacionEstandar(int numero, String descripcion, double precio)
	{
		super(numero, descripcion, precio);
	}

	@Override
	public String getTipo()
	{
		return "Estándar";
	}

	@Override
	public void solicitarLimpieza()
	{
		System.out.println("Se solicitó limpieza para la habitación "+numero);
	}
}
