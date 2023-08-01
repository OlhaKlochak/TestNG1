package data;

import org.testng.annotations.DataProvider;

public class DataTest {
    @DataProvider
    public static Object[][] getCert(){
        return new Object[][]{
                {"45924126","true"},
                {"1235","false"}

        };
    }
}
