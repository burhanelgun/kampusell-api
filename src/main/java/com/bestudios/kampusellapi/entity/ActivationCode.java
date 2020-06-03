package com.bestudios.kampusellapi.entity;

import javax.persistence.*;

@Entity
@Table(name = "activation_codes")
public class ActivationCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "activation_code", nullable = true, length = 255)
    private String activationCode;

    public ActivationCode() {
        int activationCodeSize = 6;
        String AlphaNumericString = "0123456789"
                + "abcdefghijklmnopqrstuvxyz";


        StringBuilder sb = new StringBuilder(activationCodeSize);

        for (int i = 0; i < activationCodeSize; i++) {

            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        activationCode = sb.toString();
    }

    public ActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }
}
