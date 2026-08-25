/**
 * Entry point for the Bob chatbot application.
 */
public class Bob {
    /**
     * Starts the chatbot.
     *
     * @param args command-line arguments, currently unused
     */
    public static void main(String[] args) {
        try {
            ChatBot chatBot = new ChatBot();
            chatBot.run();
        } catch (BobException exception) {
            System.out.println(exception.getMessage());
        }

    }
}
