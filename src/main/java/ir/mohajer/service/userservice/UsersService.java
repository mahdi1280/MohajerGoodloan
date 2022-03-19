package ir.mohajer.service.userservice;

import ir.mohajer.model.Users;

import java.util.List;
import java.util.Optional;

public interface UsersService {

    List<Users> findAll();

    Optional<Users> findById(long id);
}
