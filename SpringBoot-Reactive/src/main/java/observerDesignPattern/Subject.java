package observerDesignPattern;

public interface Subject { // Konu

    void registerObserver(Observer observer);  // Kayıt gözlemcisi
    void removeObserver(Observer observer);
    void notifyObserver();
}
