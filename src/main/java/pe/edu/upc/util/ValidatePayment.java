package pe.edu.upc.util;

import pe.edu.upc.dto.PaymentDto;

public class ValidatePayment {

	private static PaymentDto truePayment = new PaymentDto("4558896523657415", "05/23", "142");
	private static PaymentDto noBalancePayment = new PaymentDto("4558896596945415", "06/23", "158");

	public static int validate(PaymentDto payment) {
		if (truePayment.equals(payment)) {
			return 1;
		} else if (noBalancePayment.equals(payment)) {
			return 0;
		} else
			return -1;

	}

}
