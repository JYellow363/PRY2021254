package pe.edu.upc.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.dto.PaymentDto;
import pe.edu.upc.model.Guardian;
import pe.edu.upc.repository.IGuardianRepository;
import pe.edu.upc.service.IPaymentService;
import pe.edu.upc.util.Constants;
import pe.edu.upc.util.ValidatePayment;

@Service
public class PaymentServiceImpl implements IPaymentService {

	@Autowired
	private IGuardianRepository guardianRepository;

	@Transactional
	@Override
	public int payPremium(PaymentDto payment) {
		 int result = ValidatePayment.validate(payment);
		 if(result == 1) {
			 Guardian guardian = guardianRepository.findById(payment.getIdGuardian()).get();
			 guardian.setPremium(true);
			 Guardian guardianSave = guardianRepository.save(guardian);
			 if(guardianSave == null) result = Constants.ERROR_BD;
		 }
		 return result;
	}
	
	
}
