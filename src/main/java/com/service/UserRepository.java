package com.service;
import com.model.UserInfo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by king on 15/11/25.
 */
@Transactional
public interface UserRepository extends PagingAndSortingRepository<UserInfo, Long> {
    UserInfo findByUsername(@Param("username") String username);
    UserInfo findByUsernameAndPassword(@Param("username") String username,@Param("password") String password);
    UserInfo findByPhone(@Param("phone") String phone);
    @Modifying
    @Query("update UserInfo set avatar = ?2 where accountid = ?1")
    void updateAvatar(Long accountid,String url);

    @Modifying
    @Query("update UserInfo set avatar=?1,birthday=?2,city=?3,constitution=?4,height=?5,nickname=?6,phone=?7,scene=?8,sex=?9,weight=?10 where accountid=?11")
    void update(String avatar, String birthday, String city, String constitution, Float height,
                String nickname, String phone, String scene, String sex, Float weight, long accountid);
}
