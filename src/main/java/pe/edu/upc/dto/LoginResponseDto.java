package pe.edu.upc.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class LoginResponseDto {
	private int idResponse;
	private String token;
	
	private int idGuardian;
	private int idUserLogin;
	private String username;
	private String email;
	private String names;
	private String lastNames;
	private Date birthday;
	private boolean premium;
	
	public LoginResponseDto(int status, String token, GuardianDto guardianDto) {
		setIdResponse(status);
		setToken(token);
		setIdGuardian(guardianDto.getIdGuardian());
		setIdUserLogin(guardianDto.getIdUserLogin());
		setUsername(guardianDto.getUsername());
		setEmail(guardianDto.getEmail());
		setNames(guardianDto.getNames());
		setLastNames(guardianDto.getLastNames());
		setBirthday(guardianDto.getBirthday());
		setPremium(guardianDto.isPremium());
	}
}

