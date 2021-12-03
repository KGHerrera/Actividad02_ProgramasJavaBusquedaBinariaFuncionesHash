import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

class AlgoritmosOrdenamiento{
	private static void quicksort(int a[], int primero, int ultimo)
	{
		 int i, j, central;
		 int pivote; 
		 central = (primero + ultimo)/2;
		 pivote = a[central];
		 i = primero;
		 j = ultimo;
		 do {
		 	 while (a[i] < pivote) i++;
		 	 while (a[j] > pivote) j--;
		 	 if (i <= j) {
		 		 // Se intercambian
		 		 int aux = a[i];
				 a[i] = a[j];
				 a[j] = aux;
		 	 	 i++;
		 	 	 j--;
		 	 }
		 } while (i <= j);
		 
		 if (primero < j)
		 	 quicksort(a, primero, j); // mismo proceso con sublista izqda
		 if (i < ultimo)
		 	 quicksort(a, i, ultimo); // mismo proceso con sublista drcha
	}
	
	public static void quicksort(int a[]) {
		quicksort(a, 0, a.length-1);
		
	}
}

class BusquedaBinaria{
	int pasadas = 0;
	int comparaciones = 0;
	
	public void ejecutar(int array[], int dato) {
		
		long tInicio = System.currentTimeMillis();
		
		binarySearch(array, dato, 0, array.length-1);
		
		long tFin = System.currentTimeMillis();
		System.out.println("Pasadas: " + pasadas);
		System.out.println("Comparaciones: " + comparaciones);
		System.out.println("Tiempo de ejecucion: " + (tFin-tInicio));
		pasadas = comparaciones = 0;

	}
	
	public boolean binarySearch(int[] data, int target, int low, int high) {
		pasadas++;
	if (low > high) {
		comparaciones++;
		return false;
	}else {
		comparaciones++;
		int mid = (low + high) / 2;
		if (target == data[mid]) {
			comparaciones++;
			return true;
		}else if (target < data[mid]) {
			comparaciones++;
			return binarySearch(data, target, low, mid - 1);
		}else {
			comparaciones++;
			return binarySearch(data, target, mid + 1, high);
		}
	}
	}
}

class Hash{
	String[] arreglo;
	int tamaño;
	int contador;
	int pasadas=0, comparaciones=0;

	public Hash(int tam) {
		tamaño = tam;
		arreglo = new String[tam];
		Arrays.fill(arreglo, "-1");
	}
	
	public void funcionHash(String[] cadArreglo, String[] arreglo) {
		int i;
		for (i = 0; i < cadArreglo.length; i++) {
			String elemento = cadArreglo[i];
			int indiceArreglo = Integer.parseInt(elemento) % 100;
			System.out.println("Indice: " + indiceArreglo + " para " + elemento);
			
			while (arreglo[indiceArreglo] != "-1") {
				indiceArreglo++;
				System.out.println("Colisión en el indice: " + (indiceArreglo - 1) + " cambiando por " + indiceArreglo);
				
				indiceArreglo %= tamaño; 
			}
			arreglo[indiceArreglo] = elemento;
		}
	}


	public void mostrar() {
		int incremento = 0;
		int j;

		for (int i = 0; i < 1; i++) {
			incremento += 100;
			System.out.println("");
			System.out.println("------------------------------------------------------------------");
			for (j = incremento - 100; j < incremento; j++) {
				System.out.format(" | %3s " + " ", j);
			}
			System.out.println(" | ");
			System.out.println();
			for (j = incremento - 100; j < incremento; j++) {
				if (arreglo[j].equals("-1")) {
					System.out.println(" | ");
				} else {
					System.out.print(String.format(" | %3s" + " ", arreglo[j]));
				}
			}

			System.out.println("|");
			System.out.println("------------------------------------------------------------------");
			System.out.println("");
		}
	}

	public String buscar(String elemento) {
		long tInicio = System.currentTimeMillis();
		
		String eleme = buscarClave(elemento);
		
        long tFin= System.currentTimeMillis();
		
		System.out.println("Pasadas: " + pasadas);
		System.out.println("Comparaciones: " + comparaciones);
		System.out.println("Tiempo de ejecucion: " + (tFin-tInicio));
		pasadas = comparaciones = 0;
		
		return eleme;
	}
	
	public String buscarClave(String elemento) {
		int indiceArrglo = Integer.parseInt(elemento) % 7;
		int contador = 0;
		
		
		while (arreglo[indiceArrglo] != "-1") {
			pasadas++;
			comparaciones++;
			if (arreglo[indiceArrglo].equals(elemento)) {
				comparaciones++;
				return arreglo[indiceArrglo];
			}
			indiceArrglo++;
			indiceArrglo %= tamaño;
			contador++;
			if (contador > 100) {
				System.out.print("Error");
				break;
			}
			comparaciones++;
		}
		
		return null;
	}
}
public class PruebaBusqueda {
	public static void main(String[] args) {
		int []vector = new int[100];

		BusquedaBinaria bb = new BusquedaBinaria();
		Random random = new Random();
		Scanner entrada = new Scanner(System.in);
		
		String vector2[] = new String[100];
		
		
		
		
		
		int opcion = 0;
		for (int i = 0; i < vector.length; i++) {
			vector[i] = random.nextInt(100) + 1;
		}
		
		for(int i=0; i < vector2.length; i++) {
			vector2[i] = String.valueOf(vector[i]);
		}
		
		Hash h1 = new Hash(vector2.length);
		h1.funcionHash(vector2, h1.arreglo);
		
		AlgoritmosOrdenamiento.quicksort(vector);
		System.out.println("\n" + Arrays.toString(vector));
		
		do {
			System.out.println("\nElige una opcion");
			System.out.println("1) Busqueda Binaria");
			System.out.println("2) Busqueda hash");
			System.out.println("3) Salir");
			System.out.println("Introduce opcion: ");
			opcion = entrada.nextInt();
			
			switch (opcion) {
			case 1:
				System.out.print("\nIntroduce numero a buscar: ");
				int numero = entrada.nextInt();
				bb.ejecutar(vector, numero);
				break;
			
			case 2:
				System.out.print("\nIntroduce numero a buscar: ");
				String elemento = entrada.next();
				System.out.println(h1.buscar(elemento));
				break;
				
			case 3:
				System.out.println("\nSaliendo");
				break;
			default:
				System.out.println("\nOpcion incorrecta");
				break;
			}
		} while (opcion != 3);
		
	}
}
