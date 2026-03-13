package auca.ac.rw.EProcurementRegistration.controller;

import auca.ac.rw.EProcurementRegistration.model.Location;
import auca.ac.rw.EProcurementRegistration.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping
    public ResponseEntity<Location> saveLocation(@RequestBody Location location) {
        return ResponseEntity.ok(locationService.saveLocation(location));
    }

    @GetMapping("/exists/{code}")
    public ResponseEntity<Boolean> existsByCode(@PathVariable String code) {
        return ResponseEntity.ok(locationService.locationExistsByCode(code));
    }

    @GetMapping
    public ResponseEntity<List<Location>> getAllLocations() {
        return ResponseEntity.ok(locationService.getAllLocations());
    }
}
