package com.mk.execution.pushinfo;

import com.dianping.cat.Cat;
import com.mk.framework.redis.MkJedisConnectionFactory;
import com.mk.framework.spring.AppUtils;
import com.mk.hotel.common.Constant;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

/**
 * Created by 振涛 on 2016/2/18.
 */
class JobQueue {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(JobQueue.class);

    private MkJedisConnectionFactory jedisConnectionFactory = (MkJedisConnectionFactory) AppUtils.getBean(MkJedisConnectionFactory.class);

    private static class LazyInit {
        private static JobQueue jobQueue;

        static {
            jobQueue = new JobQueue();
        }
    }

    private JobQueue() {

    }

    static JobQueue getInstance() {
        return LazyInit.jobQueue;
    }


    Long getLeftInfo () {
        Jedis jedis = null;
        try {
            jedis = jedisConnectionFactory.getJedis();
            return jedis.scard(Constant.PUSH_INFO_SET);
        }catch (Exception e){
            e.printStackTrace();
            Cat.logError(e);
        }finally {
            if ( jedis != null ) {
                jedis.close();
            }
        }
        return 0l;
    }
    void push(JobQueueMessage msg) {
        Jedis jedis = null;
        try {
            jedis = jedisConnectionFactory.getJedis();
            jedis.sadd(Constant.PUSH_INFO_SET, JSONUtil.toJson(msg));

        }catch (Exception e){
            e.printStackTrace();
            Cat.logError(e);
        }finally {
            if ( jedis != null ) {
                jedis.close();
            }
        }
    }

    JobQueueMessage brpop() {
        Jedis jedis = null;
        try {
            jedis = jedisConnectionFactory.getJedis();

            String msgJson = jedis.spop(Constant.PUSH_INFO_SET);
            if (StringUtils.isNotBlank(msgJson)){

                JobQueueMessage msg = JSONUtil.fromJson(msgJson, JobQueueMessage.class);

                return msg;
            }else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Cat.logError(e);
        } finally {
            if ( jedis != null ) {
                jedis.close();
            }
        }
        return null;
    }

    void rem(JobQueueMessage msg) {
        Jedis jedis = null;
        try {

            jedis = jedisConnectionFactory.getJedis();

            if (msg != null) {
                jedis.srem(Constant.PUSH_INFO_SET,JSONUtil.toJson(msg));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ( jedis != null ) {
                jedis.close();
            }
        }
    }

}
