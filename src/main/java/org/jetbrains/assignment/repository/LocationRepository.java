package org.jetbrains.assignment.repository;

import org.jetbrains.assignment.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
