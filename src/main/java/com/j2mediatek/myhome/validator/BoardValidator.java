package com.j2mediatek.myhome.validator;

import com.j2mediatek.myhome.model.Board;
import org.aspectj.weaver.tools.ISupportsMessageContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BoardValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Board.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Board board = (Board)target;
        if (!StringUtils.hasText(board.getContent())) {
            errors.rejectValue("content", "", "내용을 입력하세요!!");
        }
    }
}
