import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class MoneyBagTest {
    private Money f12CHF;
    private Money f7USD;
    private MoneyBag fMB1;
    private MoneyBag fMB2;

    @Before
    public void setUp() {
        f12CHF = new Money(12, "CHF");
        f7USD = new Money(7, "USD");
        fMB1 = new MoneyBag(f12CHF);
        fMB2 = new MoneyBag(f7USD);
    }

    @Test
    public void testMixedSimpleAdd() {
        Money bag[] = { f12CHF, f7USD };
        MoneyBag expected = new MoneyBag(bag);
        assertEquals(expected, f12CHF.add(f7USD));
    }

    @Test
    public void testBagSimpleAdd() {
        MoneyBag expected = new MoneyBag(f12CHF, f7USD);
        assertEquals(expected, fMB1.add(f7USD));
    }

    @Test
    public void testSimpleBagAdd() {
        MoneyBag expected = new MoneyBag(f12CHF, f7USD);
        assertEquals(expected, f12CHF.add(fMB2));
    }

    @Test
    public void testBagBagAdd() {
        MoneyBag expected = new MoneyBag(f12CHF, f7USD);
        assertEquals(expected, fMB1.add(fMB2));
    }
}