package com.pesona.group.audit;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@OpenAPIDefinition(info = @Info(
        title = "Audit Service API",
        version = "1.0",
        description = "Documentation Audit Service API v1.0",
        contact = @Contact(
                name = "Pesona Group",
                url = "https://www.pesonateknologi.com",
                email = "pesonateknologi@gmail.com"
        ),
        license = @License(name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0"))
)
public class AuditServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuditServiceApplication.class, args);
    }

}
