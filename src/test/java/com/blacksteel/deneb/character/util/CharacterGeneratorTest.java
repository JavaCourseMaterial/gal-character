package com.blacksteel.deneb.character.util;

import com.blacksteel.deneb.character.model.Character;
import com.blacksteel.deneb.character.model.CharacterAbility;
import com.blacksteel.deneb.character.model.CharacterClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.blacksteel.deneb.character.util.CharacterAssertion.*;

public class CharacterGeneratorTest {

    private static final Logger logger = LoggerFactory.getLogger(CharacterGeneratorTest.class.getName());

    @Test
    public void getRandomCharacter() {

        /*
        Testing for specific class generation parameters
         */
        logger.info("Starting class specific tests...");
        for (int x = 1; x <= 20; x++) {

            Character character = CharacterGenerator.getRandomCharacter(CharacterClass.FIGHTER);

            assertMaxAbility(CharacterAbility.STRENGTH, character);
            assertMinAbility(CharacterAbility.INTELLIGENCE, character);

            logger.info(new StringBuilder().append("[").append(CharacterClass.FIGHTER.name()).append("] Test iteration ").append(x).append(" passed.").toString());
        }

        for (int x = 1; x <= 20; x++) {
            Character character = CharacterGenerator.getRandomCharacter(CharacterClass.RANGER);

            assertMaxAbility(CharacterAbility.DEXTERITY, character);
            assertMinAbility(CharacterAbility.CHARISMA, character);

            logger.info(new StringBuilder().append("[").append(CharacterClass.RANGER.name()).append("] Test iteration ").append(x).append(" passed.").toString());
        }

        for (int x = 1; x <= 20; x++) {
            Character character = CharacterGenerator.getRandomCharacter(CharacterClass.WIZARD);

            assertMaxAbility(CharacterAbility.INTELLIGENCE, character);
            assertMinAbility(CharacterAbility.STRENGTH, character);

            logger.info(new StringBuilder().append("[").append(CharacterClass.WIZARD.name()).append("] Test iteration ").append(x).append(" passed.").toString());
        }

        for (int x = 1; x <= 20; x++) {
            Character character = CharacterGenerator.getRandomCharacter(CharacterClass.ROGUE);

            assertMaxAbility(CharacterAbility.CHARISMA, character);
            assertMinAbility(CharacterAbility.STRENGTH, character);

            logger.info(new StringBuilder().append("[").append(CharacterClass.ROGUE.name()).append("] Test iteration ").append(x).append(" passed.").toString());
        }

        /*
        Testing for ability ranges
         */

        logger.info("Starting range assertion tests...");
        for (CharacterClass clazz : CharacterClass.values()) {

            Character character = CharacterGenerator.getRandomCharacter(clazz);

            for (int x = 1; x <= 40; x++) {

                assertAbilityRange(8, 18, character);

                logger.info(new StringBuilder().append("[").append(clazz.name()).append("] Test iteration ").append(x).append(" passed.").toString());
            }


        }

    }
}