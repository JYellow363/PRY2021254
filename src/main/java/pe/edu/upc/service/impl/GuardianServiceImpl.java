package pe.edu.upc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pe.edu.upc.dto.GuardianCreateDto;
import pe.edu.upc.dto.GuardianDto;
import pe.edu.upc.dto.GuardianUpdateDto;
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
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public int save(GuardianCreateDto guardianCreateDto) {
		UserLogin userLogin = userLoginRepository.findByUsername(guardianCreateDto.getUsername());
		if (userLogin != null)
			return Constants.ERROR_DUPLICATE;

		Guardian guardian = convert(guardianCreateDto);
		guardian.setPremium(false);
		Guardian guardianSave = guardianRepository.save(guardian);
		if (guardianSave == null)
			return Constants.ERROR_BD;

		return guardianSave.getIdGuardian();
	}

	@Override
	public int update(GuardianUpdateDto guardianUpdateDto) {
		Guardian guardian = guardianRepository.findById(guardianUpdateDto.getIdGuardian()).get();
		if (!passwordEncoder.matches(guardianUpdateDto.getPassword(), guardian.getUserLogin().getPassword()))
			return Constants.ERROR_PASSWORD;
		guardian = convert(guardian, guardianUpdateDto);
		Guardian guardianSave = guardianRepository.save(guardian);
		if (guardianSave == null)
			return Constants.ERROR_BD;
		return guardianSave.getIdGuardian();
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
		userLogin.setPassword(passwordEncoder.encode(guardianCreateDto.getPassword()));

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
		guardianDto.setPremium(guardian.isPremium());
		return guardianDto;
	}

	private Guardian convert(Guardian guardian, GuardianUpdateDto guardianUpdateDto) {
		guardian.getUserLogin().setPassword(passwordEncoder.encode(guardianUpdateDto.getNewPassword()));
		guardian.setNames(guardianUpdateDto.getNames());
		guardian.setLastNames(guardianUpdateDto.getLastNames());
		guardian.setEmail(guardianUpdateDto.getEmail());
		guardian.setBirthday(guardianUpdateDto.getBirthday());
		return guardian;
	}

}
