package co.com.jgs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket api(){
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("co.com.jgs"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("API de persistencia JGS")
				.description("Contiene todos los servicios de acceso a la persistencia del aplicativo JGS, centralizando en \n"
						   + "este API de servicios toda la persistencia y consultas que pueden lanzarse desde el aplicativo.")
				.version("1.0.0-BETA")
				.contact(new Contact("GoTa Systems", null, "gustavoovalle470@gmail.com"))
				.build();
	}
}
