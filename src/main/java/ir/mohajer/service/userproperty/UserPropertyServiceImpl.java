package ir.mohajer.service.userproperty;

import ir.mohajer.model.UserProperty;
import ir.mohajer.model.Users;
import ir.mohajer.repository.UserPropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPropertyServiceImpl implements UserPropertyService {

    private final UserPropertyRepository userPropertyRepository;

    public UserPropertyServiceImpl(UserPropertyRepository userPropertyRepository) {
        this.userPropertyRepository = userPropertyRepository;
    }

    @Override
    public List<UserProperty> findAll() {
       return this.userPropertyRepository.findAll();
    }

    @Override
    public List<UserProperty> findAllByUser(Users users) {
        return userPropertyRepository.findAllByUser(users);
    }

    @Override
    public void save(UserProperty userProperty) {
        userPropertyRepository.save(userProperty);
    }

    @Override
    public int getAllPriceByUser(Users user) {
        Integer allPriceByUser = userPropertyRepository.getAllPriceByUser(user.getId());
        return allPriceByUser==null ? 0 : allPriceByUser;
    }
}
