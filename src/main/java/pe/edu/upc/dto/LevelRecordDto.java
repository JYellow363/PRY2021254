package pe.edu.upc.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.upc.model.LevelRecord;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LevelRecordDto {
	private int id;
	private String description;
	private int positiveResults;
	private int negativeResults;
	List<LevelRecord> levelRecords;
}
