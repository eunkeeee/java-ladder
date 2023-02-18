package domain;

import java.util.ArrayList;
import java.util.List;

public class Names {
    private static final int MIN_RANGE = 2;
    private static final int MAX_RANGE = 100;
    private static final String COMMA = ",";

    private final List<Name> names;

    public Names(String names) {
        this.names = new ArrayList<>();
        for (String name : names.split(COMMA)) {
            this.names.add(new Name(name.trim()));
        }
        validate(this.names.size());
    }

    public List<Name> getNames() {
        return names;
    }

    public int getPersonNumber() {
        return names.size();
    }

    private static void validate(int personNumber) {
        if (personNumber < MIN_RANGE || personNumber > MAX_RANGE) {
            throw new IllegalArgumentException(Message.EXCEPTION_RANGE.message);
        }
    }


    private enum Message {
        EXCEPTION_RANGE("%d명 이상 %d명 이하의 사람 수를 입력해 주세요.", MIN_RANGE, MAX_RANGE);

        public static final String _FORMAT = "[ERROR] %s";
        private final String message;

        Message(String message, Object... replaces) {
            this.message = String.format(_FORMAT, String.format(message, replaces));
        }
    }
}
