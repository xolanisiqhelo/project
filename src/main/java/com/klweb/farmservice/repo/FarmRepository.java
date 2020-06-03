package com.klweb.farmservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klweb.farmservice.model.Farm;

@Repository
public interface FarmRepository extends JpaRepository<Farm, Long>{

}
