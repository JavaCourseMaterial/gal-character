package com.blacksteel.deneb.character.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class CharacterAbilityTest {

    // STRENGTH, DEXTERITY, CONSTITUTION, INTELLIGENCE, WISDOM, CHARISMA
    @Test
    public void testCharacterAbility() {
        assertEquals("STRENGTH", CharacterAbility.STRENGTH.name());
        assertEquals("DEXTERITY", CharacterAbility.DEXTERITY.name());
        assertEquals("CONSTITUTION", CharacterAbility.CONSTITUTION.name());
        assertEquals("INTELLIGENCE", CharacterAbility.INTELLIGENCE.name());
        assertEquals("WISDOM", CharacterAbility.WISDOM.name());
        assertEquals("CHARISMA", CharacterAbility.CHARISMA.name());
    }

}