package org.example.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Receipt {
    private UUID id;
    private LocalDateTime exitTime;
    private UUID ticketId;
    private double totalFee;
    private Payment.PaymentStatus paymentStatus;

    public Receipt(UUID ticketId, double totalFee) {
        this.id = UUID.randomUUID();
        this.ticketId = ticketId;
        this.totalFee = totalFee;
        this.exitTime = LocalDateTime.now();
        this.paymentStatus = Payment.PaymentStatus.PENDING;
    }

    public void markAsPaid() {
        this.paymentStatus = Payment.PaymentStatus.SUCCESS;
    }

    public void markAsFailed() {
        this.paymentStatus = Payment.PaymentStatus.FAILED;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public UUID getTicketId() {
        return ticketId;
    }

    public void setTicketId(UUID ticketId) {
        this.ticketId = ticketId;
    }

    public double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(double totalFee) {
        this.totalFee = totalFee;
    }

    public Payment.PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Payment.PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
