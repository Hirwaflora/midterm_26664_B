package auca.ac.rw.EProcurementRegistration.controller;

import auca.ac.rw.EProcurementRegistration.model.User;
import auca.ac.rw.EProcurementRegistration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<User>> getUsersPaginatedAndSorted(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "ASC") String sortDirection) {

        return ResponseEntity.ok(userService.getUsersWithPaginationAndSorting(page, size, sortField, sortDirection));
    }

    @GetMapping("/location")
    public ResponseEntity<List<User>> getUsersByLocationParam(
            @RequestParam String param) {

        return ResponseEntity.ok(userService.getUsersByLocationParam(param));
    }
}
