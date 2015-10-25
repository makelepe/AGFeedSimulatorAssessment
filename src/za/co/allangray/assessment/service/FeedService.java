package za.co.allangray.assessment.service;

import java.util.List;

import za.co.allangray.assessment.model.User;
import za.co.allangray.assessment.repository.UserRepository;
import za.co.allangray.assessment.util.FileUtil;

public class FeedService {
	
	public void load (String userFile, String feedFile) {
            try {
                List<String> userFileLines = FileUtil.getInstance().readFile(userFile);
                List<String> feedFileLines = FileUtil.getInstance().readFile(feedFile);
                
                UserRepository.getInstance().loadData(userFileLines, feedFileLines);
            } catch (Exception ex) {
                System.err.println("Error reading files : " + ex.getMessage());
            }
	}
	
	public List<User> getUsers () {
		return UserRepository.getInstance().getUsers();
	}

}
