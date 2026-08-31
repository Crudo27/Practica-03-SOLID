package personal;

import java.util.ArrayList;
import java.util.List;

public class PersonalLimpieza {
	private int codigo;
	private String nombre;
	private List<Integer> habitaciones = new ArrayList<>();

	public PersonalLimpieza(int codigo, String nombre)
	{
		this.codigo = codigo;
		this.nombre = nombre;
	}

	public int getCodigo()
	{
		return codigo;
	}

	public String getNombre()
	{
		return nombre;
	}

	public List<Integer> getHabitaciones()
	{
		return habitaciones;
	}

	public void asignarHabitacion(int numero)
	{
		if(!habitaciones.contains(numero))
		{
			habitaciones.add(numero);
		}
	}

	public int getCargaTrabajo()
	{
		return habitaciones.size();
	}
}
