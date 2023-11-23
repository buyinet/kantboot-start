package com.kantboot.functional.template.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.kantboot.functional.template.dao.repository.FunctionalTemplatePrintColumnRepository;
import com.kantboot.functional.template.dao.repository.FunctionalTemplatePrintRepository;
import com.kantboot.functional.template.dao.repository.FunctionalTemplatePrintVariableRepository;
import com.kantboot.functional.template.domain.entity.FunctionalTemplatePrint;
import com.kantboot.functional.template.domain.entity.FunctionalTemplatePrintColumn;
import com.kantboot.functional.template.domain.entity.FunctionalTemplatePrintVariable;
import com.kantboot.functional.template.service.IFunctionalTemplatePrintService;
import com.kantboot.util.common.exception.BaseException;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FunctionalTemplatePrintServiceImpl implements IFunctionalTemplatePrintService {

    @Resource
    private FunctionalTemplatePrintRepository repository;

    @Resource
    private FunctionalTemplatePrintColumnRepository columnRepository;

    @Resource
    private FunctionalTemplatePrintVariableRepository variableRepository;

    @Override
    public FunctionalTemplatePrint save(FunctionalTemplatePrint functionalTemplatePrint) {
        // 如果functionalTemplatePrint.getId()不为空，那么就是更新
        if(functionalTemplatePrint.getId()!=null){
            Optional<FunctionalTemplatePrint> or = repository.findById(functionalTemplatePrint.getId())
                    .or(() -> {
                        // 打印模板不存在
                        throw BaseException.of("printTemplateNotExist", "打印模板不存在");
                    });
            // 先删除原有的
            columnRepository.deleteAll(or.get().getColumnList());
            variableRepository.deleteAll(or.get().getVariableList());
        }


        List<FunctionalTemplatePrintColumn> columnList= BeanUtil.copyToList(functionalTemplatePrint.getColumnList(),FunctionalTemplatePrintColumn.class);
        List<FunctionalTemplatePrintVariable> variableList= BeanUtil.copyToList(functionalTemplatePrint.getVariableList(),FunctionalTemplatePrintVariable.class);
        // 如果为空，就new
        if(columnList==null){
            columnList=List.of();
        }
        if(variableList==null){
            variableList=List.of();
        }

        // 清空关联
        functionalTemplatePrint.setColumnList(null);
        functionalTemplatePrint.setVariableList(null);

        FunctionalTemplatePrint save = repository.save(functionalTemplatePrint);

        // 清空id
        columnList.forEach(item->item.setId(null).setPrintId(save.getId()));
        variableList.forEach(item->item.setId(null).setPrintId(save.getId()));

        // 保存
        columnRepository.saveAll(columnList);
        variableRepository.saveAll(variableList);

        return save;
    }

}
