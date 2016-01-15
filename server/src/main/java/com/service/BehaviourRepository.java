package com.service;

import com.model.BehaviourInfo;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by kingslay on 15/11/26.
 */
@RepositoryRestResource(path = "behaviour")
public interface BehaviourRepository extends PagingAndSortingRepository<BehaviourInfo, Long> {

}
