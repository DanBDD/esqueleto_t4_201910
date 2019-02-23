package model.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.util.Sort;

public class SortTest {

	// Muestra de datos a ordenar
		private Comparable[] datos;

		protected static final int[] ARREGLO = {350, 383, 105, 233, 140, 266, 356, 236, 80, 360, 221, 241, 130, 244, 352, 446, 18, 98, 97, 396};


		@Before
		public void setUp() throws Exception{
			datos=new Comparable[ARREGLO.length];
			int pos=0;
			for(int a:ARREGLO)
			{
				datos[pos]=a;
				pos++;
			}
		}
		public void estaOrdenado(Comparable [] a)
		{
			boolean orden=true;
			for(int i=0;i+1<a.length && orden;i++)
			{
				if(a[i].compareTo(a[i+1])>0)
				{
					orden=false;
				}
				System.out.println(a[i]);
			}
			if(orden==false)
			{
				fail("El arreglo no esta ordenado");
			}
		}
		@Test
		public void testMerge() {
			try {
				setUp();
				Sort.ordenarMergeSort(datos);
				estaOrdenado(datos);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		@Test
		public void testQuick() {
			try {
				setUp();
				Sort.ordenarQuickSort(datos);
				estaOrdenado(datos);	
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		@Test
		public void testShell() {
			try {
				setUp();
				Sort.ordenarShellSort(datos);
				estaOrdenado(datos);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

}
