package za.co.allangray.assessment.util;

import java.util.Comparator;

import za.co.allangray.assessment.model.Feed;

public class FeedComparator implements Comparator<Feed> {

	public int compare(Feed feed1, Feed feed2) {
		return feed1.getDate().compareTo(feed2.getDate());
	}

}
