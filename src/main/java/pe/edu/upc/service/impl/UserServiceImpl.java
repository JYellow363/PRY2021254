package pe.edu.upc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.upc.dto.UserDto;
import pe.edu.upc.model.Users;
import pe.edu.upc.repository.IUserRepository;
import pe.edu.upc.service.IUserService;
import pe.edu.upc.util.Constants;
import pe.edu.upc.util.RandomStringGenerator;

import javax.mail.internet.MimeMessage;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private IUserRepository userRepository;
	@Autowired
	private JavaMailSender sender;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public int loginGuardian(UserDto userLoginDto) {
		Users users = userRepository.findByUsername(userLoginDto.getUsername());
		if (users == null)
			return Constants.ERROR_EXIST;
		if (!passwordEncoder.matches(userLoginDto.getPassword(), users.getPassword()))
			return Constants.ERROR_PASSWORD;
		return users.getGuardian().getId();
	}

	@Override
	public int loginSpecialist(UserDto userLoginDto) {
		Users users = userRepository.findByUsername(userLoginDto.getUsername());
		if (users == null)
			return Constants.ERROR_EXIST;
		if (!passwordEncoder.matches(userLoginDto.getPassword(), users.getPassword()))
			return Constants.ERROR_PASSWORD;
		return users.getSpecialist().getId();
	}

	@Override
	public int restorePassword(String email) {
		Users users = userRepository.findByGuardianEmail(email);
		if (users == null)
			return Constants.ERROR_EXIST;
		else {
			String newPassword = RandomStringGenerator.getString();
			users.setPassword(passwordEncoder.encode(newPassword));
			Users usersSave = userRepository.save(users);
			if (usersSave == null)
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
