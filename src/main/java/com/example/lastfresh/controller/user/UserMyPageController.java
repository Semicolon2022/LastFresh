package com.example.lastfresh.controller.user;


import com.example.lastfresh.domain.repository.BillRepository;
import com.example.lastfresh.domain.repository.MemberRepository;
import com.example.lastfresh.domain.vo.UserVO;
import com.example.lastfresh.service.user.MyPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
/*유저 마이 페이지*/

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user/myPage/*")
public class UserMyPageController {
    private final MyPageService myPageService;



    //회원 정보 수정 페이지
    @GetMapping("/myChangeInfo")
    public void myChangeInfo(Long userNum, HttpServletRequest request, Model model) throws Exception {
        model.addAttribute("user", myPageService.get(userNum));
    };

    //정보 수정
    @PostMapping("/modify")
    public RedirectView modify(UserVO userVO, RedirectAttributes rttr) throws Exception {
        String result = null;
        Long userNum = userVO.getUserNum();

        rttr.addAttribute("userNum", userNum);
        rttr.addAttribute("result", myPageService.modify(userVO, userNum) ? "success" : "failure");

        return new RedirectView("myChangeInfo");
    };


    // 비밀번호 확인 페이지
    @GetMapping("/myCheckPw")
    public void myCheckPw(Long userNum, HttpServletRequest request, Model model) throws Exception {
        model.addAttribute("user", myPageService.get(userNum));
    }


    @GetMapping("/myOrder")
    public void myOrder(Long userNum, Model model) throws Exception{
        model.addAttribute("bills", myPageService.getBills(userNum));
        model.addAttribute("userNum", userNum);
    }

    @GetMapping("/myOrderDetail")
    public void myOrderDetail(Long userNum, Long billOrderNum, Model model) throws Exception{
        model.addAttribute("bill", myPageService.getBill(billOrderNum));
        model.addAttribute("userNum", userNum);
        model.addAttribute("user", myPageService.get(userNum));
        model.addAttribute("billOrderNum", billOrderNum);
       // log.info("ffffffffffffffffff" + myPageService.getBill(billOrderNum).toString());
    }

    //cancel one item
    @GetMapping("/cancelOrder")
    public RedirectView cancelOrder(Long billProductNum, Long userNum, Long billOrderNum, RedirectAttributes rttr) throws Exception {
        myPageService.cancelOrder(billProductNum);
        rttr.addAttribute("bill", myPageService.getBill(billOrderNum));
        rttr.addAttribute("userNum", userNum);
        rttr.addAttribute("user", myPageService.get(userNum));
        rttr.addAttribute("billOrderNum", billOrderNum);
        return new RedirectView("myOrderDetail");
    };

    //cancel all items
  /*  @GetMapping("/cancelAll")
    public RedirectView cancelAll(Long userNum, Long billOrderNum, RedirectAttributes rttr) throws Exception {
        myPageService.cancelAll(billOrderNum);
        rttr.addAttribute("bill", myPageService.getBill(billOrderNum));
        rttr.addAttribute("userNum", userNum);
        rttr.addAttribute("user", myPageService.get(userNum));
        rttr.addAttribute("billOrderNum", billOrderNum);
        return new RedirectView("myOrderDetail");
    };*/

    @GetMapping("/myReviewUnwritten")
    public void myReviewUnwritten(){}

    @GetMapping("/myReviewWrite")
    public void myReviewWrite(){}

    @GetMapping("/myReviewWritten")
    public void myReviewWritten(){}

    @GetMapping("/display")
    @ResponseBody
    public byte[] getFile(String fileName) throws IOException {
        return FileCopyUtils.copyToByteArray(new File("/Users/macintoshhd/Desktop/upload/" + fileName));
        //  return FileCopyUtils.copyToByteArray(new File("C:/upload/" + fileName));
    }
}