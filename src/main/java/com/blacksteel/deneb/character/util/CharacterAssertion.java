package com.blacksteel.deneb.character.util;

import com.blacksteel.deneb.character.model.Character;
import com.blacksteel.deneb.character.model.CharacterAbility;
import org.apache.commons.text.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.StringJoiner;

/**
 * Character assertions.
 * <p>
 * Assumptions made are:
 * * CharacterAbility matches field names in Character class.
 * * Character class has standard getter fields.
 */
public class CharacterAssertion {

    private static final Logger logger = LoggerFactory.getLogger(CharacterAssertion.class.getName());

    private static final String GETTER_PREFIX = "get";
    private static final String CHARACTER_CLASS_FQN = Character.class.getName();

    /**
     * ssert given ability is the highest for the character given.
     *
     * @param ability   ability
     * @param character character
     * @return true if ability is the highest
     */
    public static boolean assertMaxAbility(CharacterAbility ability, Character character) {

        // assume ability holds max value and prove otherwise
        boolean assertion = true;

        // get max value from target ability
        Integer maxValue = extractTargetValue(ability, character);

        // test abilities for max value - set assertion to false if higher value found
        StringJoiner message = new StringJoiner(", ", "[", "]");
        message.add(WordUtils.capitalizeFully(ability.name()) + " is " + maxValue);

        Integer strength = character.getStrength();
        if (strength > maxValue) {
            assertion = false;
            message.add("Strength is greater (" + strength + ")");
        }

        Integer dexterity = character.getDexterity();
        if (dexterity > maxValue) {
            assertion = false;
            message.add("Dexterity is greater (" + dexterity + ")");
        }

        Integer constitution = character.getConstitution();
        if (constitution > maxValue) {
            assertion = false;
            message.add("Constitution is greater (" + constitution + ")");
        }

        Integer intelligence = character.getIntelligence();
        if (intelligence > maxValue) {
            assertion = false;
            message.add("Intelligence is greater (" + intelligence + ")");
        }

        Integer wisdom = character.getWisdom();
        if (wisdom > maxValue) {
            assertion = false;
            message.add("Wisdom is greater (" + wisdom + ")");
        }

        Integer charisma = character.getCharisma();
        if (charisma > maxValue) {
            assertion = false;
            message.add("Charisma is greater (" + charisma + ")");
        }

        if (!assertion) {
            throw new AssertionError(message);
        }

        return assertion;
    }

    /**
     * Assert given ability is the lowest for the character given.
     *
     * @param ability   ability
     * @param character character
     * @return true if ability is lowest
     */
    public static boolean assertMinAbility(CharacterAbility ability, Character character) {

        // assume ability holds min value and prove otherwise
        boolean assertion = true;

        // get min value from target ability
        Integer minValue = extractTargetValue(ability, character);

        // test abilities for min value - set assertion to false if lower value found
        StringJoiner message = new StringJoiner(", ", "[", "]");
        message.add(WordUtils.capitalizeFully(ability.name()) + " is " + minValue);

        Integer strength = character.getStrength();
        if (strength < minValue) {
            assertion = false;
            message.add("Strength is less (" + strength + ")");
        }

        Integer dexterity = character.getDexterity();
        if (dexterity < minValue) {
            assertion = false;
            message.add("Dexterity is less (" + dexterity + ")");
        }

        Integer constitution = character.getConstitution();
        if (constitution < minValue) {
            assertion = false;
            message.add("Constitution is less (" + constitution + ")");
        }

        Integer intelligence = character.getIntelligence();
        if (intelligence < minValue) {
            assertion = false;
            message.add("Intelligence is less (" + intelligence + ")");
        }

        Integer wisdom = character.getWisdom();
        if (wisdom < minValue) {
            assertion = false;
            message.add("Wisdom is less (" + wisdom + ")");
        }

        Integer charisma = character.getCharisma();
        if (charisma < minValue) {
            assertion = false;
            message.add("Charisma is less (" + charisma + ")");
        }

        if (!assertion) {
            throw new AssertionError(message);
        }

        return assertion;

    }

    /**
     * Assert ability range given min and max values.
     *
     * @param min       mininum of range inclusive
     * @param max       maximum of range inclusive
     * @param character character to test
     * @return true if all abilities in range
     */
    public static boolean assertAbilityRange(Integer min, Integer max, Character character) {

        boolean assertion = true;

        // test abilities for range values - set assertion to false if value not in range
        StringJoiner message = new StringJoiner(", ", "[", "]");
        message.add("Range is " + min + " to " + max);

        Integer strength = character.getStrength();
        if (strength < min || strength > max) {
            assertion = false;
            message.add("Strength is out of range (" + strength + ")");
        }

        Integer dexterity = character.getDexterity();
        if (dexterity < min || dexterity > max) {
            assertion = false;
            message.add("Dexterity is out of range (" + dexterity + ")");
        }

        Integer constitution = character.getConstitution();
        if (constitution < min || constitution > max) {
            assertion = false;
            message.add("Constitution is out of range (" + constitution + ")");
        }

        Integer intelligence = character.getIntelligence();
        if (intelligence < min || intelligence > max) {
            assertion = false;
            message.add("Intelligence is out of range (" + intelligence + ")");
        }

        Integer wisdom = character.getWisdom();
        if (wisdom < min || wisdom > max) {
            assertion = false;
            message.add("Wisdom is out of range (" + wisdom + ")");
        }

        Integer charisma = character.getCharisma();
        if (charisma < min || charisma > max) {
            assertion = false;
            message.add("Charisma is out of range (" + charisma + ")");
        }

        if (!assertion) {
            throw new AssertionError(message);
        }

        return assertion;
    }

    /**
     * Uses reflection to extract target value from Character class based on ability passed in.
     *
     * @param ability   ability
     * @param character character
     * @return target value
     */
    private static Integer extractTargetValue(CharacterAbility ability, Character character) {

        // get Character class methods
        Class<?> clazz = null;
        try {
            clazz = Class.forName(CHARACTER_CLASS_FQN);
        } catch (ClassNotFoundException e) {
            logger.error("Error finding class.", e);
        }

        // generate method name for ability
        String abilityMethodName = new StringBuilder().append(GETTER_PREFIX).append(WordUtils.capitalizeFully(ability.name())).toString();

        // get methods for Character class
        Method[] allMethods = clazz.getMethods();

        // find getter method for chosen ability and invoke to get ability score - set to max
        Integer value = 0;
        for (Method method : allMethods) {
            if (method.getName().equalsIgnoreCase(abilityMethodName)) {
                try {
                    value = (Integer) method.invoke(character, null);
                } catch (IllegalAccessException e) {
                    logger.error("IllegalAccessException", e);
                } catch (InvocationTargetException e) {
                    logger.error("InvocationTargetException", e);
                }
                break;
            }
        }

        return value;
    }
}
