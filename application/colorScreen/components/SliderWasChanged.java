package application.colorScreen.components;

/**
 * An observer IF that communicated when a slider was changed,
 * which slider was changed, and the changed value.
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */
public interface SliderWasChanged {

    /**
     * A reference to the slider (with a reference to itself so we
     * can change the value accordingly).
     * @param value the value the slide has changed to
     * @param slider a reference to the slider that changed
     */
    void sliderWasChanged(int value, SliderPane slider);

}
