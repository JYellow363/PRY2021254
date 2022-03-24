package pe.edu.upc.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.upc.model.Symptom;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChildDto {
	private int idChild;
	private String names;
	private String lastNames;
	private Date birthday;
	private String gender;
	private String asdLevel;
	private String avatar;
	private int idGuardian;
	private String specialCategoryName;
	private List<Symptom> symptoms;
}
