package observerDesignPattern;

public class EmailClient implements Observer{

    @Override
    public void update(String message) {
        System.out.println("Email alındı : "+ message);
    }
}
