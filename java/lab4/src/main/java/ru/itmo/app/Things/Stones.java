package ru.itmo.app.Things;
import ru.itmo.app.Interfaces.Climbable;
import ru.itmo.app.Persons.Entity;
public class Stones extends Thing implements Climbable {
    public Stones(String name) {
        super(name);
    }
    public void beClimbedBy(Entity entity) {
        System.out.println(entity.getName() + " вскарабкались по " + getName());
    }
}
