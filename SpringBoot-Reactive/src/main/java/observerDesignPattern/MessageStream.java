package observerDesignPattern;

import java.util.ArrayList;
import java.util.List;

public class MessageStream implements Subject {

    private final List<Observer> observers = new ArrayList<>();
    private String message;

    public void setMessage(String message) {
        this.message = message;
        notifyObserver();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver() {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}
