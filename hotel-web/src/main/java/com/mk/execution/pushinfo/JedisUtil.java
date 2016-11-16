//package com.mk.execution.pushinfo;
//
//import com.mk.framework.AppUtils;
//import com.mk.framework.MkJedisConnectionFactory;
//import org.slf4j.Logger;
//import redis.clients.jedis.Jedis;
//
///**
// * Created by 振涛 on 2016/2/18.
// */
//class JedisUtil {
//
//    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(JedisUtil.class);
//
//    private static class LazyInit {
//        private static MkJedisConnectionFactory factory;
//
//        static {
//            LOGGER.info("init jedis starting");
//
//            factory = AppUtils.getBean(MkJedisConnectionFactory.class);
//
//            LOGGER.info("init jedis finished");
//        }
//    }
//
//    private JedisUtil() {}
//
//    static Jedis get() {
//        return LazyInit.factory.getJedis();
//    }
//
//    static void close(Jedis jedis) {
//        if ( jedis != null ) {
//            jedis.close();
//            jedis = null;
//        }
//    }
//
//}
