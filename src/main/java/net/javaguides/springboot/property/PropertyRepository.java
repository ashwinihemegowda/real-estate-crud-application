package net.javaguides.springboot.property;

import org.hibernate.mapping.Property;
import org.springframework.data.repository.CrudRepository;

public interface PropertyRepository extends CrudRepository<Properties, Integer> {
    //public Long countById(Integer id);
}
