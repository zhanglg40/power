/**
 * 
 */
package org.mybatis.spring;

import java.io.IOException;

import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * 
 * @team IT Team
 * @author zhanglg
 * @version 1.0
 * @time  2017年5月19日
 */

public class FixedSqlSessionFactory extends SqlSessionFactoryBean{
    @Override
    protected SqlSessionFactory buildSqlSessionFactory() throws IOException {
        try {
            return super.buildSqlSessionFactory();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            ErrorContext.instance().reset();
        }
        return null;
    }
}