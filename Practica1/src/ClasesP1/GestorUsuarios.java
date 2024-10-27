package ClasesP1;

import java.util.ArrayList;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GestorUsuarios 
{
	//Lista para almacenar los usuarios registrados
	private List<Jugador> usuarios;

	//Constructor que inicializa la lista de usuarios
	public GestorUsuarios()
	{
		this.usuarios = new ArrayList<>();
	}

	//Método para dar de alta a un usuario
	public boolean darDeAlta(Jugador nuevoJugador)
	{
		//Comprobar si el correo esta ya registrado
		for(Jugador jugador : usuarios)
		{
			if(jugador.getCorreo().equals(nuevoJugador.getCorreo()))
			{
				return false; //Usuario ya registrado
			}
		}

		//Añadir usuario si no esta registrado
		usuarios.add(nuevoJugador);
		return true; //Usuario registrado
	}

	//Método para modificar la informacion de un usuario
	public boolean modificarUsuario(String correo,String nuevoNombre,String nuevosApellidos,String nuevaFechaNacimiento)
	{
		for(Jugador jugador : usuarios)
		{
			if(jugador.getCorreo().equals(correo))
			{
				jugador.setNombre(nuevoNombre);
				jugador.setApellidos(nuevosApellidos);

				//Conversion de la nueva fecha de nacimiento a un objeto tipo Date
				try
				{
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					Date fechaNacimiento = dateFormat.parse(nuevaFechaNacimiento);
					jugador.setFechaNacimiento(fechaNacimiento);
					return true; //Modificación correcta
				}
				catch(ParseException ex)
				{
					System.out.println("Error: Formato de fecha inválido. Use el formato dd/MM/yyyy");
				}
			}
		}
		return false; //Usuario no encontrado
	}

	//Método para listar los usuarios registrados
	public void listarUsuarios()
	{
		for(Jugador jugador : usuarios)
		{
			System.out.println(jugador.toString());
		}
	}
}