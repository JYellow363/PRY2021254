package pe.edu.upc.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GuardianUpdateDto {
	private int idGuardian;
	private String names;
	private String lastNames;
	private Date birthday;
	private String email;
	private String password;
	private String newPassword;
}
