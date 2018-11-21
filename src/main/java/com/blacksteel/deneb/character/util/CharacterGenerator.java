package com.blacksteel.deneb.character.util;

import com.blacksteel.deneb.character.model.Character;
import com.blacksteel.deneb.character.model.CharacterClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CharacterGenerator {

    private static final Logger logger = LoggerFactory.getLogger(CharacterGenerator.class.getName());

    public static Character getRandomCharacter(CharacterClass clazz) {

        Random generator = new Random();

        // generate random values to use in character setup
        List<Integer> values = new ArrayList<>();
        for (int x = 1; x <= 6; x++) {
            Integer value = generator.nextInt(11) + 8;
            values.add(value);
        }

        Character character = getBaselineCharacter();

        Integer min = Collections.min(values);
        Integer max = Collections.max(values);

        // set special cases
        switch (clazz) {

            case FIGHTER:
                character.setStrength(max);
                character.setIntelligence(min);
                values.remove(min);
                values.remove(max);
                break;
            case RANGER:
                character.setDexterity(max);
                character.setCharisma(min);
                values.remove(min);
                values.remove(max);
                break;
            case WIZARD:
                character.setIntelligence(max);
                character.setStrength(min);
                values.remove(min);
                values.remove(max);
                break;
            case ROGUE:
                character.setCharisma(max);
                character.setStrength(min);
                values.remove(min);
                values.remove(max);
                break;
        }

        // set remaining values
        if (!character.strengthSet()) {
            character.setStrength(values.remove(0));
        }

        if (!character.dexteritySet()) {
            character.setDexterity(values.remove(0));
        }

        if (!character.constitutionSet()) {
            character.setConstitution(values.remove(0));
        }

        if (!character.intelligenceSet()) {
            character.setIntelligence(values.remove(0));
        }

        if (!character.wisdomSet()) {
            character.setWisdom(values.remove(0));
        }

        if (!character.charismaSet()) {
            character.setCharisma(values.remove(0));
        }

        // Set hit points
        character.setHitPoints(character.getConstitution() * 2);
        character.setClazz(clazz);

        return character;
    }

    private static Character getBaselineCharacter() {

        return Character.builder().strength(0).dexterity(0).constitution(0).intelligence(0).wisdom(0).charisma(0).build();
    }

}
