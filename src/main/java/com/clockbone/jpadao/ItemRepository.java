package com.clockbone.jpadao;


import com.clockbone.jpadomain.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/*public interface ItemRepository extends JpaRepository<Test, Integer>,QueryDslPredicateExecutor<Test> {*/

    public interface ItemRepository extends JpaRepository<Test, Integer> {
     
	 public List<Test> findByKey(String key);

        public List<Test> findByName(String name);

       /* @Modifying
        @Query(value = "select u.id from t_test u ")
        public Test getJackUser(Integer id);*/

      /*  @Modifying
        @Transactional(propagation= Propagation.MANDATORY)
        @Query("update  t_test  u set u.name='testtse'  where u.id =?1  ")
        public int cutDown(Integer id);
*/

        @Query(value = "select * from t_test u where u.name like %?1", nativeQuery = true)
        List<Test> findByFirstnameEndsWith(String name);

    }



