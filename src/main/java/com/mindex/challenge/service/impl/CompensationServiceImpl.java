package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompensationServiceImpl implements CompensationService {
    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);
    @Autowired
    private CompensationRepository compensationRepository;

    @Override
    public Compensation create(Compensation compensation) {
        LOG.debug("Creating Compensation [{}]", compensation);
        compensationRepository.insert(compensation);
        return compensation;
    }

    @Override
    public Compensation read(String id){
        Compensation comp = compensationRepository.findByEmployee_EmployeeId(id);
        if (comp == null){
            throw new RuntimeException("Wrong Employee ID");
        }
        return comp;
    }
}
