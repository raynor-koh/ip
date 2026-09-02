package bob.gui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import javafx.geometry.Rectangle2D;

/** Tests calculation of consistently cropped avatar viewports. */
class AvatarViewTest {

    @Test
    void calculateViewport_portraitImage_returnsCenteredSquare() {
        Rectangle2D viewport = AvatarView.calculateViewport(600, 804);

        assertEquals(new Rectangle2D(0, 102, 600, 600), viewport);
    }

    @Test
    void calculateViewport_landscapeImage_returnsCenteredSquare() {
        Rectangle2D viewport = AvatarView.calculateViewport(400, 300);

        assertEquals(new Rectangle2D(50, 0, 300, 300), viewport);
    }

    @Test
    void calculateViewport_nonPositiveDimension_illegalArgumentExceptionThrown() {
        assertThrows(IllegalArgumentException.class,
                () -> AvatarView.calculateViewport(0, 300));
    }
}
