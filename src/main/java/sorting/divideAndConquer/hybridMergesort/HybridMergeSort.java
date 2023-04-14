package sorting.divideAndConquer.hybridMergesort;

import sorting.AbstractSorting;
import util.*;

import sorting.divideAndConquer.MergeSort;

/**
 * A classe HybridMergeSort representa a implementação de uma variação do
 * MergeSort que pode fazer uso do InsertionSort (um algoritmo híbrido) da
 * seguinte forma: o MergeSort é aplicado a entradas maiores a um determinado
 * limite. Caso a entrada tenha tamanho menor ou igual ao limite o algoritmo usa
 * o InsertionSort.
 * 
 * A implementação híbrida deve considerar os seguintes detalhes:
 * - Ter contadores das quantidades de MergeSorts e InsertionSorts aplicados, de forma
 *   que essa informação possa ser capturada pelo teste.
 * - A cada chamado do método de sort(T[] array) esses contadores são resetados. E a cada chamada
 *   interna de um merge ou insertion, os contadores MERGESORT_APPLICATIONS e
 *   INSERTIONSORT_APPLICATIONS são incrementados.
 * - O InsertionSort utilizado no algoritmo híbrido deve ser in-place.
 */
public class HybridMergeSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	/**
	 * For inputs with size less or equal to this value, the insertionsort
	 * algorithm will be used instead of the mergesort.
	 */
	public static final int SIZE_LIMIT = 4;

	protected static int MERGESORT_APPLICATIONS = 0;
	protected static int INSERTIONSORT_APPLICATIONS = 0;
	
	private MergeSort<T> mergeSort;
	
	public HybridMergeSort() {
		this.mergeSort = new MergeSort<T>();
	}
	@Override
	public void sort(T[] array) {
		this.resetCounting();
		
		int leftIndex = 0;
		int rightIndex = array.length - 1;
		
		if (array.length <= SIZE_LIMIT) {
			this.insertionSort(array, leftIndex, rightIndex);
			INSERTIONSORT_APPLICATIONS += 1;
			return;
		}
        
        else {
            
            int middle = (leftIndex + rightIndex) / 2;
            sort(array, leftIndex, middle);
            sort(array, middle + 1, rightIndex);
    
            this.mergeSort.merge(array, leftIndex, middle, rightIndex);
            MERGESORT_APPLICATIONS += 1;
        }
	}

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if ((rightIndex + 1) - leftIndex <= SIZE_LIMIT) {
			this.insertionSort(array, leftIndex, rightIndex);
			INSERTIONSORT_APPLICATIONS += 1;
			return;
		}
        
        else {
            
            int middle = (leftIndex + rightIndex) / 2;
            sort(array, leftIndex, middle);
            sort(array, middle + 1, rightIndex);
    
            this.mergeSort.merge(array, leftIndex, middle, rightIndex);
            MERGESORT_APPLICATIONS += 1;
        }
	}
	
	public void insertionSort(T[] array, int leftIndex, int rightIndex) {
		for (int i = leftIndex + 1; i < rightIndex + 1; i++) { 
			
			int j = i;
		
			while (j > leftIndex && array[j].compareTo(array[j-1]) <= 0) {
				Util.swap(array, j, j - 1);
				j -= 1;
			}
		}	
	}
	
	public int getMergeSortCounting() {
		return MERGESORT_APPLICATIONS;
	}
	
	public int getInsertionSortCounting() {
		return INSERTIONSORT_APPLICATIONS;
	}
	
	private void resetCounting() {
		MERGESORT_APPLICATIONS = 0;
		INSERTIONSORT_APPLICATIONS = 0;
	}
}
