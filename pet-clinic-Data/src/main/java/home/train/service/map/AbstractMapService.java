package home.train.service.map;

import home.train.model.BaseEntity;

import java.util.*;

public abstract class AbstractMapService<T extends BaseEntity, ID extends Long> {

    Map<Long, T> map = new HashMap<>();

    Set<T> findAll() {
        return new HashSet<>(map.values());
    }

    T findById(ID id) {
        return map.get(id);
    }

    T save(T object) {

        if (object != null) {
            if (object.getId() == null) {
                object.setId(getNextId());
            }

            T savedObject=map.put(object.getId(),object);
            if(savedObject==null)return object;
            else return savedObject;
        } else {
            throw new RuntimeException("Object should not be Null");
        }
    }

    void deleteById(ID id) {
        map.remove(id);
    }

    void delete(T object) {
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

    private Long getNextId() {
        Long Id ;
        try {
            Id = Collections.max(map.keySet()) + 1L;
        } catch (NoSuchElementException e) {
            Id = 1L;
        }
        return Id;
    }
}
