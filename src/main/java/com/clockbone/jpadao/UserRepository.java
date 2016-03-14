package com.clockbone.jpadao;

import com.clockbone.jpadomain.Test;
import com.clockbone.jpadomain.UserTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by qinjun on 2016/3/10.
 */
//@NoRepositoryBean
public interface UserRepository extends JpaRepository<UserTest, Integer> {

    @Query(value = "select * from t_test u where  u.id=?1", nativeQuery = true)
    public List<UserTest> getJackUser(Integer p1);

  /*  @Query(value = "SELECT u from t_test u WHERE  u.id=?1")
    public List<UserTest> getJackUserTest(Integer id);*/

    /*@Query("select u from t_test u where u.mkey = :mkey or u.name = :name")
    List<UserTest> findByLastnameOrFirstname(@Param("mkey") String lastname,
                                   @Param("name") String firstname);*/


    @Query(value = "select * from t_test u where u.name like %?1", nativeQuery = true)
    List<UserTest> test(String name);

    @Modifying
    @Transactional
    @Query(value = "update t_test u set u.name=?2 where u.id=?1", nativeQuery = true)
    void update(Integer id,String name);
}
