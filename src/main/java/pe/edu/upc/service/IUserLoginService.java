package pe.edu.upc.service;

import pe.edu.upc.dto.UserLoginDto;

public interface IUserLoginService {
	int login(UserLoginDto userLoginDto);
}
