package com.dogtiger.challus.repository;

import com.dogtiger.challus.entity.Challenge;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ChallengeMapper {
    public int saveChallenge(Challenge challenge);
    public Challenge getChallengeByChallengeId(int challengeId);
    public List<Challenge> getChallengeList(Map<String, Object> paramsMap);
    public int getChallengeCount(Map<String, Object> paramsMap);
    public int getLikeState(int challengeId);
    public int getUserLikeState(Challenge challenge);
    public int insertLike(Challenge challenge);
    public int cancelLike(Challenge challenge);
    public int challengeDelete(int challengeId);
}
