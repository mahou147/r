package com.game.entity;


import javax.persistence.*;
import java.text.ParseException;
import java.util.Date;

@Entity
@Table(name = "player")
public class Player extends BaseEntity {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getUntilNextLevel() {
        return untilNextLevel;
    }

    public void setUntilNextLevel(Integer untilNextLevel) {
        this.untilNextLevel = untilNextLevel;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Boolean getBanned() {
        return banned;
    }

    public void setBanned(Boolean banned) {
        this.banned = banned;
    }

    public Player(String name, String title, Race race, Profession profession, Integer experience, Integer level, Integer untilNextLevel, Date birthday, Boolean banned) {
        this.name = name;
        this.title = title;
        this.race = race;
        this.profession = profession;
        this.experience = experience;
        this.level = level;
        this.untilNextLevel = untilNextLevel;
        this.birthday = birthday;
        this.banned = banned;
    }

    public Player() {
    }

    @Column(name = "name")
    private String name;

    @Column(name = "title")
    private String title;

    @Enumerated(EnumType.STRING)
    private Race race;

    @Enumerated(EnumType.STRING)
    private Profession profession;

    @Column(name = "experience")
    private Integer experience;

    @Column(name = "level")
    private Integer level;

    @Column(name = "untilNextLevel")
    private Integer untilNextLevel;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "banned")
    private Boolean banned;

    public boolean isEmpty() {
        return (this.name.equals("")
                || this.title == null
                || this.race == null
                || this.profession == null
                || this.experience == 0
                || this.level == 0
                || this.untilNextLevel == 0
                || this.birthday == null);
    }

    public boolean isNotMatches() throws ParseException {
        return (this.name.length() > 12
                || this.title.length() > 30
                || this.experience > 10000000
                || this.experience < 0);

    }

    public Boolean isBanned() {
        return this.banned;
    }
}
