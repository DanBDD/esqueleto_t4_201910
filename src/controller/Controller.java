package controller;

import java.util.Scanner;
import model.data_structures.*;
import model.vo.VOMovingViolation;
import model.logic.Sort;
import view.MovingViolationsManagerView;

@SuppressWarnings("unused")
public class Controller {

	private MovingViolationsManagerView view;
	
	// TODO Definir las estructuras de datos para cargar las infracciones del periodo definido
	
	// Muestra obtenida de los datos cargados 
	Comparable<VOMovingViolation> [ ] muestra;

	// Copia de la muestra de datos a ordenar 
	Comparable<VOMovingViolation> [ ] muestraCopia;

	public Controller() {
		view = new MovingViolationsManagerView();
		
		//TODO, inicializar las estructuras de datos para la carga de informacion de archivos
	}
	
	public void run() {
		long startTime;
		long endTime;
		long duration;
		
		int nDatos = 0;
		int nMuestra = 0;
		
		Scanner sc = new Scanner(System.in);
		boolean fin = false;
		
		while(!fin)
		{
			view.printMenu();
			
			int option = sc.nextInt();
			
			switch(option)
			{
				case 1:
					// Cargar infracciones
					nDatos = this.loadMovingViolations();
					view.printMensage("Numero infracciones cargadas:" + nDatos);
					break;
					
				case 2:
					// Obtener muestra de infracciones a ordenar
					view.printMensage("Dar tamaNo de la muestra: ");
					nMuestra = sc.nextInt();
					muestra = this.obtenerMuestra( nMuestra );
					view.printMensage("Muestra generada");
					break;
					
				case 3:
					// Mostrar los datos de la muestra actual (original)
					if ( nMuestra > 0 && muestra != null && muestra.length == nMuestra )
					{    
						view.printDatosMuestra( nMuestra, muestra);
					}
					break;

				case 4:
					// Aplicar ShellSort a una copia de la muestra
					if ( nMuestra > 0 && muestra != null && muestra.length == nMuestra )
					{
						muestraCopia = this.obtenerCopia(muestra);
						startTime = System.currentTimeMillis();
						this.ordenarShellSort(muestraCopia);
						endTime = System.currentTimeMillis();
						duration = endTime - startTime;
						view.printMensage("Tiempo de ordenamiento ShellSort: " + duration + " milisegundos");
					}
					break;
					
				case 5:
					// Aplicar MergeSort a una copia de la muestra
					if ( nMuestra > 0 && muestra != null && muestra.length == nMuestra )
					{
						muestraCopia = this.obtenerCopia(muestra);
						startTime = System.currentTimeMillis();
						this.ordenarMergeSort(muestraCopia);
						endTime = System.currentTimeMillis();
						duration = endTime - startTime;
						view.printMensage("Tiempo de ordenamiento MergeSort: " + duration + " milisegundos");
					}
					break;
											
				case 6:
					// Aplicar QuickSort a una copia de la muestra
					if ( nMuestra > 0 && muestra != null && muestra.length == nMuestra )
					{
						muestraCopia = this.obtenerCopia(muestra);
						startTime = System.currentTimeMillis();
						this.ordenarQuickSort(muestraCopia);
						endTime = System.currentTimeMillis();
						duration = endTime - startTime;
						view.printMensage("Tiempo de ordenamiento QuickSort: " + duration + " milisegundos");
					}
					break;
											
				case 7:
					// Mostrar los datos de la muestra ordenada (muestra copia)
					if ( nMuestra > 0 && muestraCopia != null && muestraCopia.length == nMuestra )
					{    view.printDatosMuestra( nMuestra, muestraCopia);    }
					break;
					
				case 8:	
					// Una muestra ordenada se convierte en la muestra a ordenar
					if ( nMuestra > 0 && muestraCopia != null && muestraCopia.length == nMuestra )
					{    
						muestra = muestraCopia;
						view.printMensage("La muestra ordenada es ahora la muestra de datos a ordenar");
					}
					break;

				case 9:
					// Invertir la muestra a ordenar
					if ( nMuestra > 0 && muestra != null && muestra.length == nMuestra )
					{    
						this.invertirMuestra(muestra);
						view.printMensage("La muestra de datos fue invertida");
					}
					break;
					
				case 10:	
					fin=true;
					sc.close();
					break;
			}
		}
	}

	
	/**
	 * Leer los datos de las infracciones de los archivos.
	 * Todas infracciones (MovingViolation) deben almacenarse en una Estructura de Datos (sin tener algun orden en particular)
	 * A partir de estos datos se obtendran muestras para evaluar los algoritmos de ordenamiento
	 * @return numero de infracciones leidas 
	 */
	public int loadMovingViolations() {
		// TODO implementar
		
		return 0;
	}
	
	/**
	 * Obtener una muestra aleatoria de tamaNo n de los datos leidos 
	 * @param n tamaNo de la muestra, n > 0
	 * @return muestra generada
	 */
	public Comparable<VOMovingViolation> [ ] obtenerMuestra( int n )
	{
		muestra = new Comparable[ n ];
					
		// TODO Llenar la muestra aleatoria con los datos leidos
		
		return muestra;
		
	}
	
	/**
	 * Generar una copia de una muestra. Se genera un nuevo arreglo con los mismos elementos.
	 * @param muestra - datos de la muestra original
	 * @return copia de la muestra
	 */
	public Comparable<VOMovingViolation> [ ] obtenerCopia( Comparable<VOMovingViolation> [ ] muestra)
	{
		Comparable<VOMovingViolation> [ ] copia = new Comparable[ muestra.length ]; 
		for ( int i = 0; i < muestra.length; i++)
		{    copia[i] = muestra[i];    }
		return copia;
	}
	
	/**
	 * Ordenar datos aplicando el algoritmo ShellSort
	 * @param datos - conjunto de datos a ordenar (inicio) y conjunto de datos ordenados (final)
	 */
	public void ordenarShellSort( Comparable<VOMovingViolation>[ ] datos ) {
		
		Sort.ordenarShellSort(datos);
	}
	
	/**
	 * Ordenar datos aplicando el algoritmo MergeSort
	 * @param datos - conjunto de datos a ordenar (inicio) y conjunto de datos ordenados (final)
	 */
	public void ordenarMergeSort( Comparable<VOMovingViolation>[ ] datos ) {

		Sort.ordenarMergeSort(datos);
	}

	/**
	 * Ordenar datos aplicando el algoritmo QuickSort
	 * @param datos - conjunto de datos a ordenar (inicio) y conjunto de datos ordenados (final)
	 */
	public void ordenarQuickSort( Comparable<VOMovingViolation>[ ] datos ) {

		Sort.ordenarQuickSort(datos);
	}

	/**
	 * Invertir una muestra de datos (in place).
	 * datos[0] y datos[N-1] se intercambian, datos[1] y datos[N-2] se intercambian, datos[2] y datos[N-3] se intercambian, ...
	 * @param datos - conjunto de datos a invertir (inicio) y conjunto de datos invertidos (final)
	 */
	public void invertirMuestra( Comparable[ ] datos ) {

		// TODO implementar
	}

}
