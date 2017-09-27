package db.util;

public interface PersonRelatedAPI {
	void getProfile(Long id);
	void getCommonInterestsOfMyFriends(Long id);
	void getCommonFriends(Long id1, Long id2);
	void getPersonsWithMostCommonInterests(Long id);
	void getJobRecommendation(Long id);
	void getShortestFriendshipPath(Long id1, Long id2);
}
