package chainOfResponsibility;

import java.util.Iterator;
import java.util.Random;

// 使用枚举实现职责链模式

class Enums {

    public static <T extends Enum<T>> T random(Class<T> ec) {
        return random(ec.getEnumConstants());
    }

    public static <T> T random(T[] values) {
        //每次调用new一个新random，标识要不一样，要不然每次运行的结果都一样。
        return values[new Random(System.nanoTime()).nextInt(values.length)];
    }
}

class Mail {
    enum GeneralDelivery {YES, NO1, NO2, NO3, NO4, NO5}

    enum Scannability {UNSCANNABLE, YES1, YES2, YES3, YES4}

    enum Readability {ILLEGIBLE, YES1, YES2, YES3, YES4}

    enum Address {INCORRECT, OK1, OK2, OK3, OK4, OK5, OK6}

    enum ReturnAddress {MISSING, OK1, OK2, OK3, OK4, OK5}

    GeneralDelivery generalDelivery;

    Scannability scannability;

    Readability readability;

    Address address;

    ReturnAddress returnAddress;

    static long counter = 0;

    long id = counter++;

    //重写toString方法
    public String toString() {
        return "Mail " + id;
    }

    //显示详细详细
    public String details() {
        return toString() +
                ", GeneralDelivery: " + generalDelivery +
                ", Scannability: " + scannability +
                ", Readability: " + readability +
                ", Address: " + address +
                ", ReturnAddress: " + returnAddress;

    }

    //创建一个随机的Mail对象，内容也随机
    public static Mail randomMail() {
        Mail mail = new Mail();
        //调用一个Enums中的随机选取工具
        mail.generalDelivery = Enums.random(GeneralDelivery.class);
        mail.scannability = Enums.random(Scannability.class);
        mail.readability = Enums.random(Readability.class);
        mail.address = Enums.random(Address.class);
        mail.returnAddress = Enums.random(ReturnAddress.class);
        return mail;
    }

    /**
     * 随机产生制度能够数量的信
     *
     * @param count 数量
     * @return 以迭代器返回，可以使用for-each遍历
     */
    public static Iterable<Mail> generator(final int count) {
        return new Iterable<Mail>() {
            int n = count;

            @Override
            public Iterator<Mail> iterator() {
                return new Iterator<Mail>() {

                    @Override
                    public boolean hasNext() {
                        return n-- > 0;
                    }

                    @Override
                    public Mail next() {
                        return randomMail();
                    }

                    @Override
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }
}

public class PostOffice {

    /*
     * 定义了四种处理邮件的方式
     * 每一种都会返回对应的值true或false
     * 如果返回false表示该种方式不可行
     */
    enum MailHandler {
        GENERAL_DELIVERY {
            @Override
            boolean handle(Mail m) {
                switch (m.generalDelivery) {
                case YES:
                    System.out.println("Using general delivery for " + m);
                    return true;
                default:
                    break;
                }
                return false;
            }

        },
        MACHINE_SCAN {
            @Override
            boolean handle(Mail m) {
                switch (m.scannability) {
                case UNSCANNABLE:
                    return false;
                default:
                    switch (m.address) {
                    case INCORRECT:
                        return false;
                    default:
                        System.out.println("Delivering " + m + " atuomatically");
                        return true;
                    }
                }
            }

        },
        VISUAL_INSPECTION {
            @Override
            boolean handle(Mail m) {
                switch (m.readability) {
                case ILLEGIBLE:
                    return false;
                default:
                    switch (m.address) {
                    case INCORRECT:
                        return false;
                    default:
                        System.out.println("Delivering " + m + " normally");
                        return true;
                    }
                }
            }

        },
        RETURN_TO_SENOER {
            @Override
            boolean handle(Mail m) {
                switch (m.returnAddress) {
                case MISSING:
                    return false;
                default:
                    System.out.println("Returing " + m + " to sender");
                    return true;
                }
            }

        };

        abstract boolean handle(Mail m);
    }

    /**
     * 按照MailHandler中的几种方法依次处理该邮件，
     * 如果有一种可以处理则返回成功
     * 如果所有的方法都不能处理，判定该邮件为死信
     *
     * @param m 邮件
     */
    static void handle(Mail m) {
        for (MailHandler handler : MailHandler.values()) {
            if (handler.handle(m)) {
                return;
            }
        }
        System.out.println(m + " is a dead letter.");
    }

    public static void main(String[] args) {
        //随机生成10封信
        for (Mail mail : Mail.generator(10)) {
            System.out.println(mail.details());
            handle(mail);
            System.out.println("===============================");
        }
    }
}
