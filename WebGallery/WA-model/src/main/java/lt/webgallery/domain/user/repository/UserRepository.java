package lt.webgallery.domain.user.repository;

import lt.webgallery.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
