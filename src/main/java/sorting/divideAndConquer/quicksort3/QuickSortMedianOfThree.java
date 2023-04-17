package sorting.divideAndConquer.quicksort3;

import sorting.AbstractSorting;
import util.Util;

/**
 * A classe QuickSortMedianOfThree representa uma variação do QuickSort que
 * funciona de forma ligeiramente diferente. Relembre que quando o pivô
 * escolhido divide o array aproximadamente na metade, o QuickSort tem um
 * desempenho perto do ótimo. Para aproximar a entrada do caso ótimo, diversas
 * abordagens podem ser utilizadas. Uma delas é usar a mediana de 3 para achar o
 * pivô. Essa técnica consiste no seguinte:
 * 1. Comparar o elemento mais a esquerda, o central e o mais a direita do intervalo.
 * 2. Ordenar os elementos, tal que: A[left] < A[center] < A[right].
 * 3. Adotar o A[center] como pivô.
 * 4. Colocar o pivô na penúltima posição A[right-1].
 * 5. Aplicar o particionamento considerando o vetor menor, de A[left+1] até A[right-1].
 * 6. Aplicar o algoritmo na particao a esquerda e na particao a direita do pivô.
 */
public class QuickSortMedianOfThree<T extends Comparable<T>> extends AbstractSorting<T> {

    @Override
    public void sort(T[] array, int leftIndex, int rightIndex) {
    	if (leftIndex < rightIndex) {
            int pivot = partition(array, leftIndex, rightIndex);
            sort(array, leftIndex, pivot - 1);
            sort(array, pivot + 1, rightIndex);
        }
    }

    private int partition(T[] array, int leftIndex, int rightIndex) {
    	int midIndex = (rightIndex - leftIndex) / 2;
        int median = medianOfThree(array, leftIndex, midIndex, rightIndex);
        Util.swap(array, median, rightIndex);  // move pivot to the end
        T pivot = array[rightIndex];
        int i = leftIndex - 1;
        for (int j = leftIndex; j < rightIndex; j++) {
            if (array[j].compareTo(pivot) == -1) {
                i++;
                Util.swap(array, i, j);
            }
        }
        Util.swap(array, i + 1, rightIndex);  // move pivot to its final position
        return i + 1;
    }
    
    public int medianOfThree(T[] array, int a, int b, int c) {
        T x = array[a], y = array[b], z = array[c];
        if (x.compareTo(y) == -1) {
            if (y.compareTo(z) == -1) return b;  // x < y < z
            else if (x.compareTo(z) == -1) return c;  // x < z <= y
            else return a;  // z <= x < y
        } else {
            if (x.compareTo(z) == -1) return a;  // y <= x < z
            else if (y.compareTo(z) == -1) return c;  // y < z <= x
            else return b;  // z <= y < x
        }
    }
}
