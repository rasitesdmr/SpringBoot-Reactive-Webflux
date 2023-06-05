package observerDesignPattern;

public class MobileClient implements Observer{
    @Override
    public void update(String message) {
        System.out.println("Mobil alındı : "+message);
    }
}
