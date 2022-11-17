package showInterface;

//接口回调的演示
public class ShowCallback {
    public static void main(String[] args) {
        Fight fight = new HeroFight();
        Hero hero1 = new Hero();
        hero1.toFight(fight);
        System.out.println("________第二次受伤害_________");
        hero1.toFight(fight);
/*        Fight heroFight=(Hero hero)->{
            System.out.println("Lambda表达式调用");
        };
        hero1.toFight(heroFight);*/
    }
}

interface Fight {
    void beAttacked(Hero hero);
}

class HeroFight implements Fight {
    @Override
    public void beAttacked(Hero hero) {
        System.out.println("英雄受到了伤害值");
        hero.setHp(hero.getHp() - 50);
        if (hero.getHp() <= 0) {
            System.out.println("该英雄受到过多伤害，已阵亡。");
            hero.die();//回调实体类Hero的方法
        }
    }
}

class Hero {
    private Integer hp = 100;
    private Boolean isDead = false;

    //战斗的方法
    public void toFight(Fight fight) {
        fight.beAttacked(this);
    }

    public void die() {
        this.isDead = true;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

}
