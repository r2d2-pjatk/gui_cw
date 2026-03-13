package zaddod01;

public class Squad {
    
    private int maxCapacity;

    private String squadName;
    private Hero[] heroes;
    private int heroesCount;
    private Leader leader;
    
    public Squad(String squadName, int maxCapacity) {
        this.squadName = squadName;
        this.maxCapacity = maxCapacity;
        this.heroesCount = 0;
        this.heroes = new Hero[maxCapacity];
    }

    public Squad(String squadName) {
        this(squadName, 1);
    }
    
    public void setLeader(Leader leader) {
        this.leader = leader;
    }
    
    public void addHero(Hero hero) throws TooManyHeroesException {
        if (heroesCount >= maxCapacity) {
            throw new TooManyHeroesException("Too many heroes!!!");
        } else {
            heroes[heroesCount++] = hero;
        }

    }

    @Override
    public String toString() {
        String result = squadName + ", leader: " + leader.getName() + ", heroes: ";
        for (int i = 0; i < heroesCount; i++) {
            result += (heroes[i].getName() + (i == heroesCount - 1 ? "" : ", "));
        }
        return result;
    }
}
