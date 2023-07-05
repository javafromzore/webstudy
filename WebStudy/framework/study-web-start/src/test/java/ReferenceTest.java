import org.junit.jupiter.api.Test;

import java.lang.ref.*;
import java.util.LinkedList;
import java.util.List;

public class ReferenceTest {
    @Test
    void strongReferenceTest() {
        String str = new String();
        str = "测试字符串";
    }

    //软引用，GC时不会被回收，但是内存不足时会被回收。因为这个特性，可以被用来做缓存。用的时候使用SoftReference。
    @Test
    void softReferenceTest() {
        //可以被当作缓存（当内存不足时自动回收）
        SoftReference<String> softReference = new SoftReference<>("测试字符串");
        System.out.println("beforeGC:" + softReference.get());
        List<String> list = new LinkedList<>();
        while (true) {
            list.add("集合元素字符串");
        }
    }

    //GC时会被回收，用来防止内存泄露，但是并不好用（因为如果有强引用的话，就失效了）
    @Test
    void weakReferenceTest() {
        WeakReference<String> weakReference = new WeakReference<>(new String("测试字符串"));
        System.out.println("beforeGC:" + weakReference.get());
        System.gc();
        System.out.println("afterGC:" + weakReference.get());
    }

    //虚引用无法被直接使用，只能搭配队列使用。在垃圾回收时不会立马被回收，而是会将其转移到队列中。目的是在垃圾回收前执行某些操作。
    @Test
    void phantomReferenceTest() throws InterruptedException {
        ReferenceQueue<String> queue = new ReferenceQueue<>();
        PhantomReference<String> phantomReference = new PhantomReference<>(new String("测试字符串"), queue);
        System.out.println("beforeGC:" + phantomReference.get());
        System.out.println("beforeGC:" + queue.poll());
        System.gc();
        Thread.sleep(100);
        System.out.println("afterGC:" + phantomReference.get());
        System.out.println("afterGC:" + queue.poll());
    }
}