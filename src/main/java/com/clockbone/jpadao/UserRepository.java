package com.clockbone.jpadao;

import com.clockbone.jpadomain.Test;
import com.clockbone.jpadomain.UserTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * Created by qinjun on 2016/3/10.
 */
//@NoRepositoryBean
public interface UserRepository extends JpaRepository<UserTest, Integer> {

    @Query(value = "select * from t_test u where  u.id=?1", nativeQuery = true)
    public List<UserTest> getJackUser(Integer p1);


    @Query(value = "select * from t_test u where u.name like %?1", nativeQuery = true)
    List<UserTest> test(String name);
}
