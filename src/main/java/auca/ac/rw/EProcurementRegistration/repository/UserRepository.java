package auca.ac.rw.EProcurementRegistration.repository;

import auca.ac.rw.EProcurementRegistration.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Requirement 8: Retrieve all users from a given province using province code
    // OR province name.
    // The query logic uses implicit/explicit joins up the Location parent
    // hierarchy.
    // Assuming worst case scenario where a User is linked to a VILLAGE, we check up
    // to 4 layers of parents
    // (Village -> Cell -> Sector -> District -> Province)

    @Query("SELECT u FROM User u " +
            "JOIN u.location l " +
            "LEFT JOIN l.parent p1 " + // Could be cell
            "LEFT JOIN p1.parent p2 " + // Could be sector
            "LEFT JOIN p2.parent p3 " + // Could be district
            "LEFT JOIN p3.parent p4 " + // Could be province
            "WHERE (l.code = :param OR l.name = :param) OR " +
            "(p1.code = :param OR p1.name = :param) OR " +
            "(p2.code = :param OR p2.name = :param) OR " +
            "(p3.code = :param OR p3.name = :param) OR " +
            "(p4.code = :param OR p4.name = :param)")
    List<User> findUsersByProvinceOrAnyLocationCode(@Param("param") String param);

    // Also check for user existing
    boolean existsByEmail(String email);
}
