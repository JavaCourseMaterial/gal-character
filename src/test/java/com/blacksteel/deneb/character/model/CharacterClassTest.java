package com.blacksteel.deneb.character.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class CharacterClassTest {

    //BARBARIAN, BARD, CLERIC, DRUID, FIGHTER, MONK, PALADIN, RANGER, ROGUE, SORCERER, WARLOCK, WIZARD

    @Test
    public void testCharacterClasses() {
        assertEquals("BARBARIAN", CharacterClass.BARBARIAN.name());
        assertEquals("BARD", CharacterClass.BARD.name());
        assertEquals("CLERIC", CharacterClass.CLERIC.name());
        assertEquals("DRUID", CharacterClass.DRUID.name());
        assertEquals("FIGHTER", CharacterClass.FIGHTER.name());
        assertEquals("MONK", CharacterClass.MONK.name());
        assertEquals("PALADIN", CharacterClass.PALADIN.name());
        assertEquals("RANGER", CharacterClass.RANGER.name());
        assertEquals("ROGUE", CharacterClass.ROGUE.name());
        assertEquals("SORCERER", CharacterClass.SORCERER.name());
        assertEquals("WARLOCK", CharacterClass.WARLOCK.name());
        assertEquals("WIZARD", CharacterClass.WIZARD.name());
    }

}