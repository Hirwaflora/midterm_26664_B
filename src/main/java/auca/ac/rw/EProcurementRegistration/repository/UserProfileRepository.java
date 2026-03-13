package auca.ac.rw.EProcurementRegistration.repository;

import auca.ac.rw.EProcurementRegistration.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
}
