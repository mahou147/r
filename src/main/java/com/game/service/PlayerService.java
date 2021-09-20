package com.game.service;

import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface PlayerService {
    void save(Player player) throws Exception;
    Player update(Player player, Long id);



    boolean existsById(Long id);
    void delete(Long id);
    Player getById(Long id);
    Page<Player> searchByCriterias(String name, String title,
                                   Race race, Profession profession,
                                   Boolean banned,
                                   Long after, Long before,
                                   @RequestParam(required = false, defaultValue = "") Integer experienceMin,
                                   @RequestParam(required = false, defaultValue = "") Integer experienceMax,
                                   @RequestParam(required = false, defaultValue = "") Integer levelMin,
                                   @RequestParam(required = false, defaultValue = "") Integer levelMax,
                                   Pageable pageable
    );
}
