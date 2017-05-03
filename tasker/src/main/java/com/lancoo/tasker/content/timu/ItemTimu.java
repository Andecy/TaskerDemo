package com.lancoo.tasker.content.timu;

import java.io.Serializable;

/**
 * Author: Andecy
 * Time: 2017/3/1
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public interface ItemTimu extends Serializable {

    float getScore();

    String getOptionAsk();

    int getIndex();

    String[] getOptions();

    String[] getOptionsKey();

    String getStandardAnswer();

    String getAnalysis();
}
