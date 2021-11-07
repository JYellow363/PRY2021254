package pe.edu.upc.service.impl;

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
		UserLogin userLogin = userLoginRepository.findByUsername(userLoginDto.getUsername());
		if(userLogin == null)
			return Constants.ERROR_EXIST;
		if(!userLogin.getPassword().equals(userLoginDto.getPassword()))
			return Constants.ERROR_PASSWORD;
		return	Constants.SUCCESSFULLY;
	}
	
}
