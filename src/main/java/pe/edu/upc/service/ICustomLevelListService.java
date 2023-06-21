package pe.edu.upc.service;

import pe.edu.upc.dto.AddLevelCustomDto;
import pe.edu.upc.dto.CustomLevelListUpdateDto;

public interface ICustomLevelListService {
    int addLevelToCustomLevelList(AddLevelCustomDto addLevelCustomListDto);
    int deleteLevelinCustomLevelList(AddLevelCustomDto idCustomLevelList);
    int updateCustomLevelList(CustomLevelListUpdateDto customLevelListUpdateDto);
}
