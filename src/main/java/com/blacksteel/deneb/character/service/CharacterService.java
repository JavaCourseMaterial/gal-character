package com.blacksteel.deneb.character.service;

import com.blacksteel.deneb.character.model.Character;
import com.blacksteel.deneb.character.repository.CharacterRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CharacterService {

    private final CharacterRepository repository;

    public CharacterService(CharacterRepository repository) {
        this.repository = repository;
    }

    public List<Character> getAllCharacters() {
        return repository.findAll();
    }

    public List<Character> getAllCharactersByName(Character character) {
        List<String> names = new ArrayList<>();
        names.add(character.getName());
        return repository.findAllByName(names);
    }

    public boolean existsById(Character character) {
        return repository.existsById(character.getId());
    }

    public Character getItemById(Character character) {
        return repository.findById(character.getId()).get();
    }

    public Character createCharacter(Character character) {
        return repository.save(character);
    }

    public Character updateCharacter(Character character) {
        return createCharacter(character);
    }

    public void deleteCharacter(Character targetCharacter) {
        repository.delete(targetCharacter);
    }
}
