/**
 * @program: Courseware-Backend-Java-2022
 * @description: 自定义异常类
 * @author: YxYL
 * @create: 2022-11-22 19:31
 **/

public class MeiQianException extends Exception {

    private Double money;

    public MeiQianException(Double money) {
        super("余额不足，还差：" + money);
        this.money = money;
    }

    public static class AccountAdmin {
        //私房钱
        private Double balance;

        public AccountAdmin(Double balance) {
            this.balance = balance;
        }

        //存钱的方法
        public void deposit(double money) {
            this.balance += money;
        }

        //取钱的方法
        public void withdraw(double money) throws MeiQianException {
            if (balance >= money) {
                balance -= money;
            } else {
                double needMoney = money - balance;
                throw new MeiQianException(needMoney);
            }
        }
    }

    public static void main(String[] args) {
        AccountAdmin accountAdmin = new AccountAdmin(100.0);
        accountAdmin.deposit(200);//存二伯块
        try {
            accountAdmin.withdraw(400);
        } catch (MeiQianException e) {
            throw new RuntimeException(e);
        }
    }
}
