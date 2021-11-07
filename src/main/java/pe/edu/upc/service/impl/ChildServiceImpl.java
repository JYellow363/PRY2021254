package pe.edu.upc.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.dto.ChildCreateDto;
import pe.edu.upc.dto.ChildDto;
import pe.edu.upc.model.Child;
import pe.edu.upc.model.Guardian;
import pe.edu.upc.model.Level;
import pe.edu.upc.model.Specialist;
import pe.edu.upc.model.Symptom;
import pe.edu.upc.model.Topic;
import pe.edu.upc.model.UserLogin;
import pe.edu.upc.repository.IChildRepository;
import pe.edu.upc.repository.IGuardianRepository;
import pe.edu.upc.repository.ISpecialistRepository;
import pe.edu.upc.repository.ISymptomRepository;
import pe.edu.upc.service.IChildService;
import pe.edu.upc.util.Constants;

@Service
public class ChildServiceImpl implements IChildService {

	@Autowired
	private IChildRepository childRepository;

	@Autowired
	private ISymptomRepository symptomRepository;

	@Autowired
	private IGuardianRepository guardianRepository;
	
	@Autowired
	private ISpecialistRepository specialistRepository;

	@Override
	public List<ChildDto> findByGuardianIdGuardian(int idGuardian) {
		return null;
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
		Specialist specialist = childSave.getSpecialist();
		specialist.setChild(childSave);
		Specialist specialistSave = specialistRepository.save(specialist);
		if (specialistSave == null)
			return Constants.ERROR_BD;
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
		child.setSymptoms(new ArrayList<Symptom>());

		Symptom symptom = new Symptom();
		for (int i = 0; i < childCreateDto.getSymptoms().length; i++) {
			symptom = symptomRepository.findById(childCreateDto.getSymptoms()[i]).get();
			child.getSymptoms().add(symptom);
		}

		Guardian guardian = guardianRepository.findById(childCreateDto.getIdGuardian()).get();
		child.setGuardian(guardian);
		child.setFavoriteLevels(new ArrayList<Level>());
		child.setFavoriteTopics(new ArrayList<Topic>());
		Specialist specialist = new Specialist();
		UserLogin userLogin = new UserLogin();
		userLogin.setActive(false);
		userLogin.setUsername(guardian.getUserLogin().getUsername() + "_esp");
		userLogin.setPassword(guardian.getUserLogin().getUsername() + "_esp");
		specialist.setUserLogin(userLogin);
		specialist.setLastNames("");
		specialist.setNames("");
		child.setSpecialist(specialist);
		return child;
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
		return childDto;
	}

	private List<ChildDto> convert(List<Child> children) {
		List<ChildDto> childrenDto = new ArrayList<ChildDto>();
		for (int i = 0; i < children.size(); i++)
			childrenDto.add(convert(children.get(i)));
		return childrenDto;
	}
}
