package pe.edu.upc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.upc.dto.*;
import pe.edu.upc.model.*;
import pe.edu.upc.repository.*;
import pe.edu.upc.service.IChildService;
import pe.edu.upc.util.Constants;
import pe.edu.upc.util.RandomStringGenerator;

import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChildServiceImpl implements IChildService {

	@Autowired
	private ICustomLevelListRepository customLevelListRepository;
	@Autowired
	private ILevelRecordRepository levelRecordRepository;
	@Autowired
	private IChildRepository childRepository;
	@Autowired
	private ISymptomRepository symptomRepository;
	@Autowired
	private IGuardianRepository guardianRepository;
	@Autowired
	private ILevelRepository levelRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private JavaMailSender sender;

	@Override
	public List<ChildDto> findByGuardianIdGuardian(int id) {
		List<ChildDto> children = convert(childRepository.findByGuardianId(id));
		return children;
	}

	@Override
	public ChildDto findById(int id) {
		ChildDto childDto = convert(childRepository.findById(id).get());
		return childDto;
	}

	@Transactional
	@Override
	public int save(ChildCreateDto childCreateDto) {
		Child child = convert(childCreateDto);
		Child childSave = childRepository.save(child);
		if (childSave == null)
			return Constants.ERROR_BD;
		return childSave.getId();
	}

	@Transactional
	@Override
	public int update(ChildUpdateDto childUpdateDto) {
		Child child = childRepository.findById(childUpdateDto.getId()).get();
		child = convert(child, childUpdateDto);
		Child childSave = childRepository.save(child);
		if (childSave == null)
			return Constants.ERROR_BD;
		return childSave.getId();
	}
	
	@Transactional
	@Override
	public int updateSpecialCategoryName(SpecialCategoryDto specialCategoryDto) {
		Child child = childRepository.findById(specialCategoryDto.getIdChild()).get();
		child.setSpecialCategoryName(specialCategoryDto.getName());
		Child childSave = childRepository.save(child);
		if (childSave == null)
			return Constants.ERROR_BD;
		return childSave.getId();
	}

	@Transactional
	@Override
	public int delete(int id) {
		try {
			levelRecordRepository.deleteByChildId(id);
			childRepository.deleteById(id);
		} catch (Exception e) {
			return Constants.ERROR_BD;
		}
		return Constants.SUCCESSFULLY;
	}

	@Transactional
	@Override
	public int activateSpecialist(int id) {
		Specialist specialist = new Specialist();
		Child child = childRepository.findById(id).get();
		
		if(child.getSpecialist() != null) {
			return Constants.ERROR_DUPLICATE;
		}
		
		Users users = new Users();
		users.setActive(false);
		String username = RandomStringGenerator.getString();
		users.setUsername(username);
		users.setActive(true);
		String password = RandomStringGenerator.getString();
		users.setPassword(passwordEncoder.encode(password));
		specialist.setUser(users);
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
		return childSave.getSpecialist().getId();
	}

	@Transactional
	@Override
	public int addFavoriteLevel(int id, int idLevel) {
		Child child = childRepository.findById(id).get();
		Level level = levelRepository.findById(idLevel).get();
		if (child.getFavoriteLevels().contains(level)) {
			return Constants.ERROR_DUPLICATE;
		} else {
			child.getFavoriteLevels().add(level);
			child.setFavoriteLevels(child.getFavoriteLevels());
			Child childSave = childRepository.save(child);
			if (childSave == null) {
				return Constants.ERROR_BD;
			} else {
				return Constants.SUCCESSFULLY;
			}
		}
	}

	@Transactional
	@Override
	public int deleteFavoriteLevel(AddLevelDto addLevelDto) {
		Child child = childRepository.findById(addLevelDto.getIdChild()).get();
		List<Level> newLevels = new ArrayList<Level>();
		for (Level level : child.getFavoriteLevels()) {
			if (level.getId() != addLevelDto.getIdLevel())
				newLevels.add(level);
		}
		child.setFavoriteLevels(newLevels);
		Child childSave = childRepository.save(child);
		if (childSave == null) {
			return Constants.ERROR_BD;
		} else {
			return Constants.SUCCESSFULLY;
		}
	}

	@Override
	public List<Level> listFavoriteLevels(int id) {
		Child child = childRepository.findById(id).get();
		return child.getFavoriteLevels();
	}

	@Transactional
	@Override
	public int addCustomLevelList(AddCustomLevelListDto addCustomLevelListDto) {
		Child child = childRepository.findById(addCustomLevelListDto.getId()).get();
		CustomLevelList customLevelList = new CustomLevelList(0, addCustomLevelListDto.getName(),
				new ArrayList<Level>());
		CustomLevelList customLevelListSave = customLevelListRepository.save(customLevelList);
		if (customLevelListSave == null) {
			return Constants.ERROR_BD;
		}
		child.getCustomLevelLists().add(customLevelList);
		child.setCustomLevelLists(child.getCustomLevelLists());
		Child childSave = childRepository.save(child);
		if (childSave == null) {
			return Constants.ERROR_BD;
		} else {
			return customLevelListSave.getId();
		}
	}

	@Override
	public List<CustomLevelList> listCustomLevelLists(int id) {
		Child child = childRepository.findById(id).get();
		return child.getCustomLevelLists();
	}

	@Override
	public CustomLevelList listCustomLevelListById(int id) {
		return customLevelListRepository.findById(id).get();
	}

	@Transactional
	@Override
	public int deleteCustomLevelList(int id, int idCustomLevelList) {
		try {
			Child child = childRepository.findById(id).get();
			List<CustomLevelList> customLevelLists = new ArrayList<CustomLevelList>();
			for (CustomLevelList levelList : child.getCustomLevelLists()) {
				if (levelList.getId() != idCustomLevelList)
					customLevelLists.add(levelList);
			}
			child.setCustomLevelLists(customLevelLists);
			Child childSave = childRepository.save(child);
			if (childSave == null)
				return Constants.ERROR_BD;
			customLevelListRepository.deleteById(idCustomLevelList);
		} catch (Exception e) {
			return Constants.ERROR_BD;
		}
		return Constants.SUCCESSFULLY;
	}

	private ChildDto convert(Child child) {
		ChildDto childDto = new ChildDto();
		childDto.setId(child.getId());
		childDto.setNames(child.getNames());
		childDto.setLastNames(child.getLastNames());
		childDto.setAsdLevel(child.getAsdLevel());
		childDto.setAvatar(child.getAvatar());
		childDto.setBirthday(child.getBirthday());
		childDto.setGender(child.getGender());
		childDto.setIdGuardian(child.getGuardian().getId());
		childDto.setSpecialCategoryName(child.getSpecialCategoryName());
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

	private Child convert(ChildCreateDto childCreateDto) {
		Child child = new Child();
		child.setNames(childCreateDto.getNames());
		child.setLastNames(childCreateDto.getLastNames());
		child.setAsdLevel(childCreateDto.getAsdLevel());
		child.setAvatar(childCreateDto.getAvatar());
		child.setBirthday(childCreateDto.getBirthday());
		child.setGender(childCreateDto.getGender());
		child.setSpecialCategoryName("Recreación");

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
		child.setCustomLevelLists(new ArrayList<CustomLevelList>());
		child.setSpecialist(null);
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
