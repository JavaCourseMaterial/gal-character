package com.blacksteel.deneb.character.util;

import com.blacksteel.deneb.character.model.Character;
import com.blacksteel.deneb.character.model.CharacterAbility;
import com.blacksteel.deneb.character.model.CharacterClass;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CharacterAssertionTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void assertMaxAbility() {

        Character character = CharacterGenerator.getRandomCharacter(CharacterClass.WIZARD);
        boolean result = CharacterAssertion.assertMaxAbility(CharacterAbility.INTELLIGENCE, character);
        assertTrue(result);


    }

    @Test
    public void assertMinAbility() {

        Character character = CharacterGenerator.getRandomCharacter(CharacterClass.WIZARD);
        boolean result = CharacterAssertion.assertMinAbility(CharacterAbility.STRENGTH, character);
        assertTrue(result);

    }

    @Test
    public void assertAbilityRange() {

        Character character = CharacterGenerator.getRandomCharacter(CharacterClass.WIZARD);
        boolean result = CharacterAssertion.assertAbilityRange(8, 18, character);
        assertTrue(result);
    }
}