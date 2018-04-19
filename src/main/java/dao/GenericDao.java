package dao;

import model.BaseEntity;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public interface GenericDao <T extends BaseEntity, PK extends Serializable> {
    void create(T objectToCreate);

    LinkedList<T> read();

    void update(T objectToUpdate, PK id);

    void delete(PK id);

    List<PK> getAllId();
}
