package com.service;
import com.model.UserInfo;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by king on 15/11/25.
 */
@RepositoryRestResource(path = "user")
public interface UserRepository extends PagingAndSortingRepository<UserInfo, Long> {
    List<UserInfo> findByUsernameLike(@Param("username") String username);
}
