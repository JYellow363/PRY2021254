package pe.edu.upc.service;

import pe.edu.upc.dto.*;
import pe.edu.upc.model.CustomLevelList;
import pe.edu.upc.model.Level;

import java.util.List;

public interface IChildService {
	List<ChildDto> findByGuardianIdGuardian(int id);
	ChildDto findById(int id);
	int save(ChildCreateDto childCreateDto);
	int update(ChildUpdateDto childupdateDto);
	int delete(int id);
	int activateSpecialist(int id);
	int addFavoriteLevel(int id, int idLevel);
	List<Level> listFavoriteLevels(int id);
	int addCustomLevelList(AddCustomLevelListDto addCustomLevelListDto);
	List<CustomLevelList> listCustomLevelLists(int id);
	CustomLevelList listCustomLevelListById(int id);
	int deleteFavoriteLevel(AddLevelDto addLevelDto);
	int deleteCustomLevelList(int id, int idCustomLevelList);
	int updateSpecialCategoryName(SpecialCategoryDto specialCategoryDto);
}
