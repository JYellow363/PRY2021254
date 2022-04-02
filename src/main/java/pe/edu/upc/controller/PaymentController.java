package pe.edu.upc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.upc.dto.PaymentDto;
import pe.edu.upc.dto.ResponseDto;
import pe.edu.upc.util.ValidatePayment;

@CrossOrigin
@RestController
@RequestMapping(path = "/payment")
public class PaymentController {

	// Fake payment
	@PostMapping(path = "/payPremium", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> payPremium(@RequestBody PaymentDto payment) {
		int result = ValidatePayment.validate(payment);
		ResponseDto response = new ResponseDto();
		response.setIdResponse(result);
		if (result == 0) {
			response.setMessage("La tarjeta no tiene fondos");
			return ResponseEntity.ok(response);
		} else if (result == -1) {
			response.setMessage("Los datos no son correctos");
			return ResponseEntity.ok(response);
		} else {
			response.setMessage("Los datos no son correctos");
			return ResponseEntity.ok(response);
		}
	}
}
