package pe.edu.upc.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pe.edu.upc.dto.ChildCreateDto;
import pe.edu.upc.dto.ChildDto;
import pe.edu.upc.dto.ChildUpdateDto;
import pe.edu.upc.model.Child;
import pe.edu.upc.model.Guardian;
import pe.edu.upc.model.Level;
import pe.edu.upc.model.Specialist;
import pe.edu.upc.model.Symptom;
import pe.edu.upc.model.Topic;
import pe.edu.upc.model.UserLogin;
import pe.edu.upc.repository.IChildRepository;
import pe.edu.upc.repository.IGuardianRepository;
import pe.edu.upc.repository.ISymptomRepository;
import pe.edu.upc.service.IChildService;
import pe.edu.upc.util.Constants;
import pe.edu.upc.util.RandomStringGenerator;

@Service
public class ChildServiceImpl implements IChildService {

	@Autowired
	private IChildRepository childRepository;
	@Autowired
	private ISymptomRepository symptomRepository;
	@Autowired
	private IGuardianRepository guardianRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private JavaMailSender sender;

	@Override
	public List<ChildDto> findByGuardianIdGuardian(int idGuardian) {
		List<ChildDto> children = convert(childRepository.findByGuardianIdGuardian(idGuardian));
		return children;
	}

	@Override
	public ChildDto findById(int idChild) {
		ChildDto childDto = convert(childRepository.findById(idChild).get());
		return childDto;
	}

	@Transactional
	@Override
	public int save(ChildCreateDto childCreateDto) {
		Child child = convert(childCreateDto);
		Child childSave = childRepository.save(child);
		if (childSave == null)
			return Constants.ERROR_BD;
		return childSave.getIdChild();
	}

	@Transactional
	@Override
	public int update(ChildUpdateDto childUpdateDto) {
		Child child = childRepository.findById(childUpdateDto.getIdChild()).get();
		child = convert(child, childUpdateDto);
		Child childSave = childRepository.save(child);
		if (childSave == null)
			return Constants.ERROR_BD;
		return childSave.getIdChild();
	}

	@Transactional
	@Override
	public int delete(int idChild) {
		try {
			childRepository.deleteById(idChild);
		} catch (Exception e) {
			return Constants.ERROR_BD;
		}
		return Constants.SUCCESSFULLY;
	}

	private Child convert(ChildCreateDto childCreateDto) {
		Child child = new Child();
		child.setNames(childCreateDto.getNames());
		child.setLastNames(childCreateDto.getLastNames());
		child.setAsdLevel(childCreateDto.getAsdLevel());
		child.setAvatar(childCreateDto.getAvatar());
		child.setBirthday(childCreateDto.getBirthday());
		child.setGender(childCreateDto.getGender());

		List<Symptom> symptoms = new ArrayList<Symptom>();
		Symptom symptom = new Symptom();
		for (int i = 0; i < childCreateDto.getSymptoms().length; i++) {
			symptom = symptomRepository.findById(childCreateDto.getSymptoms()[i]).get();
			symptoms.add(symptom);
		}
		child.setSymptoms(symptoms);
		Guardian guardian = guardianRepository.findById(childCreateDto.getIdGuardian()).get();
		child.setGuardian(guardian);
		child.setFavoriteLevels(new ArrayList<Level>());
		child.setFavoriteTopics(new ArrayList<Topic>());
		child.setSpecialist(null);
		return child;
	}

	@Override
	public int activateSpecialist(int idChild) {
		Specialist specialist = new Specialist();
		Child child = childRepository.findById(idChild).get();
		UserLogin userLogin = new UserLogin();
		userLogin.setActive(false);
		String username = RandomStringGenerator.getString();
		userLogin.setUsername(username);
		String password = RandomStringGenerator.getString();
		userLogin.setPassword(passwordEncoder.encode(password));
		specialist.setUserLogin(userLogin);
		specialist.setLastNames("");
		specialist.setNames("");
		specialist.setChild(child);
		child.setSpecialist(specialist);
		Child childSave = childRepository.save(child);
		if (childSave == null) {
			return Constants.ERROR_BD;
		} else {
			boolean send = sendEmailTool(child.getGuardian().getEmail(), username, password);
			if (send == false)
				return Constants.ERROR_EMAIL;
		}
		return childSave.getSpecialist().getIdSpecialist();
	}

	private ChildDto convert(Child child) {
		ChildDto childDto = new ChildDto();
		childDto.setIdChild(child.getIdChild());
		childDto.setNames(child.getNames());
		childDto.setLastNames(child.getLastNames());
		childDto.setAsdLevel(child.getAsdLevel());
		childDto.setAvatar(child.getAvatar());
		childDto.setBirthday(child.getBirthday());
		childDto.setGender(child.getGender());
		childDto.setIdGuardian(child.getGuardian().getIdGuardian());
		childDto.setSymptoms(new ArrayList<Symptom>());
		for (int i = 0; i < child.getSymptoms().size(); i++) {
			childDto.getSymptoms().add(child.getSymptoms().get(i));
		}
		return childDto;
	}

	private List<ChildDto> convert(List<Child> children) {
		List<ChildDto> childrenDto = new ArrayList<ChildDto>();
		for (int i = 0; i < children.size(); i++)
			childrenDto.add(convert(children.get(i)));
		return childrenDto;
	}

	private Child convert(Child child, ChildUpdateDto childUpdateDto) {
		child.setNames(childUpdateDto.getNames());
		child.setLastNames(childUpdateDto.getLastNames());
		child.setAsdLevel(childUpdateDto.getAsdLevel());
		child.setAvatar(childUpdateDto.getAvatar());
		child.setBirthday(childUpdateDto.getBirthday());
		child.setGender(childUpdateDto.getGender());
		System.out.println(childUpdateDto.getSymptoms().length);
		List<Symptom> symptoms = new ArrayList<Symptom>();
		Symptom symptom = new Symptom();
		for (int i = 0; i < childUpdateDto.getSymptoms().length; i++) {
			symptom = symptomRepository.findById(childUpdateDto.getSymptoms()[i]).get();
			symptoms.add(symptom);
		}
		child.setSymptoms(symptoms);
		System.out.println(child.getSymptoms().size());
		return child;
	}

	private boolean sendEmailTool(String email, String username, String password) {
		boolean send = false;
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			helper.setTo(email);
			helper.setSubject("TEApprendo: Credenciales de especialista");
			helper.setText("Usuario Especialista: " + username + ". Contraseña especialista: " + password + ".", false);
			sender.send(message);
			send = true;
		} catch (Exception e) {
			System.out.println(e);
		}
		return send;
	}
}
