package ir.mohajer.repository;

import ir.mohajer.model.UserProperty;
import ir.mohajer.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserPropertyRepository extends JpaRepository<UserProperty,Long> {

    List<UserProperty> findAllByUser(Users users);

}
