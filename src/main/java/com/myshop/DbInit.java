package com.myshop;

import com.myshop.model.Item;
import com.myshop.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class DbInit implements CommandLineRunner {

    private final ItemRepository itemRepository;

    @Autowired
    public DbInit(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        itemRepository.saveAll(List.of(
                new Item("Ołówek", new BigDecimal("2.50"), "https://e-sklep.koh-i-noor.pl/976-home_default/150000f01170p104-kin-olowek-grafitowy-1500-f-koh-i-noor-pojedynczy.jpg"),
                new Item("Długopis", new BigDecimal("3.20"), "https://sklep.gumed.edu.pl/wp-content/uploads/2021/03/dlugopis-03-03_1-scaled.jpg"),
                new Item("Pióro", new BigDecimal("6.70"), "https://a.allegroimg.com/original/119ea4/941e58af4d4bbd96e05c7c17f3ef/PIORO-WIECZNE-METALOWE-DLUGOPIS-NA-PREZENT-ELEGANT")
        ));
    }
}
