package com.example.acm.service.deal.Impl;

import com.example.acm.service.ReplyproblemService;
import com.example.acm.service.deal.ReplyProblemDealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ggg on 2019/4/16.
 */
@Service
public class ReplyProblemDealImpl implements ReplyProblemDealService {

    @Autowired
    private ReplyproblemService replyproblemService;
}
