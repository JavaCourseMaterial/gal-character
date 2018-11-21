package com.blacksteel.deneb.character.model;

import javax.persistence.*;
import java.beans.ConstructorProperties;
import java.util.Arrays;

/**
 * Character model.
 * <p>
 * Loosely based on http://media.wizards.com/2015/downloads/dnd/DDALRoD_CharacterSheet.pdf
 */
@Entity(name = "CHARACTER")

public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CLASS")
    private CharacterClass clazz;

    @Column(name = "INT")
    private Integer intelligence;

    @Column(name = "WIS")
    private Integer wisdom;

    @Column(name = "CHA")
    private Integer charisma;

    @Column(name = "STR")
    private Integer strength;

    @Column(name = "DEX")
    private Integer dexterity;

    @Column(name = "CON")
    private Integer constitution;

    @Column(name = "LOCATION")
    private Integer location;

    @Column(name = "INVENTORY")
    private ItemVo[] inventory;

    @Column(name = "HITPOINTS")
    private Integer hitPoints;

    public Character() {

    }

    @ConstructorProperties({"name", "clazz", "intelligence", "wisdom", "charisma", "strength", "dexterity", "constitution", "location", "inventory", "hitPoints"})
    public Character(String name, CharacterClass clazz, Integer intelligence, Integer wisdom, Integer charisma, Integer strength, Integer dexterity, Integer constitution, Integer location, ItemVo[] inventory, Integer hitPoints) {
        this.name = name;
        this.clazz = clazz;
        this.intelligence = intelligence;
        this.wisdom = wisdom;
        this.charisma = charisma;
        this.strength = strength;
        this.dexterity = dexterity;
        this.constitution = constitution;
        this.location = location;
        this.inventory = inventory;
        this.hitPoints = hitPoints;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Character setName(String name) {
        this.name = name;
        return this;
    }

    public CharacterClass getClazz() {
        return clazz;
    }

    public Character setClazz(CharacterClass clazz) {
        this.clazz = clazz;
        return this;
    }

    public Integer getIntelligence() {
        return intelligence;
    }

    public Character setIntelligence(Integer intelligence) {
        this.intelligence = intelligence;
        return this;
    }

    public Integer getWisdom() {
        return wisdom;
    }

    public Character setWisdom(Integer wisdom) {
        this.wisdom = wisdom;
        return this;
    }

    public Integer getCharisma() {
        return charisma;
    }

    public Character setCharisma(Integer charisma) {
        this.charisma = charisma;
        return this;
    }

    public Integer getStrength() {
        return strength;
    }

    public Character setStrength(Integer strength) {
        this.strength = strength;
        return this;
    }

    public Integer getDexterity() {
        return dexterity;
    }

    public Character setDexterity(Integer dexterity) {
        this.dexterity = dexterity;
        return this;
    }

    public Integer getConstitution() {
        return constitution;
    }

    public Character setConstitution(Integer constitution) {
        this.constitution = constitution;
        return this;
    }

    public Integer getLocation() {
        return location;
    }

    public Character setLocation(Integer location) {
        this.location = location;
        return this;
    }

    public ItemVo[] getInventory() {
        return inventory;
    }

    public Character setInventory(ItemVo[] inventory) {
        this.inventory = inventory;
        return this;
    }

    public Integer getHitPoints() {
        return hitPoints;
    }

    public Character setHitPoints(Integer hitPoints) {
        this.hitPoints = hitPoints;
        return this;
    }

    public boolean strengthSet() {
        return this.strength != 0;
    }

    public boolean dexteritySet() {
        return this.dexterity != 0;
    }

    public boolean constitutionSet() {
        return this.constitution != 0;
    }

    public boolean intelligenceSet() {
        return this.intelligence != 0;
    }

    public boolean wisdomSet() {
        return this.wisdom != 0;
    }

    public boolean charismaSet() {
        return this.charisma != 0;
    }

    public static CharacterBuilder builder() {
        return new CharacterBuilder();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Character{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", class='").append(clazz).append('\'');
        sb.append(", intelligence=").append(intelligence);
        sb.append(", wisdom=").append(wisdom);
        sb.append(", charisma=").append(charisma);
        sb.append(", strength=").append(strength);
        sb.append(", dexterity=").append(dexterity);
        sb.append(", constitution=").append(constitution);
        sb.append(", location=").append(location);
        sb.append(", inventory=").append(Arrays.toString(inventory));
        sb.append(", hitPoints=").append(hitPoints);
        sb.append('}');
        return sb.toString();
    }

    /**
     * Aw, look at the cute builder...builds character!
     */
    public static class CharacterBuilder {

        private Long id;
        private String name;
        private CharacterClass clazz;
        private Integer intelligence;
        private Integer wisdom;
        private Integer charisma;
        private Integer strength;
        private Integer dexterity;
        private Integer constitution;
        private Integer location;
        private ItemVo[] inventory;
        private Integer hitPoints;

        public CharacterBuilder() {

        }

        public Character.CharacterBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public Character.CharacterBuilder name(String name) {
            this.name = name;
            return this;
        }

        public Character.CharacterBuilder clazz(CharacterClass clazz) {
            this.clazz = clazz;
            return this;
        }

        public Character.CharacterBuilder intelligence(Integer intelligence) {
            this.intelligence = intelligence;
            return this;
        }

        public Character.CharacterBuilder wisdom(Integer wisdom) {
            this.wisdom = wisdom;
            return this;
        }

        public Character.CharacterBuilder charisma(Integer charisma) {
            this.charisma = charisma;
            return this;
        }

        public Character.CharacterBuilder strength(Integer strength) {
            this.strength = strength;
            return this;
        }

        public Character.CharacterBuilder dexterity(Integer dexterity) {
            this.dexterity = dexterity;
            return this;
        }

        public Character.CharacterBuilder constitution(Integer constitution) {
            this.constitution = constitution;
            return this;
        }

        public Character.CharacterBuilder location(Integer location) {
            this.location = location;
            return this;
        }

        public Character.CharacterBuilder inventory(ItemVo[] inventory) {
            this.inventory = inventory;
            return this;
        }

        public Character.CharacterBuilder hitpoints(Integer hitpoints) {
            this.hitPoints = hitpoints;
            return this;
        }

        public Character build() {
            return new Character(name, clazz, intelligence, wisdom, charisma, strength, dexterity, constitution, location, inventory, hitPoints);
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("CharacterBuilder{");
            sb.append("name='").append(name).append('\'');
            sb.append(", class='").append(clazz).append('\'');
            sb.append(", intelligence=").append(intelligence);
            sb.append(", wisdom=").append(wisdom);
            sb.append(", charisma=").append(charisma);
            sb.append(", strength=").append(strength);
            sb.append(", dexterity=").append(dexterity);
            sb.append(", constitution=").append(constitution);
            sb.append(", location=").append(location);
            sb.append(", inventory=").append(Arrays.toString(inventory));
            sb.append(", hitPoints=").append(hitPoints);
            sb.append('}');
            return sb.toString();
        }
    }
}
