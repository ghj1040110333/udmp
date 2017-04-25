/**
 *    Copyright 2009-2016 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package cn.com.git.udmp.test.mybatis.cursor;

import java.io.Reader;
import java.sql.Connection;
import java.util.Iterator;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import cn.com.git.udmp.modules.batch.dao.BatchTestSouceMapper;
import cn.com.git.udmp.modules.batch.entity.BatchTestSouce;

public class UdmpCursorSimpleTest {

    private static SqlSessionFactory sqlSessionFactory;

    @BeforeClass
    public static void setUp() throws Exception {
        // create a SqlSessionFactory
        Reader reader = Resources.getResourceAsReader("cn/com/git/udmp/test/mybatis/cursor/mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        reader.close();

        // populate in-memory database
        SqlSession session = sqlSessionFactory.openSession();
        Connection conn = session.getConnection();
        reader.close();
        session.close();
    }

    @Test
    public void shouldGetAllUser() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BatchTestSouceMapper mapper = sqlSession.getMapper(BatchTestSouceMapper.class);
        Cursor<BatchTestSouce> usersCursor = mapper.findListByCursor();
        try {
            Assert.assertFalse(usersCursor.isOpen());

            // Cursor is just created, current index is -1
            Assert.assertEquals(-1, usersCursor.getCurrentIndex());

            Iterator<BatchTestSouce> iterator = usersCursor.iterator();

            // Check if hasNext, fetching is started
            Assert.assertTrue(iterator.hasNext());
            Assert.assertTrue(usersCursor.isOpen());
            Assert.assertFalse(usersCursor.isConsumed());

            // next() has not been called, index is still -1
            Assert.assertEquals(-1, usersCursor.getCurrentIndex());

            BatchTestSouce user = iterator.next();
//            Assert.assertEquals("User1", user.getVcharC());
            System.out.println(user.getVcharC());
            Assert.assertEquals(0, usersCursor.getCurrentIndex());

            user = iterator.next();
            System.out.println(user.getVcharC());
//            Assert.assertEquals("User2", user.getVcharC());
            Assert.assertEquals(1, usersCursor.getCurrentIndex());

            user = iterator.next();
            System.out.println(user.getVcharC());
//            Assert.assertEquals("User3", user.getVcharC());
            Assert.assertEquals(2, usersCursor.getCurrentIndex());

            user = iterator.next();
            System.out.println(user.getVcharC());
//            Assert.assertEquals("User4", user.getVcharC());
            Assert.assertEquals(3, usersCursor.getCurrentIndex());

            user = iterator.next();
            System.out.println(user.getVcharC());
//            Assert.assertEquals("User5", user.getVcharC());
            Assert.assertEquals(4, usersCursor.getCurrentIndex());

            // Check no more elements
            Assert.assertFalse(iterator.hasNext());
            Assert.assertFalse(usersCursor.isOpen());
            Assert.assertTrue(usersCursor.isConsumed());
        } finally {
            sqlSession.close();
        }
    }


   

}
