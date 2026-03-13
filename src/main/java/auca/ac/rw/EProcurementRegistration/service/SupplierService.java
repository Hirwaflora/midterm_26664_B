package auca.ac.rw.EProcurementRegistration.service;

import auca.ac.rw.EProcurementRegistration.model.Supplier;
import auca.ac.rw.EProcurementRegistration.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    public Supplier registerSupplier(Supplier supplier) {
        if (supplierRepository.existsByEmail(supplier.getEmail())) {
            throw new RuntimeException("Email already registered: " + supplier.getEmail());
        }
        if (supplierRepository.existsByTinNo(supplier.getTinNo())) {
            throw new RuntimeException("TIN Number already registered: " + supplier.getTinNo());
        }
        supplier.setRegisteredAt(LocalDateTime.now());
        supplier.setStatus("Pending Verification");
        return supplierRepository.save(supplier);
    }

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public Optional<Supplier> getSupplierById(Long id) {
        return supplierRepository.findById(id);
    }
}
