package pe.edu.upc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.dto.ChildDto;
import pe.edu.upc.dto.SpecialistDto;
import pe.edu.upc.model.Child;
import pe.edu.upc.model.Specialist;
import pe.edu.upc.model.Symptom;
import pe.edu.upc.repository.ISpecialistRepository;
import pe.edu.upc.service.ISpecialistService;

import java.util.ArrayList;

@Service
public class SpecialistServiceImpl implements ISpecialistService {

	@Autowired
	private ISpecialistRepository specialistRepository;

	@Override
	public SpecialistDto listByIdSpecialist(int id) {
		Specialist specialist = specialistRepository.findById(id).get();
		SpecialistDto specialistDto = convert(specialist);
		specialistDto.setChild(convert(specialist.getChild()));
		return specialistDto;
	}

	@Override
	public SpecialistDto listByIdChild(int id) {
		SpecialistDto specialistDto = convert(specialistRepository.findByChildId(id));
		return specialistDto;
	}

	private SpecialistDto convert(Specialist specialist) {
		SpecialistDto specialistDto = new SpecialistDto();
		specialistDto.setId(specialist.getId());
		specialistDto.setNames(specialist.getNames());
		specialistDto.setLastNames(specialist.getLastNames());
		specialistDto.setUsername(specialist.getUser().getUsername());
		return specialistDto;
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
		childDto.setSymptoms(new ArrayList<Symptom>());
		for (int i = 0; i < child.getSymptoms().size(); i++) {
			childDto.getSymptoms().add(child.getSymptoms().get(i));
		}
		return childDto;
	}

}
