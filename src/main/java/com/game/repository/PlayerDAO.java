package com.game.repository;

import com.game.controller.PlayerOrder;
import com.game.entity.Player;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerDAO extends JpaRepository <Player, Long>, JpaSpecificationExecutor<Player> {


}
