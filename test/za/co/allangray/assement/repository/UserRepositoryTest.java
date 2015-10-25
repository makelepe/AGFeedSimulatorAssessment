package za.co.allangray.assement.repository;

import za.co.allangray.assessment.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import za.co.allangray.assessment.model.User;

/**
 *
 * @author radingwanes
 */
public class UserRepositoryTest {

    private static UserRepository instance = null;
    private List<String> users = null;
    private List<String> feeds = null;

    public UserRepositoryTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        instance = UserRepository.getInstance();
    }

    @AfterClass
    public static void tearDownClass() {
        instance = null;
    }

    @Before
    public void setUp() {
        users = new ArrayList<>();
        users.add("Ward follows Alan");
        users.add("Alan follows Martin");
        users.add("Ward follows Martin, Alan, Sam");

        feeds = new ArrayList<>();
        feeds.add("Alan> If you have a procedure with 10 parameters, you probably missed some.");
        feeds.add("Ward> There are only two hard things in Computer Science: cache invalidation, naming things and off-by-1 errors.");
        feeds.add("Alan> Random numbers should not be generated with a method chosen at random.");
    }

    @After
    public void tearDown() {
        users.clear();
        feeds.clear();
    }

    /**
     * Test of loadData method
     */
    @Test
    public void testLoadData() {
        System.out.println("test loadData");
        instance.loadData(users, feeds);
        assertTrue(true);
    }

    /**
     * Test of getUsers method, users.
     */
    @Test
    public void testGetUsers() {
        System.out.println("test getUsers. expected : " + users.size());
        List<User> result = instance.getUsers();
        assertEquals(4, result.size());

        User result2 = null;
        for (User user : instance.getUsers()) {
            if (user.getName().equalsIgnoreCase("Ward")) {
                result2 = user;
            }
        }
        assertNotNull(result2);
        assertEquals(3, result2.getFeeds().size());
    }

}
