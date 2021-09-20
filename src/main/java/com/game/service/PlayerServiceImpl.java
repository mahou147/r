package com.game.service;

import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.repository.PlayerDAO;
import com.game.entity.Player;
import com.game.utils.PlayerSort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerDAO playerDAO;

    @Override
    public void save(Player player) throws Exception {
        log.info("IN PlayerServiceImpl save{}", player);
        if (player == null
                || player.isNotMatches()
                || player.getBirthday().getTime() < 0L
                || !player.getBirthday().after(new SimpleDateFormat("dd.MM.yyyy" ).parse( "01.01.2000"))
                || !player.getBirthday().before(new SimpleDateFormat("dd.MM.yyyy" ).parse( "01.01.3000"))) {
            throw new Exception();
        }
        player.setBanned(player.isBanned() != null);
        player.setLevel(Math.toIntExact(Math.round((Math.sqrt(2500 + 200 * player.getExperience()) - 50) / 100)));
        player.setUntilNextLevel(50 * (player.getLevel() + 1) * (player.getLevel() + 2) - player.getExperience());
        playerDAO.save(player);
    }

    @Override
    public Player update(Player player, Long id) {
        log.info("IN PlayerServiceImpl update{}", player);
        Player entity = null;
        if (id == 0)
            return new Player();
        if(existsById(id))
            entity = playerDAO.findById(id).get();
        else return entity;

        if(player.getExperience() != null) {

            if(player.getExperience() > 10_000_00 || player.getExperience() < 0)
                return new Player();

            entity.setExperience(player.getExperience());
            double di = (Math.sqrt(2500 + 200 * player.getExperience())- 50) / 100;
            int i = (int) di;
            entity.setLevel(i);
            entity.setUntilNextLevel(50 * (entity.getLevel() + 1) * (entity.getLevel() + 2) - entity.getExperience());
        }
        if(player.getName() != null) entity.setName(player.getName());
        if(player.getTitle() != null) entity.setTitle(player.getTitle());
        if(player.getRace() != null) entity.setRace(player.getRace());
        if(player.getProfession() != null) entity.setProfession(player.getProfession());

        if(player.getBirthday() != null) {
            if(player.getBirthday().getTime() <0L)
                return new Player();

            entity.setBirthday(player.getBirthday());
        }

        if(player.isBanned() != null) entity.setBanned(player.isBanned());

        return playerDAO.save(entity);
    }

    @Override
    public boolean existsById(Long id) {
        return playerDAO.existsById(id);
    }

    @Override
    public void delete(Long id) {
        log.info("IN PlayerServiceImpl delete{}", id);
        playerDAO.deleteById(id);
    }

    @Override
    public Player getById(Long id) {
        log.info("IN PlayerServiceImpl getById{}", id);
        return playerDAO.findById(id).get();
    }

    @Override
    public Page<Player> searchByCriterias(String name, String title,
                                          Race race, Profession profession,
                                          Boolean banned,
                                          Long after, Long before,
                                          @RequestParam(required = false, defaultValue = "") Integer experienceMin,
                                          @RequestParam(required = false, defaultValue = "") Integer experienceMax,
                                          @RequestParam(required = false, defaultValue = "") Integer levelMin,
                                          @RequestParam(required = false, defaultValue = "") Integer levelMax,
                                          Pageable pageable
    ) {
        return playerDAO.findAll(Objects.requireNonNull(Objects.requireNonNull(Objects.requireNonNull(Objects.requireNonNull(Objects.requireNonNull(Objects.requireNonNull(Objects.requireNonNull(PlayerSort.findPlayerByName(name)
                                                                        .and(PlayerSort.findPlayerByRace(race)))
                                                                .and(PlayerSort.findPlayerByProfession(profession)))
                                                        .and(PlayerSort.findPlayerByStatus(banned)))
                                                .and(PlayerSort.findPlayerByTitle(title)))
                                        .and(PlayerSort.findPlayerByDate(after, before)))
                                .and(PlayerSort.findPlayerByExp(experienceMin, experienceMax)))
                        .and(PlayerSort.findPlayerByMaxLevel(levelMax)))
                .and(PlayerSort.findPlayerByMinLevel(levelMin)), pageable);
    }
}
