package pe.edu.upc.service;

import pe.edu.upc.dto.UserLoginDto;

public interface IUserLoginService {
	int loginGuardian(UserLoginDto userLoginDto);
	int loginSpecialist(UserLoginDto userLoginDto);
	int restorePassword(String email);
}
