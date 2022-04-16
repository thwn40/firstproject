package com.sjboard.firstproject.service;


import com.sjboard.firstproject.domain.Member;
import com.sjboard.firstproject.domain.Notice;
import com.sjboard.firstproject.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public Long makeNotice(Member sender, Member receiver, String content) {
        return noticeRepository.save(Notice.builder().sender(sender).receiver(receiver).content(content).build()).getId();
    }
}
