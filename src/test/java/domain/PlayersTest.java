package domain;

import domain.player.Name;
import domain.player.Names;
import domain.player.Player;
import domain.player.Players;
import domain.player.Position;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @Test
    void 생성_테스트() {
        Names names = new Names(List.of("깃짱", "이리내"));
        Players players = new Players(names);

        Player gitJjang = new Player(new Name("깃짱"), new Position(0));
        Player irene = new Player(new Name("이리내"), new Position(1));

        Assertions.assertThat(players.getPlayersSortedByPosition())
                .containsExactly(gitJjang, irene);
    }
}
