package Lab8;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

public class AdapterTest {
    private ListToMapAdapter<String> listToMap;
    private MapToListAdapter<String> mapToList;
    private List<String> baseList;
    private Map<Integer, String> baseMap;

    @BeforeEach
    void setup() {
        baseList = new ArrayList<>();
        baseList.add("A");
        baseList.add("B");
        listToMap = new ListToMapAdapter<>(baseList);

        baseMap = new LinkedHashMap<>();
        baseMap.put(0, "X");
        baseMap.put(1, "Y");
        mapToList = new MapToListAdapter<>(baseMap);
    }

    @Test
    void testListToMapBasicOperations() {
        assertEquals(2, listToMap.size());
        assertFalse(listToMap.isEmpty());
        assertTrue(listToMap.containsKey(0));
        assertTrue(listToMap.containsValue("B"));
        assertEquals("A", listToMap.get(0));

        listToMap.put(2, "C");
        assertEquals(3, listToMap.size());
        assertEquals("C", listToMap.get(2));

        assertEquals("B", listToMap.remove(1));
        assertFalse(listToMap.containsKey(1));

        listToMap.clear();
        assertTrue(listToMap.isEmpty());
    }

    @Test
    void testMapToListBasicOperations() {
        assertEquals(2, mapToList.size());
        assertFalse(mapToList.isEmpty());
        assertTrue(mapToList.contains("Y"));
        assertEquals("X", mapToList.get(0));

        mapToList.add("Z");
        assertEquals(3, mapToList.size());
        assertEquals("Z", mapToList.get(2));

        assertEquals("Y", mapToList.remove(1));
        assertFalse(mapToList.contains("Y"));

        mapToList.clear();
        assertTrue(mapToList.isEmpty());
    }

    @Test
    void testListToMapExceptions() {
        assertThrows(NullPointerException.class, () -> new ListToMapAdapter<>(null));
        assertThrows(NullPointerException.class, () -> listToMap.get(null));
        assertThrows(IndexOutOfBoundsException.class, () -> listToMap.get(100));
        assertThrows(NullPointerException.class, () -> listToMap.put(null, "V"));
        assertThrows(NullPointerException.class, () -> listToMap.put(0, null));
        assertThrows(IllegalArgumentException.class, () -> listToMap.put(-1, "V"));
        assertThrows(IndexOutOfBoundsException.class, () -> listToMap.put(5, "V"));
        assertThrows(IndexOutOfBoundsException.class, () -> listToMap.remove(5));
    }

    @Test
    void testMapToListExceptions() {
        assertThrows(NullPointerException.class, () -> new MapToListAdapter<>(null));
        assertThrows(NullPointerException.class, () -> mapToList.add(null));
        assertThrows(IndexOutOfBoundsException.class, () -> mapToList.get(100));
        assertThrows(IndexOutOfBoundsException.class, () -> mapToList.remove(100));
        assertThrows(NullPointerException.class, () -> mapToList.remove((Object) null));
    }
}