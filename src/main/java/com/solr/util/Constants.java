package com.solr.util;


import java.util.Arrays;
import java.util.List;

/**
 * Created by Yufeng Yao on 2015/12/1 13:12.
 */
public interface Constants {

    //solrType
    int seachType_selectByTag = 1;
    int seachType_selectByPhone = 2;
    int seachPhone = 3;

    //solr特殊符号  + – && || ! ( ) { } [ ] ^ ” ~ * ? : \
    List<Character> SPECIAL_CHAR = Arrays.asList('+', '-', '&', '|', '!', '(', ')', '{', '}', '[', ']', '^', '\'', '~', '?', ':', '\\');

}
