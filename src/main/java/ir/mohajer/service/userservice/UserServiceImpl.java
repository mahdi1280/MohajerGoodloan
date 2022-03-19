package ir.mohajer.service.userservice;

import ir.mohajer.model.Users;
import ir.mohajer.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    public UserServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    @Override
    public Optional<Users> findById(long id) {
        return usersRepository.findById(id);
    }
}
