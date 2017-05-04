package com.lancoo.tasker.content.timu;

import java.io.Serializable;

/**
 * Author: Andecy
 * Time: 2017/3/1
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public interface IItemTimu extends Serializable {

    /**
     * @return 小题标准得分
     */
    float getScore();

    /**
     * @return 问题
     */
    String getOptionAsk();

    /**
     * @return 小题号
     */
    int getIndex();

    /**
     * @return 选项内容(选择题专属)
     */
    String[] getOptions();

    /**
     * @return 选项(选择题专属)
     */
    String[] getOptionsKey();

    /**
     * @return 标准答案
     */
    String getStandardAnswer();

    /**
     * @return 答案解析
     */
    String getAnalysis();

    /**
     * @return 小题题型名称
     */
    String getTypeName();

    /**
     * @return 小题题型
     */
    String getType();
}
