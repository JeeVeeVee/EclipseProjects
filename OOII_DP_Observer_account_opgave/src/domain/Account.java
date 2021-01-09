package domain;

import java.util.ArrayList;

public class Account implements Subject{

    // Account balance
    private double balance;

    // readonly Account name
    private String name;
    
    private ArrayList<Observer> observers;

    
    // Account constructor
    public Account(String accountName, double openingDeposit) {
        this.observers = new ArrayList<>();

        name = accountName;
        setBalance(openingDeposit);
        
    }

    // set Account balance and notify observers of change
    private void setBalance(double accountBalance) {
        balance = accountBalance;
        notifyListeners();
    }

    // get Account balance
    public double getBalance() {
        return balance;
    }

    // withdraw funds from Account
    public void withdraw(double amount) throws IllegalArgumentException {
        if (amount < 0) {
            throw new IllegalArgumentException(
                    "Cannot withdraw negative amount");
        }

        // update Account balance
        setBalance(getBalance() - amount);
    }

    // deposit funds in account
    public void deposit(double amount) throws IllegalArgumentException {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot deposit negative amount");
        }

        // update Account balance
        setBalance(getBalance() + amount);
    }

    // get Account name (readonly)
    public String getName() {
        return name;
    }

	@Override
	public void addObserver(Observer observer) {
		this.observers.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		this.observers.remove(observer);
	}
	
	public void notifyListeners() {
		observers.forEach(e -> e.update(balance));
	}
}
