package za.co.allangray.assessment.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import za.co.allangray.assessment.util.FeedComparator;
import za.co.allangray.assessment.util.StringUtil;

public class User {

    private Integer id;
    private String name;
    private Set<User> favourates = new HashSet<>();
    private List<Feed> feeds = new ArrayList<>();

    public void init(String line) {
        this.setName(StringUtil.getUserName(line));

        /**
         * add favourate to user
         */
        for (String favourateUserName : StringUtil.getFavourates(line)) {
            User favourate = new User();
            favourate.setName(favourateUserName);
            this.addFavourate(favourate);
        }
    }

    public boolean isUserFeed(String feedCreator) {

        if (feedCreator.equalsIgnoreCase(this.getName())) {
            return true;
        }

        for (User user : this.getFavourates()) {
            if (feedCreator.equalsIgnoreCase(user.getName())) {
                return true;
            }
        }
        return false;
    }

    public List<Feed> getFeeds() {
        feeds.sort(new FeedComparator());
        return feeds;
    }

    public void addFavourate(User user) {
        if (favourates == null) {
            favourates = new HashSet<>();
        }
        favourates.add(user);
    }

    public void addFavourates(Set<User> _favourates) {
        if (_favourates != null) {
            for (User user : _favourates) {
                this.addFavourate(user);
            }
        }
    }

    public void addFeed(Feed feed) {
        if (feeds == null) {
            feeds = new ArrayList<>();
        }
        feeds.add(feed);
    }

    public Set<User> getFavourates() {
        if (favourates == null) {
            return new HashSet<>();
        }
        return favourates;
    }

    public void setFavourates(Set<User> favourates) {
        this.favourates = favourates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFeeds(List<Feed> feeds) {
        this.feeds = feeds;
    }

    @Override
    public int hashCode() {
        return this.getName() == null ? 0 : this.getName().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.getName() == null || other.getName() == null) {
            return false;
        }
        return this.getName().equalsIgnoreCase(other.getName());
    }

}
