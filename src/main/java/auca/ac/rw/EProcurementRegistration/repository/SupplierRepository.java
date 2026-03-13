package auca.ac.rw.EProcurementRegistration.repository;

import auca.ac.rw.EProcurementRegistration.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    boolean existsByEmail(String email);

    boolean existsByTinNo(String tinNo);
}
