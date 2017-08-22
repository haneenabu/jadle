package dao;

import models.Restaurant;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oRestaurantDaoTest {
    private Sql2oRestaurantDao restaurantDao;
    private Connection conn; //must be sql2o class conn

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        restaurantDao = new Sql2oRestaurantDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void add_InstantiatesNewRestaurant() throws Exception {
        Restaurant test1 = setupRestaurant();
        assertTrue(test1 instanceof Restaurant);
    }

    @Test
    public void getAll_RetrieveAllRestaurants_2() throws Exception {
        Restaurant test1 = setupRestaurant();
        Restaurant test2 = setupAltRestaurant();
        restaurantDao.add(test1);
        restaurantDao.add(test2);
        assertEquals(2,restaurantDao.getAll().size());
    }

    @Test
    public void findById_FindASpecificRestaurant_FishWitch() throws Exception {
        Restaurant test1 = setupRestaurant();
        restaurantDao.add(test1);
        Restaurant foundRestaurant = restaurantDao.findById(1);
        assertEquals("Fish Witch",foundRestaurant.getName());
    }

    @Test
    public void update_EvaluateIfNameChanged_Whataburger() throws Exception {
        Restaurant test1 = setupRestaurant();
        restaurantDao.add(test1);
        restaurantDao.update(1,"Whataburger","sdf","32421", "423-123-3252", "fdf", "sdfds", "sdf");
        Restaurant foundRestaurant = restaurantDao.findById(1);
        assertEquals("Whataburger", foundRestaurant.getName());
    }

    @Test
    public void delete_DeleteRestaurant_null() throws Exception {
        Restaurant test1 = setupRestaurant();
        restaurantDao.add(test1);
        restaurantDao.deleteById(1);
        assertEquals(0, restaurantDao.getAll().size());
    }

    @Test
    public void getNameReturnsCorrectName() throws Exception {
        Restaurant testRestaurant = setupRestaurant();
        assertEquals("Fish Witch", testRestaurant.getName());
    }

    @Test
    public void getAddressReturnsCorrectAddress() throws Exception {
        Restaurant testRestaurant = setupRestaurant();
        assertEquals("214 NE Broadway", testRestaurant.getAddress());
    }

    @Test
    public void getZipReturnsCorrectZip() throws Exception {
        Restaurant testRestaurant = setupRestaurant();
        assertEquals("97232", testRestaurant.getZipcode());
    }
    @Test
    public void getPhoneReturnsCorrectPhone() throws Exception {
        Restaurant testRestaurant = setupRestaurant();
        assertEquals("503-402-9874", testRestaurant.getPhone());
    }

    @Test
    public void getWebsiteReturnsCorrectWebsite() throws Exception {
        Restaurant testRestaurant = setupAltRestaurant();
        assertEquals("no website available", testRestaurant.getWebsite());
    }

    @Test
    public void getEmailReturnsCorrectEmail() throws Exception {
        Restaurant testRestaurant = setupAltRestaurant();
        assertEquals("no email available", testRestaurant.getEmail());
    }

    @Test
    public void getImageReturnsCorrectImage() throws Exception {
        Restaurant testRestaurant = setupAltRestaurant();
        assertEquals("/resources/images/uploads/no_image.jpg", testRestaurant.getImage());
    }

    @Test
    public void setNameSetsCorrectName() throws Exception {
        Restaurant testRestaurant = setupRestaurant();
        testRestaurant.setName("Steak House");
        assertNotEquals("Fish Witch",testRestaurant.getName());
    }

    @Test
    public void setAddressSetsCorrectAddress() throws Exception {
        Restaurant testRestaurant = setupRestaurant();
        testRestaurant.setAddress("6600 NE Ainsworth");
        assertNotEquals("214 NE Broadway", testRestaurant.getAddress());
    }

    @Test
    public void setZipSetsCorrectZip() throws Exception {
        Restaurant testRestaurant = setupRestaurant();
        testRestaurant.setZipcode("78902");
        assertNotEquals("97232", testRestaurant.getZipcode());
    }
    @Test
    public void setPhoneSetsCorrectPhone() throws Exception {
        Restaurant testRestaurant = setupRestaurant();
        testRestaurant.setPhone("971-898-7878");
        assertNotEquals("503-402-9874", testRestaurant.getPhone());
    }

    @Test
    public void setWebsiteSetsCorrectWebsite() throws Exception {
        Restaurant testRestaurant = setupRestaurant();
        testRestaurant.setWebsite("http://steakhouse.com");
        assertNotEquals("http://fishwitch.com", testRestaurant.getWebsite());
    }

    @Test
    public void setEmailSetsCorrectEmail() throws Exception {
        Restaurant testRestaurant = setupRestaurant();
        testRestaurant.setEmail("steak@steakhouse.com");
        assertNotEquals("hellofishy@fishwitch.com", testRestaurant.getEmail());
    }

    @Test
    public void setImageSetsCorrectImage() throws Exception {
        Restaurant testRestaurant = setupRestaurant();
        testRestaurant.setImage("steakhouse.jpg");
        assertNotEquals("fishwitch.jpg", testRestaurant.getImage());
    }

    //helper method
    public Restaurant setupRestaurant (){
        return new Restaurant("Fish Witch", "214 NE Broadway", "97232", "503-402-9874", "http://fishwitch.com", "hellofishy@fishwitch.com", "/no_image.jpg");

    }

    public Restaurant setupAltRestaurant (){
        return new Restaurant("Fish Witch", "214 NE Broadway", "97232", "503-402-9874");

    }
}