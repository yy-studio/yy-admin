package com.yystudio.base.module.support.codegenerator.service.variable.front;

import com.yystudio.base.module.support.codegenerator.domain.form.CodeGeneratorConfigForm;
import com.yystudio.base.module.support.codegenerator.service.variable.CodeGenerateBaseVariableService;

import java.util.*;

/**
 * @Author yy-studio
 * @Date 2022/9/29 17:20:41
 * 
 *
 * @Copyright  远游工作室
 */

public class ApiVariableService extends CodeGenerateBaseVariableService {

    @Override
    public boolean isSupport(CodeGeneratorConfigForm form) {
        return true;
    }

    @Override
    public Map<String, Object> getInjectVariablesMap(CodeGeneratorConfigForm form) {
        Map<String, Object> variablesMap = new HashMap<>();

        return variablesMap;
    }
}
