package com.blacksteel.deneb.character.controller;

import com.blacksteel.deneb.character.model.Character;
import com.blacksteel.deneb.character.model.CharacterClass;
import com.blacksteel.deneb.character.service.CharacterService;
import com.blacksteel.deneb.character.util.CharacterGenerator;
import org.apache.commons.text.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/character")
public class CharacterController {

    private static final Logger logger = LoggerFactory.getLogger(CharacterController.class);

    private final CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get")
    public List<Character> getAllCharacters(HttpServletResponse response) {

        List<Character> returnValue = characterService.getAllCharacters();
        response.setStatus(HttpServletResponse.SC_OK);
        logger.info(new StringBuilder().append("Characters returned [").append(returnValue.toString()).append("].").toString());
        return returnValue;

    }

    @RequestMapping(method = RequestMethod.GET, value = "/getByName/{name}")
    public List<Character> getCharactersByName(HttpServletResponse response,
                                               @PathVariable("name") String name) {

        Character sourceItem = Character.builder().name(name).build();
        List<Character> returnValue = characterService.getAllCharactersByName(sourceItem);
        response.setStatus(HttpServletResponse.SC_OK);
        logger.info(new StringBuilder().append("Characters returned [").append(returnValue.toString()).append("].").toString());
        return returnValue;

    }

    @RequestMapping(method = RequestMethod.GET, value = "/getById/{id}")
    public Character getCharacterById(HttpServletResponse response,
                                      @PathVariable("id") Long id) {

        Character sourceItem = Character.builder().id(id).build();
        Character returnValue = null;
        if (characterService.existsById(sourceItem)) {
            returnValue = characterService.getItemById(sourceItem);
            response.setStatus(HttpServletResponse.SC_OK);
            logger.info(new StringBuilder().append("Character returned [").append(returnValue.toString()).append("].").toString());
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            logger.error(new StringBuilder().append("Character NOT found [").append(sourceItem.toString()).append("].").toString());
        }
        return returnValue;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/gen/{name}/{clazz}")
    public Character createCharacter(HttpServletResponse response,
                                     @PathVariable("name") String name,
                                     @PathVariable("clazz") String clazz) {

        // normalize class types - business was unfamiliar with standard D&D class types.
        if ("warrior".equalsIgnoreCase(clazz.toLowerCase())) {
            clazz = "Fighter";
            logger.info("Class changed from Warrior to Fighter");
        } else if ("archer".equalsIgnoreCase((clazz.toLowerCase()))) {
            clazz = "Ranger";
            logger.info("Class changed from Archer to Ranger");
        }

        boolean validCharacterClass = true;
        // test for class type
        CharacterClass characterClass = null;
        try {
            characterClass = CharacterClass.valueOf(clazz.toUpperCase());

        } catch (IllegalArgumentException e) {

            validCharacterClass = false;
            try {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "No character class of type " + WordUtils.capitalizeFully(clazz));

            } catch (IOException e1) {
                logger.error("Error sending response...", e);
            }

            logger.error("No character class of type " + WordUtils.capitalizeFully(clazz), e);
        }


        Character character = null;
        if (validCharacterClass) {

            // generate character
            character = CharacterGenerator.getRandomCharacter(CharacterClass.valueOf(clazz.toUpperCase().trim()));
            character.setName(WordUtils.capitalizeFully(name));

            // save character in repo
            characterService.createCharacter(character);

            // set status
            response.setStatus(HttpServletResponse.SC_OK);
            logger.info(new StringBuilder().append("Character created [").append(character.toString()).append("].").toString());
        }

        return character;

    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update")
    public Character updateCharacter(HttpServletResponse response,
                                     @RequestBody Character character) {

        Character responseCharacter = null;
        if (characterService.existsById(character)) {

            characterService.updateCharacter(character);
            response.setStatus(HttpServletResponse.SC_OK);
            logger.info(new StringBuilder().append("Character updated [").append(character.toString()).append("].").toString());

        } else {
            response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
            logger.info(new StringBuilder().append("Character NOT updated [").append(character.toString()).append("].").toString());
        }


        return responseCharacter;

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
    public void deleteItem(HttpServletResponse response,
                           @PathVariable("id") Long id) {

        Character character = Character.builder().id(id).build();
        if (characterService.existsById(character)) {
            Character targetCharacter = characterService.getItemById(character);
            characterService.deleteCharacter(targetCharacter);
            response.setStatus(HttpServletResponse.SC_OK);
            logger.info(new StringBuilder().append("Character deleted [").append(targetCharacter.toString()).append("].").toString());
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            logger.error(new StringBuilder().append("Item NOT deleted [id=").append(id).append("].").toString());
        }

    }
}
