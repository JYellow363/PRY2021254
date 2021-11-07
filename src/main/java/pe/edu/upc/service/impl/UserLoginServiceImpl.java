package pe.edu.upc.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.dto.UserLoginDto;
import pe.edu.upc.model.UserLogin;
import pe.edu.upc.repository.IUserLoginRepository;
import pe.edu.upc.service.IUserLoginService;
import pe.edu.upc.util.Constants;

@Service
public class UserLoginServiceImpl implements IUserLoginService {
	@Autowired
	private IUserLoginRepository userLoginRepository;

	@Override
	public int login(UserLoginDto userLoginDto) {
		Optional<UserLogin> userLogin = userLoginRepository.findByUsername(userLoginDto.getUsername());
		if(userLogin.isEmpty())
			return Constants.ERROR_EXIST;
		if(!userLogin.get().getPassword().equals(userLoginDto.getPassword()))
			return Constants.ERROR_PASSWORD;
		return	Constants.SUCCESSFULLY;
	}
	
}
