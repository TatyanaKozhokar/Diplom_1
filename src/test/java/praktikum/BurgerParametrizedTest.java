package praktikum;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerParametrizedTest {

    private final Bun bun;
    private final List<Ingredient> testIngredients;
    private final float expectedPrice;
    private final String expectedReceipt;

    public BurgerParametrizedTest(Bun bun, List<Ingredient> testIngredients,
                                  float expectedPrice, String expectedReceipt) {
        this.bun = bun;
        this.testIngredients = testIngredients;
        this.expectedPrice = expectedPrice;
        this.expectedReceipt = expectedReceipt;

    }
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        IngredientType fillingType = mock(IngredientType.class);
        when(fillingType.toString()).thenReturn("FILLING");

        IngredientType sauceType = mock(IngredientType.class);
        when(sauceType.toString()).thenReturn("SAUCE");

        Ingredient cutlet = mock(Ingredient.class);
        when(cutlet.getName()).thenReturn("burger patty");
        when(cutlet.getType()).thenReturn(fillingType);
        when(cutlet.getPrice()).thenReturn(0.6f);

        Ingredient ketchup = mock(Ingredient.class);
        when(ketchup.getName()).thenReturn("ketchup");
        when(ketchup.getType()).thenReturn(sauceType);
        when(ketchup.getPrice()).thenReturn(0.4f);


        Ingredient cheese = mock(Ingredient.class);
        when(cheese.getName()).thenReturn("cheese");
        when(cheese.getType()).thenReturn(fillingType);
        when(cheese.getPrice()).thenReturn(1.0f);

        Ingredient mayonnaise = mock(Ingredient.class);
        when(mayonnaise.getName()).thenReturn("mayonnaise");
        when(mayonnaise.getType()).thenReturn(sauceType);
        when(mayonnaise.getPrice()).thenReturn(0.3f);

        Bun whiteBun = mock(Bun.class);
        when(whiteBun.getName()).thenReturn("white bun");
        when(whiteBun.getPrice()).thenReturn(0.5f);

        Bun redBun = mock(Bun.class);
        when(redBun.getName()).thenReturn("red bun");
        when(redBun.getPrice()).thenReturn(0.6f);

        return Arrays.asList(new Object[][]{
                {whiteBun, Arrays.asList(cutlet, ketchup), 2.0f, "(==== white bun ====)%n= filling burger patty =%n= sauce ketchup =%n(==== white bun ====)%n%nPrice: 2,000000%n"},
                {redBun, Arrays.asList(cheese, mayonnaise), 2.5f, "(==== red bun ====)%n= filling cheese =%n= sauce mayonnaise =%n(==== red bun ====)%n%nPrice: 2,500000%n"}
        });
    }

    @Test
    public void testSetBun(){
        Burger burger = new Burger();
        burger.setBuns(bun);
        assertEquals(bun.getName(), burger.bun.getName());
    }

    @Test
    public void testAddIngredient() {
        Burger burger = new Burger();
        burger.addIngredient(testIngredients.get(0));
        assertEquals(testIngredients.get(0).getName(), burger.ingredients.get(0).getName());
    }

    @Test
    public void testRemoveIngredient(){
        Burger burger = new Burger();
        burger.addIngredient(testIngredients.get(0));
        burger.addIngredient(testIngredients.get(1));
        burger.removeIngredient(1);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void testMoveIngredient(){
        Burger burger = new Burger();
        burger.addIngredient(testIngredients.get(0));
        burger.addIngredient(testIngredients.get(1));
        burger.moveIngredient(0, 1);
        assertEquals(testIngredients.get(1).getName(), burger.ingredients.get(0).getName());
    }

    @Test
    public void testGetPrice(){
        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(testIngredients.get(0));
        burger.addIngredient(testIngredients.get(1));
        assertEquals(expectedPrice, burger.getPrice(), 0);
    }

    @Test
    public void testGetReceipt(){
        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(testIngredients.get(0));
        burger.addIngredient(testIngredients.get(1));
        assertEquals(String.format(expectedReceipt), burger.getReceipt());


    }

}
