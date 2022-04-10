package pe.edu.upc.util;

import pe.edu.upc.dto.PaymentDto;

public class ValidatePayment {

	private static PaymentDto truePayment = new PaymentDto("4558896523657415", "05/23", "142", 0);
	private static PaymentDto noBalancePayment = new PaymentDto("4558896596945415", "06/23", "158", 0);

	public static int validate(PaymentDto payment) {
		if (truePayment.equals(payment)) {
			return 1;
		} else if (noBalancePayment.equals(payment)) {
			return Constants.ERROR_NO_BALANCE;
		} else
			return Constants.ERROR_FALSE_PAYMENT;

	}

}
