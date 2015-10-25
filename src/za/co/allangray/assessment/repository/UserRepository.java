package za.co.allangray.assessment.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import za.co.allangray.assessment.model.Feed;
import za.co.allangray.assessment.model.User;
import za.co.allangray.assessment.util.StringUtil;

/**
 * Singleton Data Repository - holds list of users to be queried.
 *
 * @author radingwanes
 *
 */
public class UserRepository {

    public final static Set<User> users = new HashSet<>();
    private static UserRepository instance;

    private UserRepository() {
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }

    private User findUser(String name) {
        for (User user : users) {
            if (name.equalsIgnoreCase(user.getName())) {
                return user;
            }
        }
        return null;
    }

    public void loadData(List<String> userLines, List<String> feedLines) {

        // create users
        for (String userLine : userLines) {
            User user = new User();
            user.setName(StringUtil.getUserName(userLine));

            // adding favourates
            for (String name : StringUtil.getFavourates(userLine)) {
                User favourate = new User();
                favourate.setName(name);
                user.addFavourate(favourate);
                users.add(favourate);
            }
            // merge lines
            if (users.contains(user)) {
                User _user = findUser(user.getName());
                user.addFavourates(_user.getFavourates());
                users.remove(_user);
            }
            users.add(user);
        }

        // load feeds
        for (String feedLine : feedLines) {
            String userCreatorName = StringUtil.getFeedCreatorName(feedLine);
            String message = StringUtil.getFeedMessage(feedLine);

            for (User user : users) {
                if (user.isUserFeed(userCreatorName)) {
                    Feed feed = new Feed();
                    feed.setUsername(userCreatorName);
                    feed.setMessage(message);
                    feed.setDate(new Date());
                    user.addFeed(feed);
                }

            }
        }

        System.out.println("successfully loaded users and feeds.");
    }

    public List<User> getUsers() {
        return new ArrayList<>(users);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public void addUsers(Set<User> _users) {
        users.addAll(_users);
    }

}
