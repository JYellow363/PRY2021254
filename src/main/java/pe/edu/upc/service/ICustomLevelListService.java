package pe.edu.upc.service;

import pe.edu.upc.dto.AddLevelCustomDto;
import pe.edu.upc.dto.CustomLevelListUpdateDto;

public interface ICustomLevelListService {
    int addCustomLevelListLevel(AddLevelCustomDto addLevelCustomListDto);
    int deleteCustomLevelListLevel(AddLevelCustomDto addLevelCustomListDto);
    int updateCustomLevelListLevel(CustomLevelListUpdateDto customLevelListUpdateDto);
}
