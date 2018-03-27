package com.vinodh.springbootangularmongodbcrudoperations;

import com.vinodh.springbootangularmongodbcrudoperations.model.ContactInfo;
import com.vinodh.springbootangularmongodbcrudoperations.repository.ContactRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SpringBootAngularMongodbCrudOperationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAngularMongodbCrudOperationsApplication.class, args);
	}


	@Bean
	public WebMvcConfigurer webMvcConfigurer(){
			return new WebMvcConfigurer() {
				@Override
				public void addCorsMappings(CorsRegistry registry) {
					registry.addMapping("/**/*")
							.allowedOrigins("http://localhost:4200", "http://localhost:7890")
							.allowedMethods("GET", "PUT", "POST", "DELETE", "OPTIONS")
							.allowedHeaders("*");
				}
		};
	}

	/**
	 * Load Sample Contact Data
	 *
	 */
	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	public CommandLineRunner commandLineRunner(ContactRepository contactRepository){

		ContactInfo contactInfo = ContactInfo.builder()
													.id("test1234")
													.name("Vinodh Kumar")
													.email("vinodh5052@gmail.com")
													.city("Chennai")
													.address("Shollinganallur")
													.phone("123456")
											  .build();

		ContactInfo contactInfo1 = ContactInfo.builder()
												.id("test4567")
												.name("Vinodh Thimmisetty")
												.email("vinodh5@gmail.com")
												.city("Chennai1")
												.address("Shols")
												.phone("123479")
											.build();


		ContactInfo contactInfo2 = ContactInfo.builder()
												.id("test890")
												.name("Thimmisetty")
												.email("vinodh@gmail.com")
												.city("Chennai2")
												.address("Shols2")
												.phone("123479")
											  .build();

		return args -> {

			contactRepository.save(contactInfo);
			contactRepository.save(contactInfo1);
			contactRepository.save(contactInfo2);


		};

	}

}

