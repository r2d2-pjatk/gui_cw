public class EmailNotification extends BaseNotification {

    public EmailNotification(String email) {
        super(email);
    }

    @Override
    public String getType() {
        return "EMAIL";
    }

    @Override
    public String formatMessage(String message) {
        return String.format("Subject: Notification | To: %s | Body: %s", getRecipient(), message);
    }

    @Override
    public void doSend(String formatted) {
        System.out.println("[" + getType() + "] " + formatted);
    }

}
