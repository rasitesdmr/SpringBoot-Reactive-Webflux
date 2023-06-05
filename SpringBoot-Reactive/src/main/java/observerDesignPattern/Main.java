package observerDesignPattern;

public class Main {
    public static void main(String[] args) {
        MessageStream messageStream = new MessageStream();
        Observer emailClient = new EmailClient();
        Observer mobilClient = new MobileClient();

        messageStream.registerObserver(emailClient);
        messageStream.registerObserver(mobilClient);

        messageStream.setMessage("Observer Design Pattern");
    }
}
