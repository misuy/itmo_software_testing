package misuy.domainModel;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class DomainModelTest {
    @Nested
    class FighterTest {
        @Test
        @DisplayName("check angry man construction")
        void angryManConstruction() {
            Fighter angryMan = new AngryMan("Ryan");
            assertAll(
                () -> assertEquals("Angry man (Ryan)", angryMan.getName()),
                () -> assertEquals(100f, angryMan.getHp()),
                () -> assertEquals(10f, angryMan.getDamage())
            );
        }

        @ParameterizedTest(name = "defence({0})")
        @DisplayName("check defence")
        @ValueSource(floats = {100, 150, 50, 10})
        void defence(Float damage) {
            Fighter angryMan = new AngryMan("Ryan");
            angryMan.defend(damage);
            assertEquals((100 - damage) < 0 ? 0 : 100 - damage, angryMan.getHp(), 0.0001);
        }

        @Test
        @DisplayName("check attack")
        void attack() {
            Fighter angryMan1 = new AngryMan("1");
            Fighter angryMan2 = new AngryMan("1");
            angryMan1.attack(angryMan2);
            assertEquals(90, angryMan2.getHp());
        }
    }

    @Nested
    class FightTest {
        @Test
        @DisplayName("test fight")
        void fight() {
            Team red = new Team("angry people");
            red.addFighter(new AngryMan("Paul"));
            red.addFighter(new AngryMan("Ryan"));

            Team blue = new Team("lackeys");
            blue.addFighter(new Lackey("Jack"));
            blue.addFighter(new Lackey("Alan"));

            Fight fight = new Fight(red, blue);
            fight.simulate();
            assertAll(
                () -> assertEquals(true, fight.isFinished()),
                () -> assertEquals(red, fight.getWinner())
            );
        }
    }
}
