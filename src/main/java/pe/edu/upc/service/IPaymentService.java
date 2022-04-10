package pe.edu.upc.service;

import pe.edu.upc.dto.PaymentDto;

public interface IPaymentService {
	int payPremium(PaymentDto payment);
}
