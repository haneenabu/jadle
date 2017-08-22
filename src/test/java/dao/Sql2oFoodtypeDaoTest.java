package dao;

import models.Foodtype;
import models.Restaurant;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static dao.Sql2oRestaurantDaoTest.setupAltRestaurant;
import static dao.Sql2oRestaurantDaoTest.setupRestaurant;
import static org.junit.Assert.*;

public class Sql2oFoodtypeDaoTest {
    private Sql2oFoodtypeDao foodTypeDao;
    private Sql2oRestaurantDao restaurantDao;
    private Connection conn; //must be sql2o class conn

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        foodTypeDao = new Sql2oFoodtypeDao(sql2o);
        restaurantDao = new Sql2oRestaurantDao(sql2o);
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

    @Test
    public void addFoodTypeToRestaurantAddsTypeCorrectly() throws Exception {

        Restaurant testRestaurant = setupRestaurant();
        Restaurant altRestaurant = setupAltRestaurant();

        restaurantDao.add(testRestaurant);
        restaurantDao.add(altRestaurant);

        Foodtype testFoodtype = setupFoodType();

        foodTypeDao.add(testFoodtype);

        foodTypeDao.addFoodTypeToRestaurant(testFoodtype, testRestaurant);
        foodTypeDao.addFoodTypeToRestaurant(testFoodtype, altRestaurant);

        assertEquals(2, foodTypeDao.getAllRestaurantsForAFoodtype(testFoodtype.getId()).size());
    }

    @Test
    public void deleteingRestaurantAlsoUpdatesJoinTable() throws Exception {
        Foodtype testFoodtype  = new Foodtype("Seafood");
        foodTypeDao.add(testFoodtype);

        Restaurant testRestaurant = setupRestaurant();
        restaurantDao.add(testRestaurant);

        Restaurant altRestaurant = setupAltRestaurant();
        restaurantDao.add(altRestaurant);

        restaurantDao.addRestaurantToFoodType(testRestaurant,testFoodtype);
        restaurantDao.addRestaurantToFoodType(altRestaurant, testFoodtype);

        restaurantDao.deleteById(testRestaurant.getId());
        assertEquals(0, restaurantDao.getAllFoodtypesForARestaurant(testRestaurant.getId()).size());
    }

    //helper
    Foodtype setupFoodType(){
        return new Foodtype("Thai");
    }
}