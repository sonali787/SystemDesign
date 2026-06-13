package org.example;

/**
 * Parameter object representing a booking request.
 * Implements the Builder pattern to handle the creation of requests with many fields.
 */
public class BookingRequest {
    private final String movieId;
    private final String seatId;
    private final String paymentMethod;
    private final int loyaltyPoints;
    private final double amount;
    private final String email;
    private final String message;
    private final String userId;

    private BookingRequest(Builder builder) {
        this.movieId = builder.movieId;
        this.seatId = builder.seatId;
        this.paymentMethod = builder.paymentMethod;
        this.loyaltyPoints = builder.loyaltyPoints;
        this.amount = builder.amount;
        this.email = builder.email;
        this.message = builder.message;
        this.userId = builder.userId;
    }

    public String getMovieId() {
        return movieId;
    }

    public String getSeatId() {
        return seatId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public double getAmount() {
        return amount;
    }

    public String getEmail() {
        return email;
    }

    public String getMessage() {
        return message;
    }

    public String getUserId() {
        return userId;
    }

    /**
     * Builder static nested class.
     */
    public static class Builder {
        private String movieId;
        private String seatId;
        private String paymentMethod;
        private int loyaltyPoints;
        private double amount;
        private String email;
        private String message;
        private String userId;

        public Builder setMovieId(String movieId) {
            this.movieId = movieId;
            return this;
        }

        public Builder setSeatId(String seatId) {
            this.seatId = seatId;
            return this;
        }

        public Builder setPaymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }

        public Builder setLoyaltyPoints(int loyaltyPoints) {
            this.loyaltyPoints = loyaltyPoints;
            return this;
        }

        public Builder setAmount(double amount) {
            this.amount = amount;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public BookingRequest build() {
            return new BookingRequest(this);
        }
    }
}
