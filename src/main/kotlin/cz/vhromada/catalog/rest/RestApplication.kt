package cz.vhromada.catalog.rest

import cz.vhromada.catalog.CatalogConfiguration
import cz.vhromada.common.web.WebConfiguration
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

/**
 * A class represents Spring boot application.
 *
 * @author Vladimir Hromada
 */
@SpringBootApplication
@EnableSwagger2
@Import(WebConfiguration::class, CatalogConfiguration::class)
class RestApplication : WebMvcConfigurer {

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE")
    }

    @Bean
    fun applicationApi(): Docket {
        val info = ApiInfoBuilder()
                .title("Catalog")
                .description("Catalog of movies, games, music and programs ")
                .version("1.0.0")
                .build()
        return Docket(DocumentationType.SWAGGER_2)
                .groupName("catalog")
                .apiInfo(info)
                .select()
                .paths(PathSelectors.regex("/catalog.*"))
                .build()
    }

}

fun main(args: Array<String>) {
    SpringApplication.run(RestApplication::class.java, *args)
}
