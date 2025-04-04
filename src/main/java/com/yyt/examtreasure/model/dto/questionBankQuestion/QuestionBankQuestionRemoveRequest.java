package com.yyt.examtreasure.model.dto.questionBankQuestion;

import lombok.Data;

import java.io.Serializable;

/**
 * 移除题目题库关系的请求
 */
@Data
public class QuestionBankQuestionRemoveRequest implements Serializable {

   //Serializable 是一个标记接口，此接口自身并未包含任何方法。它的主要用途是标记一个类，表明该类的对象能够被序列化与反序列化。
    /**
    *题库id
     */
    private  Long questionBankId;
    /**
     * 题目id
     */
    private Long questionId;

    //serialVersionUID 是一个可选的静态常量，用于确保序列化和反序列化时类的版本一致性。
    private static final long serialVersionUID = 1L;

}
