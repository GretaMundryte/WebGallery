package lt.webgallery.domain.user.service;


import lombok.RequiredArgsConstructor;
import lt.webgallery.domain.user.model.User;
import lt.webgallery.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private UserRepository userRepository;

    private List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
