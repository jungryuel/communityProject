package service;

import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    public void joinUser(User user){
        userRepository.save(user);
    }
    public boolean isNicknameAvailable(String nickname){
        Optional<User> user = userRepository.findByNicknameAndStatus(nickname, 1);
        return user.isEmpty();
    }

    public User getUserEntity(String email){
        Optional<User> user = userRepository.findByEmailAndStatus(email, 1);
        return user.orElse(null);
    }

    public User getUserEntityById(Long userId){
        Optional<User> user = userRepository.findByUserIdAndStatus(userId, 1);
        return user.orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmailAndStatus(username, 1);
        if (user.isPresent()){
            return (UserDetails) user.get();
        }
        else{
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }
    }
}