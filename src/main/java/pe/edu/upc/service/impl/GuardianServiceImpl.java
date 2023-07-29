package pe.edu.upc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.upc.dto.GuardianCreateDto;
import pe.edu.upc.dto.GuardianDto;
import pe.edu.upc.dto.GuardianUpdateDto;
import pe.edu.upc.model.Guardian;
import pe.edu.upc.model.Users;
import pe.edu.upc.repository.IGuardianRepository;
import pe.edu.upc.repository.IUserRepository;
import pe.edu.upc.service.IGuardianService;
import pe.edu.upc.util.Constants;

@Service
public class GuardianServiceImpl implements IGuardianService {
	@Autowired
	private IGuardianRepository guardianRepository;
	@Autowired
	private IUserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public int save(GuardianCreateDto guardianCreateDto) {
		Users users = userRepository.findByUsername(guardianCreateDto.getUsername());
		if (users != null)
			return Constants.ERROR_DUPLICATE;

		Guardian guardian = convert(guardianCreateDto);
		guardian.setPremium(false);
		Guardian guardianSave = guardianRepository.save(guardian);
		if (guardianSave == null)
			return Constants.ERROR_BD;

		return guardianSave.getId();
	}

	@Override
	public int update(GuardianUpdateDto guardianUpdateDto) {
		Guardian guardian = guardianRepository.findById(guardianUpdateDto.getId()).get();
		if (!passwordEncoder.matches(guardianUpdateDto.getPassword(), guardian.getUser().getPassword()))
			return Constants.ERROR_PASSWORD;
		guardian = convert(guardian, guardianUpdateDto);
		Guardian guardianSave = guardianRepository.save(guardian);
		if (guardianSave == null)
			return Constants.ERROR_BD;
		return guardianSave.getId();
	}

	@Override
	public GuardianDto listByIdGuardian(int id) {
		Guardian guardian = guardianRepository.findById(id).get();
		GuardianDto guardianDto = convert(guardian);
		return guardianDto;
	}

	private Guardian convert(GuardianCreateDto guardianCreateDto) {
		Users user = new Users();
		user.setId(0);
		user.setUsername(guardianCreateDto.getUsername());
		user.setActive(true);
		user.setPassword(passwordEncoder.encode(guardianCreateDto.getPassword()));

		Guardian guardian = new Guardian();
		guardian.setId(0);
		guardian.setNames(guardianCreateDto.getNames());
		guardian.setLastNames(guardianCreateDto.getLastNames());
		guardian.setEmail(guardianCreateDto.getEmail());
		guardian.setBirthday(guardianCreateDto.getBirthday());
		guardian.setUser(user);

		return guardian;
	}

	private GuardianDto convert(Guardian guardian) {
		GuardianDto guardianDto = new GuardianDto();
		guardianDto.setBirthday(guardian.getBirthday());
		guardianDto.setEmail(guardian.getEmail());
		guardianDto.setId(guardian.getId());
		guardianDto.setLastNames(guardian.getLastNames());
		guardianDto.setNames(guardian.getNames());
		guardianDto.setIdUser(guardian.getUser().getId());
		guardianDto.setUsername(guardian.getUser().getUsername());
		guardianDto.setActive(guardian.getUser().isActive());
		guardianDto.setPremium(guardian.isPremium());
		return guardianDto;
	}

	private Guardian convert(Guardian guardian, GuardianUpdateDto guardianUpdateDto) {
		guardian.getUser().setPassword(passwordEncoder.encode(guardianUpdateDto.getNewPassword()));
		guardian.setNames(guardianUpdateDto.getNames());
		guardian.setLastNames(guardianUpdateDto.getLastNames());
		guardian.setEmail(guardianUpdateDto.getEmail());
		guardian.setBirthday(guardianUpdateDto.getBirthday());
		return guardian;
	}

}
