public class RegularMember extends GymMember {
    private final int attendanceLimit = 30;
    private boolean isEligibleForUpgrade;
    private String removalReason;
    private String referralSource;
    private String plan;
    private double price;

    public RegularMember(int id, String name, String location, String phone, String email,
                        String gender, String DOB, String membershipStartDate,
                        String referralSource,String plan, double price) {
        super(id, name, location, phone, email, gender, DOB, membershipStartDate);
        this.isEligibleForUpgrade = false;
        this.removalReason = "";
        this.referralSource = referralSource;
        this.plan = "basic";
        this.price = 6500.0;
    }

    public int getAttendanceLimit() { return attendanceLimit; }
    public boolean isEligibleForUpgrade() { return isEligibleForUpgrade; }
    public String getRemovalReason() { return removalReason; }
    public String getReferralSource() { return referralSource; }
    public String getPlan() { return plan; }
    public double getPrice() { return price; }

    @Override
    public void markAttendance() {
        if (get_activeStatus()) {
            attendance++;
            loyaltyPoints += 5;
            if (attendance >= attendanceLimit) {
                isEligibleForUpgrade = true;
            }
        }
    }

    public double getPlanPrice(String plan) {
        switch (plan.toLowerCase()) {
            case "basic": return 6500;
            case "standard": return 12500;
            case "deluxe": return 18500;
            default: return -1;
        }
    }
    
    public String upgradePlan(String newPlan) {
        if (!isEligibleForUpgrade) {
            return "Not eligible for upgrade. Attendance limit not reached.";
        }
        if (plan.equalsIgnoreCase(newPlan)) {
            return "Already subscribed to " + newPlan + " plan.";
        }
        
        double newPrice = getPlanPrice(newPlan);
        if (newPrice == -1) {
            return "Invalid plan selected.";
        }
        
        plan = newPlan;
        price = newPrice;
        isEligibleForUpgrade = false;
        return "Plan upgraded to " + newPlan + " successfully.";
    }

    public void revertRegularMember(String removalReason) {
        resetMember();
        this.isEligibleForUpgrade = false;
        this.plan = "basic";
        this.price = 6500;
        this.removalReason = removalReason;
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Plan: " + plan);
        System.out.println("Price: " + price);
        if (!removalReason.isEmpty()) {
            System.out.println("Removal Reason: " + removalReason);
        }
    }
}