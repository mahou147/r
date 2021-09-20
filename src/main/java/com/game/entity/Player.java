package com.game.entity;

import lombok.*;

import javax.persistence.*;
import java.text.ParseException;
import java.util.Date;

@Entity
@Setter
@Getter
@Table(name = "player")
@NoArgsConstructor
@AllArgsConstructor
public class Player extends BaseEntity {

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

    @Column(name= "birthday")
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
        return (this.name.length()>12
                ||this.title.length()>30
                ||this.experience>10000000
                ||this.experience<0);

    }

    public Boolean isBanned() {
        return this.banned;
    }
}
