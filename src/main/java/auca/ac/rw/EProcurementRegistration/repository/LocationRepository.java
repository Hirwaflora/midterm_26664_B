package auca.ac.rw.EProcurementRegistration.repository;

import auca.ac.rw.EProcurementRegistration.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    // Requirement 7: Implementation of existBy() method for Existence Checking.
    boolean existsByCode(String code);

    // Also adding a find method for convenience
    Optional<Location> findByCode(String code);
}
