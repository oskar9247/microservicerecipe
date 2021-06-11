package learningtests.streams;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class StreamsTest
{
    @Test
    public void givenNullCollection_whenUsingStreams_thenGetEmptyStream()
    {
        // given
        HashSet<String> strings = null;

        // when
        final var list = collectionToStream(strings).map(s->s.toUpperCase()).collect(Collectors.toList());

        // then
        Assertions.assertTrue(list.isEmpty());
    }

    private Stream<String> collectionToStream(Collection<String> collection) {
        return Optional.ofNullable(collection)
                .map(Collection::stream)
                .orElseGet(Stream::empty);
    }
}
