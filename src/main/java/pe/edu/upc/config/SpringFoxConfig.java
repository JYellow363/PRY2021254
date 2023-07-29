package pe.edu.upc.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.fasterxml.classmate.TypeResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.upc.dto.*;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxConfig {

	@Bean
	public Docket api(TypeResolver typeResolver) {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
				.securityContexts(Arrays.asList(securityContext())).securitySchemes(Arrays.asList(apiKey())).select()
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).paths(PathSelectors.any())
				.build().additionalModels(
						typeResolver.resolve(AddCustomLevelListDto.class),
						typeResolver.resolve(AddLevelCustomDto.class),
						typeResolver.resolve(AddLevelDto.class),
						typeResolver.resolve(ChildCreateDto.class),
						typeResolver.resolve(ChildDto.class),
						typeResolver.resolve(ChildUpdateDto.class),
						typeResolver.resolve(CustomLevelListUpdateDto.class),
						typeResolver.resolve(GuardianCreateDto.class),
						typeResolver.resolve(GuardianDto.class),
						typeResolver.resolve(GuardianUpdateDto.class),
						typeResolver.resolve(LevelHistoricalRecordDto.class),
						typeResolver.resolve(LevelRecordCreateDto.class),
						typeResolver.resolve(LevelRecordDto.class),
						typeResolver.resolve(LoginResponseDto.class),
						typeResolver.resolve(ObservationDto.class),
						typeResolver.resolve(ObservationUpdateDto.class),
						typeResolver.resolve(PaymentDto.class),
						typeResolver.resolve(ResponseDto.class),
						typeResolver.resolve(SpecialCategoryDto.class),
						typeResolver.resolve(SpecialistDto.class),
						typeResolver.resolve(UserDto.class)
				).apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfo("RESTful API TEApprendo", "RESTful API de la aplicación TEApprendo para mejorar el aprendizaje en niños con autismo.", "1.0.0", "",
				new Contact("GitHub", "https://github.com/JYellow363/PRY2021254", "pry2021254@gmail.com"), "", "", Collections.emptyList());
	}

	private ApiKey apiKey() {
		return new ApiKey("JWT", "Authorization", "header");
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).build();
	}

	private List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
	}
}