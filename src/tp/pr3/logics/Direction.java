package tp.pr3.logics;

public enum Direction {
	UP, DOWN, RIGHT, LEFT;

	public static String externaliseAll () {
		String s = "";
		for (Direction dir : Direction.values())
			s = s + " " + dir + ",";
		return s.substring(1, s. length()-1);
	}
}