package application;

/**
 * An interface for handling sending messages
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */
public interface MessageHandlerIF {

    /**
     * Sends a message to the message handler
     * @param message the message to send to the messenger
     */
    void sendMessage(String message);

}
