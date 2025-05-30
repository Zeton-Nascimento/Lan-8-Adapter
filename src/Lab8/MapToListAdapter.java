package Lab8;

import java.util.*;

public class MapToListAdapter<V> implements List<V> {
    private final SortedMap<Integer, V> map;

    public MapToListAdapter(Map<Integer, V> map) {
        if (map == null) throw new NullPointerException("Mapa não pode ser nulo");
        this.map = new TreeMap<>(map);
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size()) throw new IndexOutOfBoundsException("Índice fora do intervalo: " + index);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return map.containsValue(o);
    }

    @Override
    public Iterator<V> iterator() {
        return new Iterator<V>() {
            private int index = 0;
            @Override public boolean hasNext() { return index < size(); }
            @Override public V next() {
                if (!hasNext()) throw new NoSuchElementException();
                return get(index++);
            }
        };
    }

    @Override
    public V get(int index) {
        checkIndex(index);
        return map.get(index);
    }

    @Override
    public boolean add(V e) {
        if (e == null) throw new NullPointerException("Elemento não pode ser nulo");
        map.put(size(), e);
        return true;
    }

    @Override
    public void add(int index, V element) {
        throw new UnsupportedOperationException("Operação não suportada");
    }

    @Override
    public V set(int index, V element) {
        throw new UnsupportedOperationException("Operação não suportada");
    }

    @Override
    public V remove(int index) {
        checkIndex(index);
        V removed = map.remove(index);
        for (int i = index; i < size() + 1; i++) {
            V v = map.remove(i + 1);
            if (v != null) map.put(i, v);
        }
        return removed;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) throw new NullPointerException("Elemento não pode ser nulo");
        for (int i = 0; i < size(); i++) {
            if (Objects.equals(get(i), o)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof List)) return false;
        List<?> other = (List<?>) o;
        if (other.size() != size()) return false;
        for (int i = 0; i < size(); i++) {
            if (!Objects.equals(get(i), other.get(i))) return false;
        }
        return true;
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size()];
        for (int i = 0; i < size(); i++) arr[i] = get(i);
        return arr;
    }

    // Métodos não requeridos pelo exercício lançam exceção
    @Override public <T> T[] toArray(T[] a) { throw new UnsupportedOperationException("Operação não suportada"); }
    @Override public int indexOf(Object o) { throw new UnsupportedOperationException("Operação não suportada"); }
    @Override public int lastIndexOf(Object o) { throw new UnsupportedOperationException("Operação não suportada"); }
    @Override public ListIterator<V> listIterator() { throw new UnsupportedOperationException("Operação não suportada"); }
    @Override public ListIterator<V> listIterator(int index) { throw new UnsupportedOperationException("Operação não suportada"); }
    @Override public List<V> subList(int fromIndex, int toIndex) { throw new UnsupportedOperationException("Operação não suportada"); }
    @Override public boolean containsAll(Collection<?> c) { throw new UnsupportedOperationException("Operação não suportada"); }
    @Override public boolean addAll(Collection<? extends V> c) { throw new UnsupportedOperationException("Operação não suportada"); }
    @Override public boolean addAll(int index, Collection<? extends V> c) { throw new UnsupportedOperationException("Operação não suportada"); }
    @Override public boolean removeAll(Collection<?> c) { throw new UnsupportedOperationException("Operação não suportada"); }
    @Override public boolean retainAll(Collection<?> c) { throw new UnsupportedOperationException("Operação não suportada"); }
    @Override public int hashCode() { throw new UnsupportedOperationException("Operação não suportada"); }
}