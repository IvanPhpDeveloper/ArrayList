package TheBestArrayList;

import java.util.*;


public class TheBestArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int capacity;
    private T[] arr;
    private int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size()==0;
    }

    @Override
    public boolean contains(Object o) {
        for (T element:arr) {
            if (Objects.equals(element, o))
                return true;
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new TheBestArrayIterator();

    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(arr, size);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(T t) {
        add(this.size+1,t);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i <this.size ; i++) {
            if(arr[i].equals(o)){
                System.arraycopy(arr,i+1,arr,i,this.size-i-1);//this.size-i-1   начала
                size--; //this.size or size
                arr[size]=null;
           return true;
            }
        }
        return false;
    }


    @Override
    public boolean containsAll(Collection<?> c) {
        for(Object o:c){ //каждый елемент в колеекции с последовательно приравниваем o До тех пор не достигнит последнех
            if(!TheBestArrayList.this.contains(o)){
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        addAll(size,c); //size-конец нашей существующий коллекции
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        for(T element:c){
            add(index,element);
            index++;
            return true;
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean result = false;
        int indexOfElementOfCollection;//index который возвращает indexOf
        for (Object elCol : c) {
            do {
                indexOfElementOfCollection = indexOf(elCol);//первое значение
                if (indexOfElementOfCollection >= 0) {
                    remove(indexOfElementOfCollection);
                    result=true;
                }

            } while (indexOfElementOfCollection >= 0);//удаляем дубликат
        }
        return result;
    }




    @Override
    public boolean retainAll(Collection<?> c) {
        boolean inCollection;
        int i = this.size - 1;

        do {
            inCollection = false;
            for (Object elOfColl : c) {
                if (Objects.equals(arr[i], elOfColl)) {
                    inCollection = true;
                    break;
                }
                if (!inCollection) {
                    remove(i);
                }
                i--;
            }
        } while (i >= 0); //каждый элемент пробегаем и сравнивает с коллекцией
                return true;
            }


    @Override
    public void clear() {
      size=0;
      Arrays.fill(arr, null); //Стоит ли после сотрение заполнять массив нулями
    }

    @Override
    public T get(int index) {
if(index>=size-1||index<size){//если Index меньше 0
    throw new IndexOutOfBoundsException("");
}
return arr[index];
    }

    @Override
    public T set(int index, T element) {
        if(index>=size){
            throw new IndexOutOfBoundsException("");
        }
        T result = arr[index];
        arr[index] = element;
        return result;
    }

    @Override
    public void add(int index, T element) {
      if(index>=size){
          throw new IndexOutOfBoundsException( "");
      }
      ensureCapacity();
      System.arraycopy(arr,index,arr,index+1,this.size=index);
      arr[index]=element;//arr[index] дырка в которую вставляем элемент
      this.size++;

    }

    @Override
    public T remove(int index) {

        if (index > this.size - 1 || index < 0) {
            throw new IndexOutOfBoundsException("You try REMOVE element out of bound array. From index = " + index);
        } else {
            System.arraycopy(arr, index + 1, arr, index, size - index + 1);
        }
        if (size > 0) {
            size--;

        }

         return arr[index];
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(arr[i], o)) {
                return i;
            }
        }
        return -1;
    }


    @Override
    public int lastIndexOf(Object o) {
        for (int i = size-1; i >= 0; i--) {
            if (Objects.equals(arr[i], o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        return (ListIterator<T>) new TheBestArrayIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("You try set ListIterator out of bound array. From index = " + index);
        }
        return new TheBestArrayListIterator(index);
    }


    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        if(fromIndex < 0 || toIndex > size){
            throw new IndexOutOfBoundsException("");
        }
        if(fromIndex > toIndex){
             throw new IllegalArgumentException("");
        }
        List<T> subList = (List<T>) new TheBestArrayList<>();
        for (int i = fromIndex; i < toIndex; i++) {
            subList.add(arr[i]);
        }
        return subList;
    }
    private void ensureCapacity() {
        this.capacity = getNewCapacity();
    }

    private int getNewCapacity() {
        return ((
                this.capacity * 3) / 2) + 1;
    }



    private class TheBestArrayIterator implements Iterator<T> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex >= 0 && currentIndex < size;
        }

        @Override
        public T next() {
            if (currentIndex >= 0 && currentIndex < size) {
                this.currentIndex++;
                return arr[currentIndex - 1];
            } else {
                throw new IndexOutOfBoundsException("You try GET NEXT elements out of bound array: ( " + currentIndex + " ) ");
            }
        }

        @Override
        public void remove() {
            if (currentIndex >= 0 && currentIndex < size) {
                System.arraycopy(arr, currentIndex + 1, arr, currentIndex, size - currentIndex + 1);
                if (size > 0) {
                    size--;
                }
            } else {
                throw new IndexOutOfBoundsException("You try REMOVE Current elements out of bound array: ( " + currentIndex + " ) ");
            }
        }
    }

    private class TheBestArrayListIterator implements ListIterator<T> {
        private int currentIndex = 0;

        public TheBestArrayListIterator() {
        }

        public TheBestArrayListIterator(int currentIndex) {
            if (currentIndex >= 0 && currentIndex < size) {
                this.currentIndex = currentIndex;
            } else {
                throw new IndexOutOfBoundsException("You try Set CurrentIndex out of bounds. CurrentIndex = " + currentIndex);
            }
        }

        @Override
        public boolean hasNext() {
            return currentIndex >= 0 && currentIndex < size;
        }

        @Override
        public T next() {
            if (currentIndex >= 0 && currentIndex < size) {
                this.currentIndex++;
                return arr[currentIndex - 1];
            } else {
                throw new IndexOutOfBoundsException("You try get Next element out of array bounds. Index = " + currentIndex);
            }
        }

        @Override
        public boolean hasPrevious() {
            return currentIndex - 1 >= 0 && currentIndex - 1 < size;
        }

        @Override
        public T previous() {
            if (currentIndex - 1 >= 0 && currentIndex - 1 < size) {
                this.currentIndex--;
                return arr[currentIndex];
            } else {
                throw new IndexOutOfBoundsException("You try get Previous element out of array bounds. Index = " + currentIndex);
            }
        }

        @Override
        public int nextIndex() {
            if (currentIndex >= 0 && currentIndex + 1 <= size) {
                return currentIndex++;
            } else {
                throw new IndexOutOfBoundsException("You try get NextIndex out of array bounds. Index = " + currentIndex);
            }
        }

        @Override
        public int previousIndex() {
            if (currentIndex - 1 >= 0 && currentIndex - 1 < size) {
                return currentIndex--;
            } else {
                throw new IndexOutOfBoundsException("You try get NextIndex out of array bounds. Index = " + currentIndex);
            }
        }

        @Override
        public void remove() {
            TheBestArrayList.this.remove(currentIndex);
        }

        @Override
        public void set(T t) {
            TheBestArrayList.this.set(currentIndex, t);
        }

        @Override
        public void add(T t) {
            TheBestArrayList.this.add(currentIndex, t);
            currentIndex++;
        }
    }

}
