package com.thbs.realest.property;

import org.springframework.data.repository.CrudRepository;

public interface PropertyRepository extends CrudRepository<Properties, Integer> {
    //public Long countById(Integer id);
}
