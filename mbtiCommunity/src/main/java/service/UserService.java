package service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional

public class UserService {
private final UserRepository userRepository;

    public void join(User user){
    userRepository.save(user);
    }
    public User getUserEntity(String email){
        Optional<User> user = userRepository.findByEmailAndStatus(email, 1);
        return user.orElse(null);
    }
}
