package com.example;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

//@RepositoryRestResource
@Repository
public interface MemberRepository extends PagingAndSortingRepository<Member, Long> {
}
