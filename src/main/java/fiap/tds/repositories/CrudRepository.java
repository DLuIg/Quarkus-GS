package fiap.tds.repositories;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, ID> {
    boolean save(T entity);
    Optional<T> findById(ID id);
    List<T> findAll();
    boolean update(T entity);
    boolean deleteById(ID id);
}
