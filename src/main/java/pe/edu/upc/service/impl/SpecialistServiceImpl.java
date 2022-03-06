package pe.edu.upc.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.dto.ChildDto;
import pe.edu.upc.dto.SpecialistDto;
import pe.edu.upc.model.Child;
import pe.edu.upc.model.Specialist;
import pe.edu.upc.model.Symptom;
import pe.edu.upc.repository.ISpecialistRepository;
import pe.edu.upc.service.ISpecialistService;

@Service
public class SpecialistServiceImpl implements ISpecialistService {

	@Autowired
	private ISpecialistRepository specialistRepository;

	@Override
	public SpecialistDto listByIdSpecialist(int idSpecialist) {
		Specialist specialist = specialistRepository.findById(idSpecialist).get();
		SpecialistDto specialistDto = convert(specialist);
		specialistDto.setChild(convert(specialist.getChild()));
		return specialistDto;
	}

	@Override
	public SpecialistDto listByIdChild(int idChild) {
		SpecialistDto specialistDto = convert(specialistRepository.findByChildIdChild(idChild));
		return specialistDto;
	}

	private SpecialistDto convert(Specialist specialist) {
		SpecialistDto specialistDto = new SpecialistDto();
		specialistDto.setIdSpecialist(specialist.getIdSpecialist());
		specialistDto.setNames(specialist.getNames());
		specialistDto.setLastNames(specialist.getLastNames());
		specialistDto.setUsername(specialist.getUserLogin().getUsername());
		return specialistDto;
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

}
