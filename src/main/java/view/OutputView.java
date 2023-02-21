package view;

import domain.LadderStep;
import domain.Line;
import domain.Lines;
import domain.Mission;
import domain.Missions;
import domain.Names;

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

    public void printLadder(Lines lines) {
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

    public void printResult(Mission mission) {
        System.out.printf(Message.OUTPUT_RESULT.message);
        System.out.println(mission.getMission());
    }


    private enum Message {
        OUTPUT_RESULT("실행결과" + System.lineSeparator()),
        COLUMN_LADDER("  |"),
        ROW_LADDER("-----|"),
        EMPTY_ROW_LADDER("     |");

        private final String message;

        Message(String message) {
            this.message = message;
        }
    }
}
