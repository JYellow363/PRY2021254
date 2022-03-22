package pe.edu.upc.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.upc.model.Level;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LevelHistoricalRecordDto {
	private int idLevelRecord;
	private Level level;
	private Date date;
	private boolean isSuccessful;
}
