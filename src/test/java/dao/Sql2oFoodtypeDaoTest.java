package dao;

import models.Foodtype;
import models.Restaurant;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oFoodtypeDaoTest {
    private Sql2oFoodtypeDao foodTypeDao;
    private Connection conn; //must be sql2o class conn

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        foodTypeDao = new Sql2oFoodtypeDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void add_InstantiatesNewRestaurant() throws Exception {
        Foodtype testFood = setupFoodType();
        assertTrue(testFood instanceof Foodtype);
    }

    @Test
    public void getAll_RetrieveAllFoodTypes_2() throws Exception {
        Foodtype testFood = setupFoodType();
        Foodtype testFood2 = new Foodtype("Korean");
        foodTypeDao.add(testFood);
        foodTypeDao.add(testFood2);
        assertEquals(2,foodTypeDao.getAll().size());
    }

    //helper
    Foodtype setupFoodType(){
        return new Foodtype("Thai");
    }
}