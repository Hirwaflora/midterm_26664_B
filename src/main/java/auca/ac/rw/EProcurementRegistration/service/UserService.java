package auca.ac.rw.EProcurementRegistration.service;

import auca.ac.rw.EProcurementRegistration.model.User;
import auca.ac.rw.EProcurementRegistration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Requirement 3: Implementation of Sorting and Pagination functionality.
    public Page<User> getUsersWithPaginationAndSorting(int page, int size, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return userRepository.findAll(pageable);
    }

    // Requirement 8: Retrieve all users from a given province.
    public List<User> getUsersByLocationParam(String param) {
        return userRepository.findUsersByProvinceOrAnyLocationCode(param);
    }
}
