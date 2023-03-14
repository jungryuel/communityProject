package repository;

import java.util.Optional;
import domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByNicknameAndStatus(String nickname, Integer status);
    Optional<User> findByEmailAndStatus(String email, Integer Status);
    Optional<User> findByUserIdAndStatus(Long userId, Integer Status);
}
