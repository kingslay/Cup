package com.service;
import com.model.UserInfo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by king on 15/11/25.
 */
public interface UserRepository extends PagingAndSortingRepository<UserInfo, Long> {
    UserInfo findByUsername(@Param("username") String username);
    UserInfo findByUsernameAndPassword(@Param("username") String username,@Param("password") String password);
    UserInfo findByPhone(@Param("phone") String phone);
    @Modifying
    @Query("update UserInfo  set avatar = ?1 where accountid = ?2")
    void updateAvatar(String accountid,String url);

}
