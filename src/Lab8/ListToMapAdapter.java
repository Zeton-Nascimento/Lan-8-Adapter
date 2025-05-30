package Lab8;

import java.util.*;

public class ListToMapAdapter<V> implements Map<Integer, V> {
    private final List<V> list;

    public ListToMapAdapter(List<V> list) {
        if (list == null) throw new NullPointerException("Lista não pode ser nula");
        this.list = list;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        if (!(key instanceof Integer)) return false;
        int idx = (Integer) key;
        if (idx < 0 || idx >= list.size()) return false;
        return list.get(idx) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        return list.contains(value);
    }

    @Override
    public V get(Object key) {
        if (key == null) throw new NullPointerException("Chave não pode ser nula");
        if (!containsKey(key)) throw new IndexOutOfBoundsException("Chave fora do intervalo: " + key);
        return list.get((Integer) key);
    }

    @Override
    public V put(Integer key, V value) {
        if (key == null) throw new NullPointerException("Chave não pode ser nula");
        if (value == null) throw new NullPointerException("Valor não pode ser nulo");
        if (key < 0) throw new IllegalArgumentException("Chave negativa: " + key);
        if (key < list.size()) {
            return list.set(key, value);
        } else if (key == list.size()) {
            list.add(value);
            return null;
        } else {
            throw new IndexOutOfBoundsException("Índice fora do intervalo: " + key);
        }
    }

    @Override
    public V remove(Object key) {
        if (key == null) throw new NullPointerException("Chave não pode ser nula");
        if (!(key instanceof Integer)) throw new IllegalArgumentException("Chave deve ser Integer");
        int idx = (Integer) key;
        if (idx < 0 || idx >= list.size()) throw new IndexOutOfBoundsException("Chave fora do intervalo: " + key);
        V old = list.get(idx);
        list.set(idx, null);
        return old;
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public Collection<V> values() {
        return Collections.unmodifiableList(list);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Map)) return false;
        Map<?, ?> other = (Map<?, ?>) o;
        return this.entrySet().equals(other.entrySet());
    }

    // Métodos não requeridos pelo exercício lançam exceção
    @Override public Set<Integer> keySet() { throw new UnsupportedOperationException("Operação não suportada"); }
    @Override public Set<Entry<Integer, V>> entrySet() { throw new UnsupportedOperationException("Operação não suportada"); }
    @Override public void putAll(Map<? extends Integer, ? extends V> m) { throw new UnsupportedOperationException("Operação não suportada"); }
    @Override public int hashCode() { throw new UnsupportedOperationException("Operação não suportada"); }
}