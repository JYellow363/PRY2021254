package pe.edu.upc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.service.ILevelRecordService;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
	@Autowired
	private ILevelRecordService levelRecordService;
	
	int idChildren = 84;
	
	@RequestMapping("/categories")
	public String goDashBoardCategory(Model model) {
		model.addAttribute("title", "Monitoreo por Categoría");
		model.addAttribute("levelRecords",levelRecordService.listByChildrenForCategory(idChildren));
		return "dashboard";
	}
	
	@RequestMapping("/categories/{idCategory}")
	public String goDashBoardTopic(Model model, @PathVariable int idCategory) {
		model.addAttribute("title", "Monitoreo por Tema");
		model.addAttribute("levelRecords",levelRecordService.listByChildrenForTopic(idChildren, idCategory));
		return "dashboard";
	}
	
	@RequestMapping("/topics/{idTopic}")
	public String goDashBoardLevel(Model model, @PathVariable int idTopic) {
		model.addAttribute("title", "Monitoreo por Nivel");
		model.addAttribute("levelRecords",levelRecordService.listByChildrenForLevel(idChildren, idTopic));
		return "dashboard";
	}
}
