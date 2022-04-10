package pe.edu.upc.dto;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {
	private String cardNumber;
	private String dueDate;
	private String ccv;
	private int idGuardian;
	
	@Override
	public int hashCode() {
		return Objects.hash(cardNumber, ccv, dueDate);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PaymentDto other = (PaymentDto) obj;
		return Objects.equals(cardNumber, other.cardNumber) && Objects.equals(ccv, other.ccv)
				&& Objects.equals(dueDate, other.dueDate);
	}
	
	
}
