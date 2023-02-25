package domain.mission;

import domain.player.Position;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Missions {
    private final List<Mission> missions;

    private Missions(List<String> missions) {
        this.missions = formatMissions(missions);
    }

    public static Missions of(List<String> missions, int size) {
        validateSize(missions, size);
        return new Missions(missions);
    }

    private static void validateSize(List<String> missions, int size) {
        if (missions.size() != size) {
            throw new IllegalArgumentException("미션의 개수를 다시 확인해 주세요!");
        }
    }

    private List<Mission> formatMissions(List<String> missions) {
        List<Mission> randomMission = missions.stream()
                .map(String::trim)
                .map(Mission::new)
                .collect(Collectors.toList());
        // TODO: shuffle에 대해서 통제할 수 있는 코드 작성
        Collections.shuffle(randomMission);
        return randomMission;
    }

    public List<Mission> getMissions() {
        return Collections.unmodifiableList(missions);
    }

    public int size() {
        return missions.size();
    }

    public Mission findByPosition(Position position) {
        return missions.get(position.getValue());
    }
}
