package com.ran.ams.repository;

import com.ran.ams.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Riyan Amanda
 * {@code @linkedin} <a href="https://id.linkedin.com/in/riyan-amanda/in">...</a>
 * @since 09/12/2024
 **/

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
    boolean existsByName(String name);
}
