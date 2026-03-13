package auca.ac.rw.EProcurementRegistration.service;

import auca.ac.rw.EProcurementRegistration.model.Location;
import auca.ac.rw.EProcurementRegistration.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    // Requirement 2: Implementation of saving Location.
    public Location saveLocation(Location location) {
        // Because of the hierarchical relationship, if we save a Location with
        // subLocations
        // and CascadeType.ALL is configured, Spring JPA will automatically traverse
        // down
        // the list and persist the District, Sector, Cell, and Village from the
        // Province object down.
        if (location.getSubLocations() != null) {
            location.getSubLocations().forEach(sub -> sub.setParent(location));
        }
        return locationRepository.save(location);
    }

    // Check if location code exists (Req 7)
    public boolean locationExistsByCode(String code) {
        return locationRepository.existsByCode(code);
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }
}
