package com.blacksteel.deneb.character.service;

import com.blacksteel.deneb.character.model.Character;
import com.blacksteel.deneb.character.model.CharacterClass;
import com.blacksteel.deneb.character.repository.CharacterRepository;
import com.blacksteel.deneb.character.util.CharacterGenerator;
import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class CharacterServiceTest {

    CharacterRepository repositoryMock;
    List<Character> expectedList;
    Character expectedCharacter;
    Optional<Character> optional;

    @Before
    public void setUp() throws Exception {
        repositoryMock = Mockito.mock(CharacterRepository.class);
        expectedList = new ArrayList<>();
        Long index = 1L;
        for (CharacterClass clazz : CharacterClass.values()) {
            Character character = CharacterGenerator.getRandomCharacter(clazz);
            character.setName("Garthok");
            character.setId(index++);
            expectedList.add(character);
        }
        expectedCharacter = expectedList.get(0);
        optional = Optional.of(expectedCharacter);

    }


    @Test
    public void getAllCharacters() {

        // setup
        when(repositoryMock.findAll()).thenReturn(expectedList);
        CharacterService characterService = new CharacterService(repositoryMock);

        // exercise
        List<Character> actual = characterService.getAllCharacters();

        verify(repositoryMock, times(1)).findAll();
        assertThat(actual, IsIterableContainingInOrder.contains(expectedList.toArray()));
    }

    @Test
    public void getAllCharactersByName() {

        // setup
        List<String> names = new ArrayList<>();
        names.add("Garthok");
        when(repositoryMock.findAllByName(names)).thenReturn(expectedList);
        CharacterService characterService = new CharacterService(repositoryMock);

        // exercise
        List<Character> actual = characterService.getAllCharactersByName(expectedCharacter);

        // assert
        verify(repositoryMock, times(1)).findAllByName(names);
        assertThat(actual, IsIterableContainingInOrder.contains(expectedList.toArray()));

    }

    @Test
    public void existsById() {

        // setup
        when(repositoryMock.existsById(expectedCharacter.getId())).thenReturn(true);
        CharacterService characterService = new CharacterService(repositoryMock);

        // exercise
        boolean actual = characterService.existsById(expectedCharacter);

        // assert
        verify(repositoryMock, times(1)).existsById(expectedCharacter.getId());
        assertTrue(actual);
    }

    @Test
    public void getItemById() {

        // setup
        when(repositoryMock.findById(expectedCharacter.getId())).thenReturn(optional);
        CharacterService characterService = new CharacterService(repositoryMock);

        // exercise
        Character actual = characterService.getItemById(expectedCharacter);

        // assert
        verify(repositoryMock, times(1)).findById(expectedCharacter.getId());
        assertEquals(expectedCharacter, actual);

    }

    @Test
    public void createCharacter() {

        // setup
        when(repositoryMock.save(expectedCharacter)).thenReturn(expectedCharacter);
        CharacterService characterService = new CharacterService(repositoryMock);

        // exercise
        Character actual = characterService.createCharacter(expectedCharacter);

        // assert
        verify(repositoryMock, times(1)).save(expectedCharacter);
        assertEquals(expectedCharacter, actual);
    }

    @Test
    public void updateCharacter() {

        // setup
        when(repositoryMock.save(expectedCharacter)).thenReturn(expectedCharacter);
        CharacterService characterService = new CharacterService(repositoryMock);

        // exercise
        Character actual = characterService.updateCharacter(expectedCharacter);

        // assert
        verify(repositoryMock, times(1)).save(expectedCharacter);
        assertEquals(expectedCharacter, actual);

    }

    @Test
    public void deleteCharacter() {

        // setup
        CharacterService characterService = new CharacterService(repositoryMock);

        // exercise
        characterService.deleteCharacter(expectedCharacter);

        // assert
        verify(repositoryMock, times(1)).delete(expectedCharacter);
    }
}