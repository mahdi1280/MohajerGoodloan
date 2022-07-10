package ir.mohajer.service.userproperty;

import ir.mohajer.model.UserProperty;
import ir.mohajer.model.Users;
import org.apache.catalina.User;

import java.util.List;

public interface UserPropertyService {

    List<UserProperty> findAll();

    List<UserProperty> findAllByUser(Users users);

    void save(UserProperty userProperty);

}
