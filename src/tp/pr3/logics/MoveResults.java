package tp.pr3.logics;

public class MoveResults {

	private boolean moved; //identificar si ha habido movimiento
	private int points;//Almacena el numero de puntos obtenidos en el movimiento
	
	public MoveResults() {
		moved=false;
		points=0;

	}
	
	public void setMoved(boolean moved) {
		this.moved = moved;
	}

	public void setPoints(int points) {
		this.points = points;
	}


	
	public boolean isMoved() {
		return moved;
	}

	public int getPoints() {
		return points;
	}

}
