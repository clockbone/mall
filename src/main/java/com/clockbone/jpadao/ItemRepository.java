package com.clockbone.jpadao;


import com.clockbone.jpadomain.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import java.util.List;
/*public interface ItemRepository extends JpaRepository<Test, Integer>,QueryDslPredicateExecutor<Test> {*/

    public interface ItemRepository extends JpaRepository<Test, Integer> {
     
	 public List<Test> findByKey(String key);

        public List<Test> findByName(String name);

}



