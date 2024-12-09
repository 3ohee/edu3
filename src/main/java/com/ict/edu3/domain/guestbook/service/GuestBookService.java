package com.ict.edu3.domain.guestbook.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ict.edu3.domain.guestbook.vo.GuestBookVO;

@Service
public interface GuestBookService {
    List<GuestBookVO> getGuestBookList();

    GuestBookVO getGuestBookById(String gb_idx);
    
    int getGuestBookUpdate(GuestBookVO gvo);
    
    int getGuestBookDelete(String gb_idx);

    int getGuestBookWrite(GuestBookVO gvo);

}
