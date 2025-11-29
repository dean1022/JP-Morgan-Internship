package com.jpmc.midascore;

import com.jpmc.midascore.entity.UserRecord;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
public class TransactionRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "sender_id")
    private UserRecord sender;

    @ManyToOne(optional = false)
    @JoinColumn(name = "recipient_id")
    private UserRecord recipient;

    @Column(nullable = false)
    private float amount;

    @Column(nullable = false)
    private Instant createdAt = Instant.now();

    public TransactionRecord(){}

    public TransactionRecord(UserRecord sender, UserRecord recipient, float amount){
        this.sender = sender;
        this.recipient = recipient;
        this.amount = amount;
        this.createdAt = Instant.now();
    }

    public Long getId(){
        return id;
    }

    public UserRecord getSender(){
        return sender;
    }

    public UserRecord getRecipient(){
        return recipient;
    }

    public void setRecipient(UserRecord recipient){
        this.recipient = recipient;
    }

    public float getAmount(){
        return amount;
    }

    public void setAmount(float amount){
        this.amount = amount;
    }

    public Instant getCreatedAt(){
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt){
        this.createdAt = createdAt;
    }
}
