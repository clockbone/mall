package com.clockbone.jpadao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.io.Serializable;

/**
 * Created by qinjun on 2016/3/10.
 * JpaRepository or MongoRepository. Those interfaces extend CrudRepository
 */
public interface MyBaseRepository <T, ID extends Serializable> extends JpaRepository<T, ID> {

}
