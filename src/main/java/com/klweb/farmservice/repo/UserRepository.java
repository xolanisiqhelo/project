package com.klweb.farmservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klweb.farmservice.model.Farm;
import com.klweb.farmservice.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}