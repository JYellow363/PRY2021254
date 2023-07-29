package pe.edu.upc.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChildUpdateDto {
	private int id;
	private String names;
	private String lastNames;
	private Date birthday;
	private String gender;
	private String asdLevel;
	private String avatar;
	private int[] symptoms;
}
