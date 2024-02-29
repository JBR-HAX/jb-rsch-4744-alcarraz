package org.jetbrains.assignment.repository;

import org.jetbrains.assignment.model.Move;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoveRepository extends JpaRepository<Move, Long> {
}
