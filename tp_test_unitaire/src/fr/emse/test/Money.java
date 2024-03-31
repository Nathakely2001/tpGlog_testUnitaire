
package fr.emse.test;


import java.util.Vector;

class Money implements IMoney {
    private int fAmount;
    private String fCurrency;

    public Money(int amount, String currency) {
        fAmount = amount;
        fCurrency = currency;
    }

    public int amount() {
        return fAmount;
    }

    public String currency() {
        return fCurrency;
    }

    public IMoney add(IMoney m) {
        return m.addMoney(this);
    }

    public IMoney addMoney(Money m) {
        if (currency().equals(m.currency())) {
            int totalAmount = amount() + m.amount();
            if (totalAmount == 0) {
                return new Money(0, fCurrency);
            } else {
                return new Money(totalAmount, fCurrency);
            }
        } else {
            // Handle different currencies
            return null; // For simplicity, returning null
        }
    }

    public IMoney addMoneyBag(MoneyBag mb) {
        return mb.addMoney(this);
    }
}

class MoneyBag implements IMoney {
    private Vector<Money> fMonies = new Vector<Money>();

    public MoneyBag(Money m1, Money m2) {
        appendMoney(m1);
        appendMoney(m2);
    }

    public MoneyBag(Money bag[]) {
        for (int i = 0; i < bag.length; i++)
            appendMoney(bag[i]);
    }

    private void appendMoney(Money m) {
        if (fMonies.isEmpty()) {
            fMonies.add(m);
        } else {
            int i = 0;
            while ((i < fMonies.size())
                    && (!(fMonies.get(i).currency().equals(m.currency()))))
                i++;
            if (i >= fMonies.size()) {
                fMonies.add(m);
            } else {
                fMonies.set(i, new Money(fMonies.get(i).amount() +
                        m.amount(),
                        m.currency()));
            }
        }
    }

    public IMoney add(IMoney m) {
        return m.addMoneyBag(this);
    }

    public IMoney addMoney(Money m) {
        appendMoney(m);
        return this;
    }

    public IMoney addMoneyBag(MoneyBag mb) {
        for (Money m : mb.fMonies) {
            appendMoney(m);
        }
        return this;
    }
}  
