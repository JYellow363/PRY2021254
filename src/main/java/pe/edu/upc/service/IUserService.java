package pe.edu.upc.service;

import pe.edu.upc.dto.UserDto;

public interface IUserService {
	int loginGuardian(UserDto userDto);
	int loginSpecialist(UserDto userDto);
	int restorePassword(String email);
}
