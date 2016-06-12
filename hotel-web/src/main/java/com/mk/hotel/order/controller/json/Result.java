package com.mk.hotel.order.controller.json;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.xml.CompactWriter;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.StringWriter;
import java.io.Writer;


/**
 * Created by chenqi on 16/6/7.
 */
@XStreamAlias(value="Result")
public class Result {
    @XStreamAlias(value = "Message")
    private String Message;
    @XStreamAlias(value = "Status")
    private String Status;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public static void main(String[] args)
    {
        Result tx = new Result();
        tx.setMessage("1");
        tx.setStatus("2");

        // 对象序列化
        XStream xstream = new XStream(new DomDriver());
        xstream.autodetectAnnotations(true);
        // 不序列化contents属性，但是序列化下面的子对象
        //xstream.addImplicitCollection(Result.class, "contents");
        // 格式化输出
        System.out.println(xstream.toXML(tx));
        // 无格式输出
        Writer writer = new StringWriter();
        xstream.marshal(tx, new CompactWriter(writer));
        String seri = writer.toString();
        System.out.println(seri);
        // 反序列化
        Object object = xstream.fromXML(seri, new Result());
        System.out.println(object);
    }
}
