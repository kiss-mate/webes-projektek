package hu.kissmt.webesrestapi.entities;

import hu.kissmt.webesrestapi.dataaccess.IOrderDAO;
import hu.kissmt.webesrestapi.dataaccess.IProductDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(IOrderDAO orderRepo, IProductDAO productRepo) {



        var p1 = new Product("34236KDK", "Brake", 10.0, 15.0);
        var p2 = new Product("DFGJ3634", "Tyre", 2.9, 4.3);
        var p3 = new Product("432352BDF", "Handle", 10.0, 15.0);
        var p4 = new Product("8SDLG926", "Bag", 5.0, 7.0);
        var p5 = new Product("999SDSJ", "Rack", 3.5, 4.6);
        var p6 = new Product("999SDSJ", "Phoneholder", 0.9, 1.2);

        var pa1 = new HashMap<String, String>();
        pa1.put(p1.getProductName(),"1");
        pa1.put(p2.getProductName(),"1");

        var pa2 = new HashMap<String, String>();
        pa2.put(p3.getProductName(),"1");
        pa2.put(p4.getProductName(),"1");

        var o1 = new Order( 100.0, 120.0, pa1);
        var o2 = new Order( 40.0, 60.0, pa2);

        return args -> {
            orderRepo.save(o1);
            orderRepo.save(o2);
            productRepo.save(p1);
            productRepo.save(p2);
            productRepo.save(p3);
            productRepo.save(p4);
            productRepo.save(p5);
            productRepo.save(p6);
        };
    }
}
