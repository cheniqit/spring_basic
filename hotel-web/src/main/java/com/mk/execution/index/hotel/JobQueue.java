package com.mk.execution.index.hotel;

import com.mk.hotel.common.Constant;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import redis.clients.jedis.Jedis;

/**
 * Created by 振涛 on 2016/2/18.
 */
class JobQueue {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(JobQueue.class);


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


    void push(JobQueueMessage msg) {
        Jedis jedis = null;
        try {
            jedis = JedisUtil.get();
            jedis.sadd(Constant.PUSH_INFO_SET, JSONUtil.toJson(msg));

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if ( jedis != null ) {
                jedis.close();
            }
        }



    }

    JobQueueMessage brpop() {
        Jedis jedis = null;
        try {
            jedis = JedisUtil.get();

            String msgJson = jedis.spop(Constant.PUSH_INFO_SET);
            if (StringUtils.isNotBlank(msgJson)){

                JobQueueMessage msg = JSONUtil.fromJson(msgJson, JobQueueMessage.class);

                return msg;
            }else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
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

            jedis = JedisUtil.get();

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
