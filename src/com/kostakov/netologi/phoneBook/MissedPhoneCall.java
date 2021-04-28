package com.kostakov.netologi.phoneBook;

import java.time.*;
import java.util.*;

public class MissedPhoneCall {
    private Map<LocalDateTime, String> missedCall= new TreeMap<>();

    public void addMissedCall (String phoneNumber) {
        missedCall.put(LocalDateTime.now(), phoneNumber);
    }

    public void clearMissedCall() {
        missedCall.clear();
    }

    public boolean isEmpty() {
        return missedCall.isEmpty();
    }

    public Set<Map.Entry<LocalDateTime, String>> entrySet() {
        return missedCall.entrySet();
    }
}