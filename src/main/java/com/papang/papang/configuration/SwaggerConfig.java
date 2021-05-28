package com.papang.papang.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     * 스웨거 API 문서 생성
     * @author gunkim
     * @return Docket
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(this.swaggerInfo()) //api 정보 등록
                .select()
                .apis(RequestHandlerSelectors.any())
                .build()
                .useDefaultResponseMessages(true);
    }

    /**
     * 스웨거 정보
     * @author gunkim
     * @return ApiInfo
     */
    private ApiInfo swaggerInfo(){
        return new ApiInfoBuilder()
                .title("PAPANG API 문서") //문서 제목
                .description("PAPANG 프로젝트입니다.") //문서 설명
                .version("1.0.0") //버전
                .build();
    }
}
