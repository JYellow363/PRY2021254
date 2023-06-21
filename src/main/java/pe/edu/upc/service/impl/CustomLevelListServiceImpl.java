package pe.edu.upc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.dto.AddLevelCustomDto;
import pe.edu.upc.dto.CustomLevelListUpdateDto;
import pe.edu.upc.model.CustomLevelList;
import pe.edu.upc.model.Level;
import pe.edu.upc.repository.ICustomLevelListRepository;
import pe.edu.upc.repository.ILevelRepository;
import pe.edu.upc.service.ICustomLevelListService;
import pe.edu.upc.util.Constants;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomLevelListServiceImpl implements ICustomLevelListService {

    @Autowired
    private ICustomLevelListRepository customLevelListRepository;

    @Autowired
    private ILevelRepository levelRepository;

    @Transactional
    @Override
    public int addLevelToCustomLevelList(AddLevelCustomDto addLevelCustomListDto) {
        CustomLevelList customLevelList = customLevelListRepository
                .findById(addLevelCustomListDto.getIdCustomLevelList()).get();
        Level level = levelRepository.findById(addLevelCustomListDto.getIdLevel()).get();
        if (customLevelList.getLevels().contains(level)) {
            return Constants.ERROR_DUPLICATE;
        } else {
            customLevelList.getLevels().add(level);
            customLevelList.setLevels(customLevelList.getLevels());
            CustomLevelList customLevelListSave = customLevelListRepository.save(customLevelList);
            if (customLevelListSave == null) {
                return Constants.ERROR_BD;
            } else {
                return Constants.SUCCESSFULLY;
            }
        }
    }

    @Transactional
    @Override
    public int deleteLevelinCustomLevelList(AddLevelCustomDto addLevelCustomListDto) {
        CustomLevelList customLevelList = customLevelListRepository
                .findById(addLevelCustomListDto.getIdCustomLevelList()).get();
        List<Level> newLevels = new ArrayList<Level>();
        for (Level level : customLevelList.getLevels()) {
            if (level.getIdLevel() != addLevelCustomListDto.getIdLevel())
                newLevels.add(level);
        }
        customLevelList.setLevels(newLevels);
        CustomLevelList customLevelListSave = customLevelListRepository.save(customLevelList);
        if (customLevelListSave == null) {
            return Constants.ERROR_BD;
        } else {
            return Constants.SUCCESSFULLY;
        }
    }

    @Transactional
    @Override
    public int updateCustomLevelList(CustomLevelListUpdateDto customLevelListUpdateDto) {
        CustomLevelList customLevelList = customLevelListRepository.findById(customLevelListUpdateDto.getIdCustomLevelList()).get();
        customLevelList.setName(customLevelListUpdateDto.getName());
        CustomLevelList customLevelListSave = customLevelListRepository.save(customLevelList);
        if (customLevelListSave == null) {
            return Constants.ERROR_BD;
        } else {
            return Constants.SUCCESSFULLY;
        }
    }
}
