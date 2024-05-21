package application;

import java.net.URL;
import java.util.ResourceBundle;

public interface Initializable {
    /**
     * Initializes the controller after its root element has been completely processed.
     *
     * @param url the location used to resolve relative paths for the root object, or
     *            {@code null} if the location is not known.
     * @param rb  the resources used to localize the root object, or {@code null} if
     *            the root object was not localized.
     */
    void initialize(URL url, ResourceBundle rb);
}
