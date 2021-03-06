package model.data_structures;

import java.util.Iterator;

public class Pila<T extends Comparable<T>> implements IStack<T> {

	private Nodo<T> primerNodo ;

	private int numElementos;

	public Pila()
	{
		primerNodo=null;
	}
	@Override
	public Iterator<T> iterator() {

		return new Iterador<T>(primerNodo);
	}

	@Override
	public boolean isEmpty() {
		if(numElementos == 0){
			return true;
		}
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return numElementos;
	}

	@Override
	public void push(T t) {
		// TODO Auto-generated method stub
		if(primerNodo==null){
			primerNodo = new Nodo<T>(t);
		}
		else 
		{
			Nodo<T> p = new Nodo<T>(t);
			p.cambiarSiguiente(primerNodo);
			primerNodo=p;
		}
		numElementos++;
	}

	@Override
	public T pop() {
		T elem = primerNodo.darElem();
		primerNodo=primerNodo.darSiguiente();
		numElementos--;
		return elem;
	}

	public T get(int i){
		Iterator<T> it = this.iterator();
		int cont = 0;
		T objeto = null;
		while(cont<i){
			objeto = it.next();
			cont++;
		}
		return objeto;
	}

}
