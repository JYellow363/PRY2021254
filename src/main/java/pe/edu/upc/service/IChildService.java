package pe.edu.upc.service;

import java.util.List;

import pe.edu.upc.dto.AddCustomLevelListDto;
import pe.edu.upc.dto.AddLevelCustomDto;
import pe.edu.upc.dto.AddLevelDto;
import pe.edu.upc.dto.ChildCreateDto;
import pe.edu.upc.dto.ChildDto;
import pe.edu.upc.dto.ChildUpdateDto;
import pe.edu.upc.dto.SpecialCategoryDto;
import pe.edu.upc.model.CustomLevelList;
import pe.edu.upc.model.Level;

public interface IChildService {
	List<ChildDto> findByGuardianIdGuardian(int idGuardian);
	ChildDto findById(int idChild);
	int save(ChildCreateDto childCreateDto);
	int update(ChildUpdateDto childupdateDto);
	int delete(int idChild);
	int activateSpecialist(int idChild);
	int addFavoriteLevel(int idChild, int idLevel);
	List<Level> listFavoriteLevels(int idChild);
	int addCustomLevelList(AddCustomLevelListDto addCustomLevelListDto);
	List<CustomLevelList> listCustomLevelLists(int idChild);
	CustomLevelList listCustomLevelListById(int idChild);
	int deleteFavoriteLevel(AddLevelDto addLevelDto);
	int addLevelToCustomLevelList(AddLevelCustomDto addLevelCustomListDto);
	int deleteLevelinCustomLevelList(AddLevelCustomDto addLevelCustomListDto);
	int deleteCustomLevelList(int idChild, int idCustomLevelList);
	int updateSpecialCategoryName(SpecialCategoryDto specialCategoryDto);
}
