package Trab1Gb;

public class CalculaT<T> {

	// Arrays
	protected T[] arrayAscendente;
	protected T[] arrayDescendente;
	protected T[] arrayAleatorio;
	protected T[] arrayAleatorioRep;

	/**
	 * Metódos para instanciar os cenários
	 */
	// Instancia os 4 cenários

	// Ascendente: no array terá chaves únicas em ordem crescente
	public void ascendente(int tamArray) {
		arrayAscendente = (T[]) new Comparable[tamArray];
		for (Integer i = 0; i < arrayAscendente.length; i++) {
			arrayAscendente[i] = (T) i;
		}
	}

	// Descendente: no array terá chaves únicas em ordem decrescente
	public void descendente(int tamArray) {
		arrayDescendente = (T[]) new Comparable[tamArray];
		for (Integer i = 0, j = tamArray - 1; i < arrayDescendente.length; i++, j--) {
			arrayDescendente[i] = (T) j;
		}
	}

	// Aleatória: no array terá chaves únicas distribuídas aleatoriamente
	public T[] aleatorio(int tamArray) {
		T[] array = (T[]) new Comparable[tamArray];
		T aux;
		for (Integer i = 0; i < array.length; i++) {
			array[i] = (T) i;
		}

		for (int i = 0; i < array.length; i++) {
			aux = array[i];
			Integer pos = (int) Math.floor(Math.random() * ((array.length - 1) - i) + 1);
			if (!search(array[pos], array, i)) {
				array[i] = array[pos];
				array[pos] = aux;
			}
		}
		imprime(array);
		arrayAleatorio = array;
		return arrayAleatorio;

	}

	// Aleátoria repetida
	public void aleatorioRep(int tamArray) {
		T[] aux = aleatorio(tamArray);
		arrayAleatorioRep = (T[]) new Comparable[tamArray];
		int cont = 0;
		int quantV = 0;
		int proporcao = proporcao(tamArray);
		for (int i = 0; i < arrayAleatorioRep.length; i++) {
			if (arrayAleatorioRep[i] == null) {
				arrayAleatorioRep[i] = aux[i];
				if ((((cont * 7) / 3) % 2 != 0) && quantV < proporcao) {
					arrayAleatorioRep[i + 1] = arrayAleatorioRep[i];
					quantV++;
					cont = 0;
				}
			}
			cont++;
		}

	}

	public boolean search(T element, T[] array, int indice) {
		for (int i = 0; i < indice; i++) {
			if (element.equals(array[i])) {
				return true;
			}
		}
		return false;
	}

	/*
	 * Metódos auxiliares
	 */
	// Imprimir array
	public void imprime(T[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}

	}

	// Verifica se tem repetição
	public void repetido(T[] a) {
		int cont = 0;
		for (int i = 0; i < a.length - 1; i++) {
			if (a[i].equals(a[i + 1])) {
				System.out.println("Valor " + i + ": " + a[i]);
				cont++;
			}
		}
		System.out.println();
		System.out.println("Total de repetido: " + cont);
	}

	// Calcula 5% de n
	private int proporcao(int n) {
		return (int) (n * 0.05);
	}

	/*
	 * Algoritimos de ordenação
	 */
	// Exchange
	private <T extends Comparable<? super T>> void exchange(T[] a, int i, int j) {
		T tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	// BubbleSort
	public <T extends Comparable<? super T>> double bubbleSort(T[] a) {
		double inicio = System.nanoTime();
		boolean exchange;
		do {
			exchange = false;
			for (int i = 0; i < a.length - 1; i++) {
				if (a[i].compareTo(a[i + 1]) > 0) {
					exchange(a, i, i + 1);
					exchange = true;
				}
			}
		} while (exchange);

		double fim = System.nanoTime() - inicio;
		return fim;

	}

	// InsertionSort
	public <T extends Comparable<? super T>> double insertionSort(T[] a) {
		double inicio = System.nanoTime();
		for (int i = 1; i < a.length; i++) {
			for (int j = i; j > 0 && a[j - 1].compareTo(a[j]) > 0; j--) {
				exchange(a, j - 1, j);
			}
		}
		double fim = System.nanoTime() - inicio;
		return fim;

	}

	// SelectionSort
	public <T extends Comparable<? super T>> double selectionSort(T[] a) {
		double inicio = System.nanoTime();
		for (int min, i = 0; i < a.length; i++) {
			min = i;
			for (int j = i + 1; j < a.length; j++) {
				if (a[j].compareTo(a[min]) < 0) {
					min = j;
				}
			}
			exchange(a, min, i);
		}
		double fim = System.nanoTime() - inicio;
		return fim;
	}

	// HeapSort
	public <T extends Comparable<? super T>> double heapSort(T[] a) {
		double inicio = System.nanoTime();
		buildMaxHeap(a);
		for (int i = a.length - 1; i > 0; i--) {
			exchange(a, 0, i);
			maxHeapify(a, 0, i);
		}
		double fim = System.nanoTime() - inicio;
		return fim;
	}

	private <T extends Comparable<? super T>> void buildMaxHeap(T[] a) {
		for (int i = a.length / 2 - 1; i >= 0; i--) {
			maxHeapify(a, i, a.length);
		}
	}

	private <T extends Comparable<? super T>> void maxHeapify(T[] a, int i, int n) {
		int max = 2 * i + 1;
		if (max + 1 < n && a[max].compareTo(a[max + 1]) < 0) {
			max++;
		}
		if (max < n && a[max].compareTo(a[i]) > 0) {
			exchange(a, i, max);
			maxHeapify(a, max, n);
		}
	}

	// ShellSort
	public <T extends Comparable<? super T>> double shellSort(T[] a) {
		double inicio = System.nanoTime();
		int h = 1;
		while (3 * h + 1 < a.length) {
			h = 3 * h + 1;
		}
		while (h > 0) {
			for (int i = h; i < a.length; i++) {
				for (int j = i; j >= h && a[j - h].compareTo(a[j]) > 0; j -= h) {
					exchange(a, j - h, j);
				}
			}
			h /= 3;
		}
		double fim = System.nanoTime() - inicio;
		return fim;
	}

	// MergeSort
	public <T extends Comparable<? super T>> double mergeSort(T[] a) {
		double inicio = System.nanoTime();
		T[] aux = (T[]) new Comparable[a.length];
		divide(a, aux, 0, a.length - 1);
		double fim = System.nanoTime() - inicio;
		return fim;
	}

	private <T extends Comparable<? super T>> void divide(T[] a, T[] aux, int low, int high) {
		if (low >= high) {
			return;
		}

		int middle = (low + high) / 2;
		divide(a, aux, low, middle);
		divide(a, aux, middle + 1, high);
		conquer(a, aux, low, middle, high);
	}

	private <T extends Comparable<? super T>> void conquer(T[] a, T[] aux, int low, int middle, int high) {
		for (int k = low; k <= high; k++) {
			aux[k] = a[k];
		}
		int i = low, j = middle + 1;
		for (int k = low; k <= high; k++) {
			if (i > middle) {
				a[k] = aux[j++];
			} else if (j > high) {
				a[k] = aux[i++];
			} else if (aux[j].compareTo(aux[i]) < 0) {
				a[k] = aux[j++];
			} else {
				a[k] = aux[i++];
			}
		}
	}

	public <T extends Comparable<? super T>> void mergeSortBottomUp(T[] a) {
		int n = a.length;
		T[] aux = (T[]) new Comparable[n];
		for (int len = 1; len < n; len *= 2) {
			for (int low = 0; low < n - len; low += len + len) {
				int middle = low + len - 1;
				int high = Math.min(low + len + len - 1, n - 1);
				conquer(a, aux, low, middle, high);
			}
		}
	}

	public <T extends Comparable<? super T>> double quickSort(T[] a) {
		double inicio = System.nanoTime();
		sort(a, 0, a.length - 1);
		double fim= System.nanoTime() - inicio;
		System.out.println(fim);
		return fim;
	}

	private <T extends Comparable<? super T>> void sort(T[] a, int low, int high) {
		if (high <= low)
			return;

		if (a[high].compareTo(a[low]) < 0)
			exchange(a, low, high);

		int less = low + 1, greater = high - 1;
		int i = low + 1;
		while (i <= greater) {
			if (a[i].compareTo(a[low]) < 0)
				exchange(a, less++, i++);
			else if (a[high].compareTo(a[i]) < 0)
				exchange(a, i, greater--);
			else
				i++;
		}
		exchange(a, low, --less);
		exchange(a, high, ++greater);

		sort(a, low, less - 1);
		if (a[less].compareTo(a[greater]) < 0)
			sort(a, less + 1, greater - 1);
		sort(a, greater + 1, high);
	}

	// QuickSort single pivot
	/*
	 * public <T extends Comparable<? super T>> double quickSort(T[] a) { double inicio
	 * = System.nanoTime();
	 * 
	 * sort(a, 0, a.length - 1);
	 * 
	 * double fim = System.nanoTime() - inicio; return fim; }
	 * 
	 * private <T extends Comparable<? super T>> void sort(T[] a, int low, int high)
	 * { if (low >= high) { return; } int p = partition(a, low, high); sort(a, low,
	 * p - 1); sort(a, p + 1, high); }
	 * 
	 * private <T extends Comparable<? super T>> int partition(T[] a, int low, int
	 * high) { T pivot = a[high]; int i = low - 1; for (int j = low; j < high; j++)
	 * { if (a[j].compareTo(pivot) <= 0) { exchange(a, ++i, j); } } exchange(a, i +
	 * 1, high); return i + 1; }
	 */

	/**
	 * Getters
	 *
	 * @return
	 */
	public T[] getArrayAscendente() {
		return arrayAscendente;
	}

	public T[] getArrayDescendente() {
		return arrayDescendente;
	}

	public T[] getArrayAleatorio() {
		return arrayAleatorio;
	}

	public T[] getArrayAleatorioRep() {
		return arrayAleatorioRep;
	}

}
