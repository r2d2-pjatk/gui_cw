public
    class Main {

    public static void main(String[] args) {

        NotificationService service = new NotificationService("OnlineStore");

        service.addChannel(new EmailNotification("jan@pj.edu"));
        service.addChannel(new SmsNotification("+48 22 58 44 500"));

//TODO 01: implements Notification interface inline
        Notification n = new Notification() {
            @Override
            public void send(String message) {
                System.out.println("[PUSH] " + message);
            }

            @Override
            public String getType() {
                return "PUSH";
            }
        };

        service.addChannel(n);
//TODO 09: NotificationFilter is @FunctionalInterface
        NotificationFilter blankFilter = (msg) -> !msg.isBlank();
        NotificationFilter lengthFilter = (msg) -> msg.length() <= 200;
        NotificationFilter spamFilter = (msg) -> !msg.toLowerCase().contains("spam");

        service.addFilter(blankFilter);
        service.addFilter(lengthFilter);
        service.addFilter(spamFilter);
//TODO 05
        NotificationListener nl = new NotificationListener() {
            private int successCount;
            private int failCount;

            @Override
            public void onSuccess(String type, String message) {
                successCount++;
                System.out.println("  [AUDIT] OK #" + successCount + " via " + type);
            }

            @Override
            public void onFailure(String type, String message, String reason) {
                failCount++;
                System.out.println("  [AUDIT] FAIL #" + failCount + " via " + type + " -- " + reason);
            }
        };

        service.addListener(nl);

        service.sendAll("Your order #1234 has been shipped!");
        service.sendAll("");
        service.sendAll("This is SPAM content");
        service.sendAll("Welcome to our store!");

        service.printHistory();

//TODO 11
        System.out.println("\n--- EMAIL only ---");

        NotificationService.Result[] emailResults = service.getByChannel("EMAIL");
        for (int i = 0; i < emailResults.length; i++) {
            System.out.println(emailResults[i].toString());
        }
//TODO 13
        System.out.println("\n--- Sorted by timestamp (newest first) ---");

        NotificationService.Result[] sorted = NotificationService.sort(
                service.getSuccessful(),
                (result1, result2) -> result2.getTimestamp().compareTo(result1.getTimestamp())
        );

        for (int i = 0; i < sorted.length; i++) {
            System.out.println(sorted[i].toString());
        }
    }
}