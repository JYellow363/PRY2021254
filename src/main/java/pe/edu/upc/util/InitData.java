package pe.edu.upc.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.model.Category;
import pe.edu.upc.model.Level;
import pe.edu.upc.model.Symptom;
import pe.edu.upc.model.Topic;
import pe.edu.upc.service.ICategoryService;
import pe.edu.upc.service.ILevelService;
import pe.edu.upc.service.ISymptomService;
import pe.edu.upc.service.ITopicService;

import java.util.List;

@Service
public class InitData {

    @Autowired
    private ISymptomService symptomService;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private ITopicService topicService;
    @Autowired
    private ILevelService levelService;

    public void create() {
        createSymptoms();
        createLevels();
    }

    private void createSymptoms() {
        List<Symptom> symptoms = symptomService.list();
        if(symptoms.size() == 0) {
            symptomService.save(new Symptom(0, "No verbal"));
            symptomService.save(new Symptom(0, "Deficiencia motriz"));
            symptomService.save(new Symptom(0, "Comportamientos repetitivos"));
            symptomService.save(new Symptom(0, "Escaso o nulo contacto visual"));
            symptomService.save(new Symptom(0, "Hipersensibilidad auditiva"));
            symptomService.save(new Symptom(0, "Hipersensibilidad sensorial"));
            symptomService.save(new Symptom(0, "Déficit de atención"));
        }
    }

    private void createLevels() {
        List<Category> categories = categoryService.findAll();

        Category category = new Category();
        Topic topic = new Topic();
        if (categories.size() == 0) {
            category = categoryService.save(new Category(0, "Matemáticas"));
            topic = topicService.save(new Topic(0, "Aprendiendo a contar", category));
            levelService.save(new Level(0, "¿Cuántas botellas de leche quedan?", topic, "https://www.youtube.com/"));
            levelService.save(new Level(0, "¿Cuántos libros hay en la mesa?", topic, "https://www.youtube.com/"));
            levelService.save(new Level(0, "¿Cuántas pelotas metieron gol?", topic, "https://www.youtube.com/"));
            topic = topicService.save(new Topic(0, "Aprendiendo a sumar", category));
            levelService.save(new Level(0, "Por implementar", topic, "https://www.youtube.com/"));
            levelService.save(new Level(0, "Por implementar", topic, "https://www.youtube.com/"));
            levelService.save(new Level(0, "Por implementar", topic, "https://www.youtube.com/"));
            levelService.save(new Level(0, "Por implementar", topic, "https://www.youtube.com/"));
            levelService.save(new Level(0, "Por implementar", topic, "https://www.youtube.com/"));
            levelService.save(new Level(0, "Por implementar", topic, "https://www.youtube.com/"));
            levelService.save(new Level(0, "Por implementar", topic, "https://www.youtube.com/"));
            levelService.save(new Level(0, "Por implementar", topic, "https://www.youtube.com/"));
            levelService.save(new Level(0, "Por implementar", topic, "https://www.youtube.com/"));
            levelService.save(new Level(0, "Por implementar", topic, "https://www.youtube.com/"));
            levelService.save(new Level(0, "Por implementar", topic, "https://www.youtube.com/"));
            levelService.save(new Level(0, "Por implementar", topic, "https://www.youtube.com/"));
            levelService.save(new Level(0, "Por implementar", topic, "https://www.youtube.com/"));

            category = categoryService.save(new Category(0, "Comunicaciones"));
            topic = topicService.save(new Topic(0, "Aprendiendo frutas y verduras", category));
            levelService.save(new Level(0, "¿Reconoces qué fruta es?", topic, "https://www.youtube.com/"));
            levelService.save(new Level(0, "¿Reconoces qué verdura es?", topic, "https://www.youtube.com/"));
            levelService.save(new Level(0, "¿Es una fruta o una verdura?", topic, "https://www.youtube.com/"));
            topic = topicService.save(new Topic(0, "Aprendiendo sobre los alimentos", category));
            levelService.save(new Level(0, "Por implementar", topic, "https://www.youtube.com/"));
            levelService.save(new Level(0, "Por implementar", topic, "https://www.youtube.com/"));
            levelService.save(new Level(0, "Por implementar", topic, "https://www.youtube.com/"));
            levelService.save(new Level(0, "Por implementar", topic, "https://www.youtube.com/"));
            levelService.save(new Level(0, "Por implementar", topic, "https://www.youtube.com/"));
            levelService.save(new Level(0, "Por implementar", topic, "https://www.youtube.com/"));
            levelService.save(new Level(0, "Por implementar", topic, "https://www.youtube.com/"));
            levelService.save(new Level(0, "Por implementar", topic, "https://www.youtube.com/"));
            levelService.save(new Level(0, "Por implementar", topic, "https://www.youtube.com/"));
            levelService.save(new Level(0, "Por implementar", topic, "https://www.youtube.com/"));
            levelService.save(new Level(0, "Por implementar", topic, "https://www.youtube.com/"));
            levelService.save(new Level(0, "Por implementar", topic, "https://www.youtube.com/"));
            levelService.save(new Level(0, "Por implementar", topic, "https://www.youtube.com/"));

            category = categoryService.save(new Category(0, "Personal Social"));
            topic = topicService.save(new Topic(0, "Identificando las emociones", category));
            levelService.save(new Level(0, "¿Cómo se siente el niño?", topic, "https://www.youtube.com/"));
            levelService.save(new Level(0, "Los sentimientos y los valores", topic, "https://www.youtube.com/"));
            levelService.save(new Level(0, "¿Es una emoción negativa o positiva?", topic, "https://www.youtube.com/"));
            topic = topicService.save(new Topic(0, "Aprendiendo los valores", category));
            levelService.save(new Level(0, "Por implementar", topic, "https://www.youtube.com/"));
            levelService.save(new Level(0, "Por implementar", topic, "https://www.youtube.com/"));
            levelService.save(new Level(0, "Por implementar", topic, "https://www.youtube.com/"));
            levelService.save(new Level(0, "Por implementar", topic, "https://www.youtube.com/"));
            levelService.save(new Level(0, "Por implementar", topic, "https://www.youtube.com/"));
            levelService.save(new Level(0, "Por implementar", topic, "https://www.youtube.com/"));
            levelService.save(new Level(0, "Por implementar", topic, "https://www.youtube.com/"));
            levelService.save(new Level(0, "Por implementar", topic, "https://www.youtube.com/"));
            levelService.save(new Level(0, "Por implementar", topic, "https://www.youtube.com/"));
            levelService.save(new Level(0, "Por implementar", topic, "https://www.youtube.com/"));
            levelService.save(new Level(0, "Por implementar", topic, "https://www.youtube.com/"));
            levelService.save(new Level(0, "Por implementar", topic, "https://www.youtube.com/"));
            levelService.save(new Level(0, "Por implementar", topic, "https://www.youtube.com/"));

            category = categoryService.save(new Category(0, "Ciencia y Tecnología"));
            topic = topicService.save(new Topic(0, "La Tierra, el Sol y la Luna", category));
            levelService.save(new Level(0, "Conociendo a nuestro planeta", topic, "https://www.youtube.com/"));
            levelService.save(new Level(0, "Exploremos el Sistema Solar", topic, "https://www.youtube.com/"));
            levelService.save(new Level(0, "El ecosistema del planeta", topic, "https://www.youtube.com/"));
            topic = topicService.save(new Topic(0, "Conozcamos a los dinosaurios", category));
            levelService.save(new Level(0, "Por implementar", topic, "https://www.youtube.com/"));
        }
    }

}
