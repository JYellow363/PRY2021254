package pe.edu.upc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GuardianDto {
	
	private int id;
	private String names;
	private String lastNames;
	private Date birthday;
	private String email;
	private int idUser;
	private String username;
	private boolean isActive;
	private boolean premium;
}
