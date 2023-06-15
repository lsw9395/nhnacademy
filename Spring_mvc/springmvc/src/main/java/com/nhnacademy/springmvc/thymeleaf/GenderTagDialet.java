package com.nhnacademy.springmvc.thymeleaf;

import org.thymeleaf.context.IExpressionContext;
import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.dialect.IExpressionObjectDialect;
import org.thymeleaf.expression.IExpressionObjectFactory;
import org.thymeleaf.spring5.expression.SpringStandardExpressionObjectFactory;

import java.util.Collections;
import java.util.Set;

public class GenderTagDialet extends AbstractDialect implements IExpressionObjectDialect {
    public GenderTagDialet(){
        super("genderTag");
    }

    @Override
    public IExpressionObjectFactory getExpressionObjectFactory() {
        return new SpringStandardExpressionObjectFactory(){
            @Override
            public Set<String> getAllExpressionObjectNames() {
                return Collections.singleton("genderTag");
            }

            @Override
            public Object buildObject(IExpressionContext context, String expressionObjectName) {
                super.buildObject(context,expressionObjectName);
                return new GenderTagUtils();
            }

            @Override
            public boolean isCacheable(String expressionObjectName) {
                return true;
            }
        };
    }
}
