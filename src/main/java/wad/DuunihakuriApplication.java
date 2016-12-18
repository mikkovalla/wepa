/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"wad"})
@EntityScan("wad.domain")
@EnableJpaRepositories("wad.repository")
public class DuunihakuriApplication {

    public static void main(String[] args) {
        SpringApplication.run(DuunihakuriApplication.class, args);
    }
}
