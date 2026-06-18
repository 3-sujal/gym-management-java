public class PremiumMember extends GymMember {
    private final double premiumCharge = 50000;
    private String personalTrainer;
    private boolean isFullPayment;
    private double paidAmount;
    private double discountAmount;

    public PremiumMember(int id, String name, String location, String phone, String email,
                        String gender, String DOB, String membershipStartDate,
                        String personalTrainer) {
        super(id, name, location, phone, email, gender, DOB, membershipStartDate);
        this.personalTrainer = personalTrainer;
        this.isFullPayment = false;
        this.paidAmount = 0;
        this.discountAmount = 0;
    }

    public double getPremiumCharge() { return premiumCharge; }
    public String getPersonalTrainer() { return personalTrainer; }
    public boolean isFullPayment() { return isFullPayment; }
    public double getPaidAmount() { return paidAmount; }
    public double getDiscountAmount() { return discountAmount; }

    public String payDueAmount(double amount) {
        if (isFullPayment) {
            return "Payment already completed.";
        }
        
        if (amount <= 0) {
            return "Invalid payment amount.";
        }
        
        paidAmount += amount;
        if (paidAmount >= premiumCharge) {
            paidAmount = premiumCharge;
            isFullPayment = true;
            calculateDiscount();
        }
        
        double remaining = premiumCharge - paidAmount;
        return String.format("Payment successful. Remaining amount: %.2f", remaining);
    }

    public String calculateDiscount() {
        if (isFullPayment) {
            discountAmount = premiumCharge * 0.10;
            return String.format("Discount applied: %.2f", discountAmount);
        }
        discountAmount = 0;
        return "No discount available. Complete payment first.";
    }

    public void revertPremiumMember() {
        resetMember();
        personalTrainer = "";
        isFullPayment = false;
        paidAmount = 0;
        discountAmount = 0;
    }

    @Override
    public void markAttendance() {
        if (get_activeStatus()) {
            attendance++;
            loyaltyPoints += 10;
        }
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Personal Trainer: " + personalTrainer);
        System.out.println("Paid Amount: " + paidAmount);
        System.out.println("Full Payment: " + isFullPayment);
        System.out.println("Remaining Amount: " + (premiumCharge - paidAmount));
        if (isFullPayment) {
            System.out.println("Discount Amount: " + discountAmount);
        }
    }
}