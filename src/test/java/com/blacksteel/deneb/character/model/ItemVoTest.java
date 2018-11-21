package com.blacksteel.deneb.character.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ItemVoTest {

    ItemVo vo;

    @Before
    public void setUp() throws Exception {
        vo = new ItemVo(1L, "test");

    }

    @Test
    public void getId() {
        Long expected = 2L;
        vo.setId(expected);
        Long actual = vo.getId();
        assertEquals(expected, actual);

    }

    @Test
    public void getName() {
        String expected = "Garthok";
        vo.setName(expected);
        String actual = vo.getName();
        assertEquals(expected, actual);
    }

    @Test
    public void equals() {
        assertTrue(vo.equals(vo));
    }

    @Test
    public void testHashCode() {
        assertNotSame(0,vo.hashCode());
    }

    @Test
    public void testToString() {
        assertNotNull(vo.toString());
    }
}