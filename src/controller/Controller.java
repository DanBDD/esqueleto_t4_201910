package controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import com.opencsv.CSVReader;

import model.data_structures.*;
import model.util.Sort;
import model.vo.VOMovingViolation;
import view.MovingViolationsManagerView;

public class Controller {
	private static final String enero = "./data/Moving_Violations_Issued_in_January_2018-2.csv";
	private static final String febrero = "./data/Moving_Violations_Issued_in_February_2018.csv";
	private static final String marzo = "./data/Moving_Violations_Issued_in_March_2018.csv";


	private MovingViolationsManagerView view;

	// TODO Definir las estructuras de datos para cargar las infracciones del periodo definido
	private Cola<VOMovingViolation> queue;
	private Pila<VOMovingViolation> stack;
	// Muestra obtenida de los datos cargados 
	Comparable<VOMovingViolation> [ ] muestra;

	// Copia de la muestra de datos a ordenar 
	Comparable<VOMovingViolation> [ ] muestraCopia;

	public Controller() {
		view = new MovingViolationsManagerView();

		//TODO inicializar las estructuras de datos para la carga de informacion de archivos
		queue=new Cola<VOMovingViolation>();
		stack=new Pila<VOMovingViolation>();
	}

	/**
	 * Leer los datos de las infracciones de los archivos. Cada infraccion debe ser Comparable para ser usada en los ordenamientos.
	 * Todas infracciones (MovingViolation) deben almacenarse en una Estructura de Datos (en el mismo orden como estan los archivos)
	 * A partir de estos datos se obtendran muestras para evaluar los algoritmos de ordenamiento
	 * @return numero de infracciones leidas 
	 */
	public int loadMovingViolations() {
		// TODO Los datos de los archivos deben guardarse en la Estructura de Datos definida
		int contador=0;
		try {
			CSVReader lector = new CSVReader(new FileReader(enero));
			String[] nextLineR1 = lector.readNext();
			while((nextLineR1 = lector.readNext()) != null){
				String id = nextLineR1[0];
				int idObjeto = Integer.parseInt(id);
				String location = nextLineR1[2];
				String fecha = nextLineR1[13];
				String fechaFormato = formatearFecha(fecha);
				String total = nextLineR1[8];
				int totalObjeto = Integer.parseInt(total);
				String indicator = nextLineR1[12];
				String description = nextLineR1[15];
				queue.enqueue(new VOMovingViolation(idObjeto, location, fechaFormato, totalObjeto, indicator, description));
				stack.push(new VOMovingViolation(idObjeto, location, fecha, totalObjeto, indicator, description));
				contador++;
			}
			lector.close();
			CSVReader lector2 = new CSVReader(new FileReader(febrero));
			String[] nextLineR2 = lector2.readNext();
			while((nextLineR2 = lector2.readNext()) != null){
				String id = nextLineR2[0];
				int idObjeto = Integer.parseInt(id);
				String location = nextLineR2[2];
				String fecha = nextLineR2[13];
				String fechaFormato = formatearFecha(fecha);
				String total = nextLineR2[8];
				int totalObjeto = Integer.parseInt(total);
				String indicator = nextLineR2[12];
				String description = nextLineR2[15];
				queue.enqueue(new VOMovingViolation(idObjeto, location, fechaFormato, totalObjeto, indicator, description));
				stack.push(new VOMovingViolation(idObjeto, location, fecha, totalObjeto, indicator, description));
				contador++;
			}
			lector2.close();
			CSVReader lector3 = new CSVReader(new FileReader(marzo));
			String[] nextLineR3 = lector3.readNext();
			while((nextLineR3 = lector3.readNext()) != null){
				String id = nextLineR3[0];
				int idObjeto = Integer.parseInt(id);
				String location = nextLineR3[2];
				String fecha = nextLineR3[13];
				String fechaFormato = formatearFecha(fecha);
				String total = nextLineR3[8];
				int totalObjeto = Integer.parseInt(total);
				String indicator = nextLineR3[12];
				String description = nextLineR3[15];
				queue.enqueue(new VOMovingViolation(idObjeto, location, fechaFormato, totalObjeto, indicator, description));
				stack.push(new VOMovingViolation(idObjeto, location, fecha, totalObjeto, indicator, description));
				contador++;
			}
			lector3.close();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return contador;
	}

	/**
	 * Generar una muestra aleatoria de tamaNo n de los datos leidos.
	 * Los datos de la muestra se obtienen de las infracciones guardadas en la Estructura de Datos.
	 * @param n tamaNo de la muestra, n > 0
	 * @return muestra generada
	 */
	public Comparable<VOMovingViolation> [ ] generarMuestra( int n )
	{
		muestra = new Comparable[ n ];

		// TODO Llenar la muestra aleatoria con los datos guardados en la estructura de datos
		for(int i=0;i<n;i++)
		{
			muestra[i]=queue.dequeue();
		}
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
		Pila<VOMovingViolation> s = null;
		for(int i=0;i<datos.length;i++)
		{
			s.push((VOMovingViolation) datos[i]);
		}
		for(int i=0;i<datos.length;i++)
		{
			datos[i]=s.pop();
		}
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
				// Generar muestra de infracciones a ordenar
				view.printMensage("Dar tamaNo de la muestra: ");
				nMuestra = sc.nextInt();
				muestra = this.generarMuestra( nMuestra );
				view.printMensage("Muestra generada");
				break;

			case 3:
				// Mostrar los datos de la muestra actual (original)
				if ( nMuestra > 0 && muestra != null && muestra.length == nMuestra )
				{    
					view.printDatosMuestra( nMuestra, muestra);
				}
				else
				{
					view.printMensage("Muestra invalida");
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
					view.printMensage("Ordenamiento generado en una copia de la muestra");
					view.printMensage("Tiempo de ordenamiento ShellSort: " + duration + " milisegundos");
				}
				else
				{
					view.printMensage("Muestra invalida");
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
					view.printMensage("Ordenamiento generado en una copia de la muestra");
					view.printMensage("Tiempo de ordenamiento MergeSort: " + duration + " milisegundos");
				}
				else
				{
					view.printMensage("Muestra invalida");
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
					view.printMensage("Ordenamiento generado en una copia de la muestra");
					view.printMensage("Tiempo de ordenamiento QuickSort: " + duration + " milisegundos");
				}
				else
				{
					view.printMensage("Muestra invalida");
				}
				break;

			case 7:
				// Mostrar los datos de la muestra ordenada (muestra copia)
				if ( nMuestra > 0 && muestraCopia != null && muestraCopia.length == nMuestra )
				{    view.printDatosMuestra( nMuestra, muestraCopia);    }
				else
				{
					view.printMensage("Muestra Ordenada invalida");
				}
				break;

			case 8:	
				// Una muestra ordenada se convierte en la muestra a ordenar
				if ( nMuestra > 0 && muestraCopia != null && muestraCopia.length == nMuestra )
				{    
					muestra = muestraCopia;
					view.printMensage("La muestra ordenada (copia) es ahora la muestra de datos a ordenar");
				}
				break;

			case 9:
				// Invertir la muestra a ordenar
				if ( nMuestra > 0 && muestra != null && muestra.length == nMuestra )
				{    
					this.invertirMuestra(muestra);
					view.printMensage("La muestra de datos a ordenar fue invertida");
				}
				else
				{
					view.printMensage("Muestra invalida");
				}

				break;

			case 10:	
				fin=true;
				sc.close();
				break;
			}
		}
	}
	public String formatearFecha(String pFecha){

		String fechaFormateada = pFecha.substring(0,10);

		return fechaFormateada;
	}

}
