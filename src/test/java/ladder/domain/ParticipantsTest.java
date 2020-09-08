package ladder.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ParticipantsTest {
    @Test
    void participants_creation_test() {
        String input = "pobi,honux,crong,jk";

        String[] split = input.split(",");

        Participants participants = Participants.of(input);

        boolean isLast = participants.isLastParticipant(split.length);
        assertThat(isLast).isTrue();
    }

    @Test
    void participants_exception_test() {
        String empty = "";
        assertThatThrownBy(() -> Participants.of(empty))
                .isInstanceOf(NullPointerException.class);

        assertThatThrownBy(() -> Participants.of(null))
                .isInstanceOf(NullPointerException.class);

        String participant = " tdd";
        assertThatThrownBy(() -> Participants.of(participant))
                .isInstanceOf(IllegalArgumentException.class);

        String duplication = "pobi,pobi,tdd,clean";
        assertThatThrownBy(() -> Participants.of(duplication))
                .isInstanceOf(IllegalArgumentException.class);
    }
}