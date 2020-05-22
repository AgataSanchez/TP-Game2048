package tp.pr3;

import java.util.Scanner;
import java.util.Random;

import tp.pr3.control.Controller;
import tp.pr3.exception.OtherExceptions;
import tp.pr3.logic.multigames.*;
import tp.pr3.logics.GameType;



public class Game2048 {


	public static void main(String[] args) {
		long seed;
		try {
			if(args.length>1 && args.length<4) {
				int dim = Integer.parseInt(args[0]);
				int init = Integer.parseInt(args[1]);
				if(args.length==3)//Si hay 3 argumentos
					seed=Long.parseLong(args[2]);
				else
					seed = new Random().nextInt(1000);//SI solo hay dos argumentos
				Scanner sc= new Scanner(System.in);
				Controller controller = new Controller(new Game(GameType.ORIG, seed, dim, init), sc);
				controller.run();
			}
			else
				throw new OtherExceptions("ussage: Game <size> <initCells> [<seed>]");
		}
		catch(NumberFormatException e) {
			System.out.println("The command-line arguments must be numbers: " + e.getMessage());
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Error en el indice del Array(fuera de rango): "+ e.getMessage());
		}
		catch(NegativeArraySizeException e) {
			System.out.println("Error en un numero negativo.");
		}
		catch(OtherExceptions e) {
			e.getMessage();
		}

	}
}
