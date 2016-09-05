package com.framework.core.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 序列化就是将一个对象转换为二进制的数据流。这样就可以进行传输，或者保存到文件中。如果一个类的对象要想实现序列化，就必须实现serializable接口。
 * 在此接口中没有任何的方法，此接口只是作为一个标识，表示本类的对象具备了序列化的能力而已。 反序列化:将二进制数据流转换成相应的对象。
 * 如果想要完成对象的序列化，则还要依靠ObjectOutputStream和ObjectInputStream,前者属于序列化操作，而后者属于反序列化操作。
 */
public class SerializationUtil {

    private static final Logger LOG = LoggerFactory.getLogger(SerializationUtil.class);

    /**
     * 序列化
     * 
     * @param object
     * @return byte[]
     * @throws IOException
     */
    public static byte[] serialize(Object object) throws IOException {

        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;

        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (IOException e) {
            LOG.error("error when serialize object. " + e.getMessage(), e);
            throw e;
        } finally {
            baos.close();
            oos.close();
        }
    }

    /**
     * 反序列化
     * 
     * @param byte[]
     * @return Object
     * @throws ClassNotFoundException
     * @throws Exception
     */
    public static Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {

        ByteArrayInputStream bais = null;

        try {
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (IOException e) {
            LOG.error("error when deserialize object. " + e.getMessage(), e);
            throw e;
        } finally {
            bais.close();
        }
    }

}
