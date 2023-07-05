import org.junit.jupiter.api.Test;

public class AsynchronizationTest {
    @Test
    void asynchronize() throws InterruptedException {
        System.out.println("第一件事");
        System.out.println("第二件事");
        {
            //异步不一定要顺序执行，不一定要等前面的逻辑执行完了后面的逻辑才能执行
            Thread.sleep(1000);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("第三件事");
                }
            }).start();
        }
        System.out.println("第四件事");
    }

    @Test
    void synchronize() throws InterruptedException {
        System.out.println("第一件事");
        System.out.println("第二件事");
        {
            //同步必须顺序执行，后面的逻辑必须等前面的逻辑执行完才能执行
            Thread.sleep(1000);
            System.out.println("第三件事");
        }
        System.out.println("第四件事");
    }
}