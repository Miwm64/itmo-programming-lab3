package objects;

import java.time.LocalDateTime;

public class KnifeFactory extends EquipmentFactory {

    public KnifeFactory() {}

    @Override
    public Equipment create(String title, String description, int durability) {
        return new Knife(title, LocalDateTime.now(), description, durability);
    }

    @Override
    public Equipment create(int durability) {
        return new Knife(durability);
    }

    public Knife createBarrayaranServiceKnife() {
        return new Knife("Barrayaran Service Knife", LocalDateTime.now(),
                "Standard issue for Imperial Service personnel", 100);
    }
}