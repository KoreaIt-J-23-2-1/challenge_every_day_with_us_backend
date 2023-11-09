package com.dogtiger.challus.service;

import com.dogtiger.challus.dto.LettersResDto;
import com.dogtiger.challus.entity.Letter;
import com.dogtiger.challus.entity.Order;
import com.dogtiger.challus.entity.User;
import com.dogtiger.challus.repository.LetterMapper;
import com.dogtiger.challus.security.PrincipalUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LetterService {
    private final LetterMapper letterMapper;
    public List<LettersResDto> getLetters() {
        List<LettersResDto> letters = null;
        letters = letterMapper.findLettersByUserId(getCurrentUser().getUserId()).stream().map(Letter::toLettersResDto).collect(Collectors.toList());
        return letters;
    }

    public int getLettersCount() {
        User user = getCurrentUser();
        return letterMapper.getLettersCountByUserId(user.getUserId());
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean changeReadStatus(int letterId) {
        User user = getCurrentUser();
        Letter letter = letterMapper.findLetterByLetterId(letterId);
        if(user.getUserId() != letter.getReceiverUserId()) {
            return false;
        }
        if(letter.getIsRead() == 0) {
            letter.setIsRead(1);
        }else{
            letter.setIsRead(0);
        }
        letterMapper.updateLetter(letter);
        return true;
    }

    private User getCurrentUser(){
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = principalUser.getUser();
        return user;
    }
}