public class SmsNotification extends BaseNotification {

    public SmsNotification(String phoneNumber) {
        super(phoneNumber);
    }

    @Override
    public String getType() {
        return "SMS";
    }

    @Override
    protected String formatMessage(String message) {
        String text = message.length() > 160 ? message.substring(157) : message;
        return "SMS to " + getRecipient() + ": " + text;
    }

    @Override
    protected void doSend(String formatted) {
        System.out.println("[" + getType() + "] " + formatted);
    }

}
