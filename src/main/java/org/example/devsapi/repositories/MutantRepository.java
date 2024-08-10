package org.example.devsapi.repositories;

import org.example.devsapi.models.Mutant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MutantRepository extends JpaRepository<Mutant, UUID> {
}
