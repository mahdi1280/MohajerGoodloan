package ir.mohajer.service.installment;

import ir.mohajer.model.Installments;
import ir.mohajer.model.UserLoan;
import ir.mohajer.repository.InstallmentsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstallmentsServiceImpl implements InstallmentsService {

    private final InstallmentsRepository installmentsRepository;

    public InstallmentsServiceImpl(InstallmentsRepository installmentsRepository) {
        this.installmentsRepository = installmentsRepository;
    }


    @Override
    public List<Installments> findByLoanUserId(UserLoan userLoan) {
        return installmentsRepository.findByUserLoan(userLoan);
    }
}
