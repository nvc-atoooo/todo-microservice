package com.example.authservice.repository;

import com.example.authservice.domain.UserAuthority;
import org.springframework.data.repository.CrudRepository;

public interface UserAuthorityRepository extends CrudRepository<UserAuthority, Long> {

}
