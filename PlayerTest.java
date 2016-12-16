class PlayerTest {
    static private int failedTests;

    public static void main(String[] args) {
		PlayerTest p = new PlayerTest();
		p.run();
	}

    static public void run() {
		boolean t = true; int count = 1;
		failedTests = 0;

		String want = "RED";

		Player p = new Player(want);
		t &= test(p.getColor().equals(want), count++);
		t &= test(p.isRed(), count++);
		t &= test(p.isBlack() == false, count++);

		want = "BLACK";
		p = new Player(want);
		t &= test(p.getColor().equals(want), count++);
		t &= test(p.isBlack(), count++);
		t &= test(p.isRed() == false, count++);

		System.out.println("Failed tests: " + failedTests);
	}
	    
	static private boolean test(boolean test, int b) {
		if(!test) {
		    failedTests++;
		    System.out.println("Failed test # " + b);
		}
		return test;
    }
}
