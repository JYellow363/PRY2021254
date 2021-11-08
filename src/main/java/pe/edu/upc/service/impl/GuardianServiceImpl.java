package pe.edu.upc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.dto.GuardianCreateDto;
import pe.edu.upc.dto.GuardianDto;
import pe.edu.upc.model.Guardian;
import pe.edu.upc.model.UserLogin;
import pe.edu.upc.repository.IGuardianRepository;
import pe.edu.upc.repository.IUserLoginRepository;
import pe.edu.upc.service.IGuardianService;
import pe.edu.upc.util.Constants;

@Service
public class GuardianServiceImpl implements IGuardianService {
	@Autowired
	private IGuardianRepository guardianRepository;
	@Autowired
	private IUserLoginRepository userLoginRepository;

	@Override
	public int save(GuardianCreateDto guardianCreateDto) {
		UserLogin userLogin = userLoginRepository.findByUsername(guardianCreateDto.getUsername());
		if (userLogin != null)
			return Constants.ERROR_DUPLICATE;

		Guardian guardian = convert(guardianCreateDto);
		Guardian guardianSave = guardianRepository.save(guardian);
		if (guardianSave == null)
			return Constants.ERROR_BD;

		return Constants.SUCCESSFULLY;
	}

	@Override
	public GuardianDto listByIdGuardian(int idGuardian) {
		Guardian guardian = guardianRepository.findById(idGuardian).get();
		GuardianDto guardianDto = convert(guardian);
		return guardianDto;
	}

	private Guardian convert(GuardianCreateDto guardianCreateDto) {
		UserLogin userLogin = new UserLogin();
		userLogin.setIdUserLogin(0);
		userLogin.setUsername(guardianCreateDto.getUsername());
		userLogin.setActive(true);
		userLogin.setPassword(guardianCreateDto.getPassword());

		Guardian guardian = new Guardian();
		guardian.setIdGuardian(0);
		guardian.setNames(guardianCreateDto.getNames());
		guardian.setLastNames(guardianCreateDto.getLastNames());
		guardian.setEmail(guardianCreateDto.getEmail());
		guardian.setBirthday(guardianCreateDto.getBirthday());
		guardian.setUserLogin(userLogin);

		return guardian;
	}
	
	private GuardianDto convert(Guardian guardian) {
		GuardianDto guardianDto = new GuardianDto();
		guardianDto.setBirthday(guardian.getBirthday());
		guardianDto.setEmail(guardian.getEmail());
		guardianDto.setIdGuardian(guardian.getIdGuardian());
		guardianDto.setLastNames(guardian.getLastNames());
		guardianDto.setNames(guardian.getNames());
		guardianDto.setIdUserLogin(guardian.getUserLogin().getIdUserLogin());
		guardianDto.setUsername(guardian.getUserLogin().getUsername());
		guardianDto.setActive(guardian.getUserLogin().isActive());
		return guardianDto;
	}

}
