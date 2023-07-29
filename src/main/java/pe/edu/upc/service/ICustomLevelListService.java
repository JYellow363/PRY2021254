package pe.edu.upc.service;

import pe.edu.upc.dto.AddLevelCustomDto;
import pe.edu.upc.dto.CustomLevelListUpdateDto;

public interface ICustomLevelListService {
    int addLevelToCustomLevelList(AddLevelCustomDto addLevelCustomListDto);
    int deleteLevelinCustomLevelList(AddLevelCustomDto addLevelCustomListDto);
    int updateCustomLevelList(CustomLevelListUpdateDto customLevelListUpdateDto);
}
