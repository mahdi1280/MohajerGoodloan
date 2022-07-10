package ir.mohajer.repository;

import ir.mohajer.model.UserProperty;
import ir.mohajer.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserPropertyRepository extends JpaRepository<UserProperty,Long> {

    List<UserProperty> findAllByUser(Users users);

    @Query("select sum(u.price) from UserProperty u where u.user.id=:userId")
    Integer getAllPriceByUser(long userId);
}