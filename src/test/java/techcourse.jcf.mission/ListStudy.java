package techcourse.jcf.mission;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class ListStudy {
    @Test
    public void arrayList() {
        SimpleList values = new SimpleLinkedList();
        values.add("first");
        values.add("second");
        values.add(1, "new");

        assertThat(values.add("third")).isTrue();

        assertThat(values.get(0)).isEqualTo("first");
        assertThat(values.contains("first")).isTrue();

        assertThat(values.remove(0)).isEqualTo("first");
        assertThat(values.remove("second")).isTrue();
        assertThat(values.remove("none")).isFalse();

        SimpleList emptyValues = new SimpleArrayList();
        assertThat(emptyValues.isEmpty()).isTrue();

        System.out.println(values);
    }
}
