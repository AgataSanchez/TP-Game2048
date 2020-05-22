package tp.pr3.util;

import java.util.Random;

public class ArrayAsList {
	private int  cont;
	private Object [] lista;
	private int tam;
	
	public ArrayAsList(int size) {//Constructora
		cont=0;
		tam=size*size;
		lista= new Object[tam];
	}
	
	public ArrayAsList(ArrayAsList b) {
		this.lista = b.lista;
		this.cont = b.cont;
		this.tam = b.tam;
	}
	
	public int length() {
		return cont;
	}
	
	public boolean llena() {
		return cont==tam;
	}
	
	public boolean vacia() {
		return cont==0;
	}
	
	public void insertar(Object pos) {
		if(!llena()) {
		lista[cont]=pos;
		this.cont++;	
		}
	}
	
	public Object recuperar(int i) {
		return lista[i];
	}
	

	public static void shuffle(ArrayAsList list, Random random) {
		for (int i = list.length(); i > 1; i--) {
			swap(list.lista, i - 1, random.nextInt(i));
		}
	}


	public static Object choice(ArrayAsList list, Random random) {
		return list.recuperar(random.nextInt(list.length()));
	}
	
	
	private static void swap(Object[] anArray, int i, int j) {
		Object temp = anArray[i];
		anArray[i] = anArray[j];
		anArray[j] = temp;
	}
}