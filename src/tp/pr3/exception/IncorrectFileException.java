package tp.pr3.exception;

public class IncorrectFileException extends Exception {
	public IncorrectFileException(String mensaje){
		System.out.println(mensaje);
	}
}
