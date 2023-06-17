package com.education.repository;

import com.education.entity.Facsimile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacsimileRepository extends JpaRepository<Facsimile, Long> {

}
