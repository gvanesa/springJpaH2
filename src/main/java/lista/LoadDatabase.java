package lista;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(PrecioRepositorio repositorio){
        return args -> {
            log.info("Preloading" + repositorio.save(new Precio("bananas",120)));
            log.info("Preloading" + repositorio.save(new Precio("papas",50)));
            log.info("Preloading" + repositorio.save(new Precio("cebollas",60)));
            log.info("Preloading" + repositorio.save(new Precio("tomate",55)));

        };
    }
}
