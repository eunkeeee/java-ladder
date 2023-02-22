package view;

import domain.ladder.LadderStep;
import domain.ladder.Line;
import domain.ladder.Lines;
import domain.mission.Missions;
import domain.player.Names;
import domain.Result;
import java.util.List;

public class OutputView {

    private static final OutputView instance = new OutputView();

    public static OutputView getInstance() {
        return instance;
    }

    private OutputView() {
    }

    public void printExceptionMessage(Exception exception) {
        System.out.println(exception.getMessage());
    }

    public void printResult(Names names, Lines lines, Missions missions) {
        System.out.println(Message.OUTPUT_RESULT.message);
        names.getNames()
                .forEach(name -> System.out.printf("%-5s", name.getName()));
        System.out.println();
        printLadder(lines);
        missions.getMissions()
                .forEach(mission -> System.out.printf("%-5s", mission.getMission()));
        System.out.println();
    }

    public void printLines(Lines lines) {
        for (Line line : lines.getLines()) {
            StringBuilder ladderDisplay = new StringBuilder();
            ladderDisplay.append(Message.COLUMN_LADDER.message);
            line.getLadderSteps()
                    .stream().map(LadderStep::exists)
                    .forEach(exists -> ladderDisplay.append(getPointString(exists)));
            System.out.println(ladderDisplay);
        }
    }

    private String getPointString(boolean isPoint) {
        if (isPoint) {
            return Message.ROW_LADDER.message;
        }
        return Message.EMPTY_ROW_LADDER.message;
    }

    public void printAllResult(List<Result> result) {
        System.out.print(Message.OUTPUT_RESULT.message);
        result.forEach(element -> System.out.printf(Message.OUTPUT_RESULT_ALL.message,
                element.getName(),
                element.getMission()));
    }

    public void printSingleResult(Result result) {
        System.out.print(Message.OUTPUT_RESULT.message);
        System.out.println(result.getMission());
    }


    private enum Message {
        OUTPUT_RESULT("실행결과" + System.lineSeparator()),
        OUTPUT_RESULT_ALL("%s : %s" + System.lineSeparator()),
        COLUMN_LADDER("  |"),
        ROW_LADDER("-----|"),
        EMPTY_ROW_LADDER("     |");

        private final String message;

        Message(String message) {
            this.message = message;
        }
    }
}
