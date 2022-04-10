package pe.edu.upc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.upc.dto.PaymentDto;
import pe.edu.upc.dto.ResponseDto;
import pe.edu.upc.service.IPaymentService;
import pe.edu.upc.util.Constants;

@CrossOrigin
@RestController
@RequestMapping(path = "/payment")
public class PaymentController {

	@Autowired
	private IPaymentService paymentService;

	// Fake payment
	@PostMapping(path = "/payPremium", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> payPremium(@RequestBody PaymentDto payment) {
		int result = paymentService.payPremium(payment);
		ResponseDto response = new ResponseDto();
		response.setIdResponse(result);
		if (result == Constants.ERROR_NO_BALANCE) {
			response.setMessage("La tarjeta no tiene fondos");
			return ResponseEntity.ok(response);
		} else if (result == Constants.ERROR_FALSE_PAYMENT) {
			response.setMessage("Los datos no son correctos");
			return ResponseEntity.ok(response);
		} else if (result == Constants.ERROR_BD) {
			response.setMessage("Error al realizar pago");
			return ResponseEntity.ok(response);
		} else {
			response.setMessage("Pago exitoso");
			return ResponseEntity.ok(response);
		}
	}
}
