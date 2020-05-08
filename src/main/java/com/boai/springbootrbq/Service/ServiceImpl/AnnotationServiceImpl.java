package com.boai.springbootrbq.Service.ServiceImpl;

import com.boai.springbootrbq.Service.AnnotationService;
import org.springframework.stereotype.Service;

@Service
public class AnnotationServiceImpl implements AnnotationService {

    @Override
    public String sayHello(String name) {
        return "annotation: hello," + name;
    }
}
