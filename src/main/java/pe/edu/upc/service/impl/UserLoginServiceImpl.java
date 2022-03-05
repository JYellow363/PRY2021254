package pe.edu.upc.service.impl;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pe.edu.upc.dto.UserLoginDto;
import pe.edu.upc.model.UserLogin;
import pe.edu.upc.repository.IUserLoginRepository;
import pe.edu.upc.service.IUserLoginService;
import pe.edu.upc.util.Constants;
import pe.edu.upc.util.RandomStringGenerator;

@Service
public class UserLoginServiceImpl implements IUserLoginService {
	@Autowired
	private IUserLoginRepository userLoginRepository;
	@Autowired
	private JavaMailSender sender;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public int loginGuardian(UserLoginDto userLoginDto) {
		UserLogin userLogin = userLoginRepository.findByUsername(userLoginDto.getUsername());
		if (userLogin == null)
			return Constants.ERROR_EXIST;
		if (!passwordEncoder.matches(userLoginDto.getPassword(), userLogin.getPassword()))
			return Constants.ERROR_PASSWORD;
		return userLogin.getGuardian().getIdGuardian();
	}

	@Override
	public int loginSpecialist(UserLoginDto userLoginDto) {
		UserLogin userLogin = userLoginRepository.findByUsername(userLoginDto.getUsername());
		if (userLogin == null)
			return Constants.ERROR_EXIST;
		if (!passwordEncoder.matches(userLoginDto.getPassword(), userLogin.getPassword()))
			return Constants.ERROR_PASSWORD;
		return userLogin.getSpecialist().getIdSpecialist();
	}

	@Override
	public int restorePassword(String email) {
		UserLogin userLogin = userLoginRepository.findByGuardianEmail(email);
		if (userLogin == null)
			return Constants.ERROR_EXIST;
		else {
			String newPassword = RandomStringGenerator.getString();
			userLogin.setPassword(passwordEncoder.encode(newPassword));
			UserLogin userLoginSave = userLoginRepository.save(userLogin);
			if (userLoginSave == null)
				return Constants.ERROR_BD;
			else {
				boolean send = sendEmailTool(email, newPassword);
				if (send == false)
					return Constants.ERROR_EMAIL;
				return Constants.SUCCESSFULLY;
			}
		}
	}

	private boolean sendEmailTool(String email, String newPassword) {
		boolean send = false;
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			helper.setTo(email);
			helper.setSubject("TEApprendo: Recuperación de contraseña");
			helper.setText("Su nueva contraseña es " + newPassword, false);
			sender.send(message);
			send = true;
		} catch (Exception e) {
			System.out.println(e);
		}
		return send;
	}

}
