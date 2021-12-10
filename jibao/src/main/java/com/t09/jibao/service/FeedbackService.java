package com.t09.jibao.service;

import com.t09.jibao.domain.Feedback;

public interface FeedbackService {
    // save
    Feedback save(Feedback feedback);

    // add a new feedback
    int add(Long uid, String content);

    // find feedback by id
    Feedback findById(Long id);
}
