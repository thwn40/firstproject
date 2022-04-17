package com.sjboard.firstproject.service;


import com.sjboard.firstproject.domain.Member;
import com.sjboard.firstproject.domain.Notice;
import com.sjboard.firstproject.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    @Transactional
    public void checkNotice(Member receiver){
        noticeRepository.checkingNotice(receiver.getId());

    }

    @Transactional
    public Long makeNotice(Member sender, Member receiver, String content) {
        return noticeRepository.save(Notice.builder().sender(sender).receiver(receiver).content(content).build()).getId();
    }

    public Page<Notice> findNotice(Member receiver, Pageable pageable){
        return noticeRepository.findByReceiver(receiver, pageable);
    }
}
