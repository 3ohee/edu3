package com.ict.edu3.domain.guestbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ict.edu3.domain.auth.vo.DataVO;
import com.ict.edu3.domain.guestbook.service.GuestBookService;
import com.ict.edu3.domain.guestbook.vo.GuestBookVO;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@Slf4j
@RestController
@RequestMapping("/api/guestbook")
public class GuestBookController {
    @Autowired
    private GuestBookService guestBookService ;

    @GetMapping("/list")
    public DataVO getGuestBookList() {
        DataVO dataVO = new DataVO();
        try {
            List<GuestBookVO> list = guestBookService.getGuestBookList();
            dataVO.setSuccess(true);
            dataVO.setMessage("게스트북 조회 성공");
            dataVO.setData(list);

        } catch (Exception e) {
            dataVO.setSuccess(false);
            dataVO.setMessage("게스트북 조회 실패");
        }
        
        return dataVO;
    }

    @GetMapping("/detail/{gb_idx}")
    public DataVO getGuestBookDetail(@PathVariable String gb_idx) {
        DataVO dataVO = new DataVO();
        try {
            // log.info("gb_idx : " + gb_idx);
            GuestBookVO gvo = guestBookService.getGuestBookById(gb_idx);

            dataVO.setSuccess(true);
            dataVO.setMessage("게스트북 상세보기 성공");
            dataVO.setData(gvo);

        } catch (Exception e) {
            dataVO.setSuccess(false);
            dataVO.setMessage("게스트북 상세보기 실패");
        }
        
        return dataVO;
    }
    
    
}
