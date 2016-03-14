package com.clockbone.jpadao;

import com.clockbone.jpadomain.Person;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by qinjun on 2016/3/11.
 */
public interface PersonRepository extends CrudRepository<Person,Integer> {
}
