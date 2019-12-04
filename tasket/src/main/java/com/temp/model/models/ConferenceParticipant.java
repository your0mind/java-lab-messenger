package com.temp.model.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "conference_participants")
public class ConferenceParticipant implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "conference_id")
    private Conference conference;

    @Column(name = "conference_id")
    private int conferenceId;

    @Column(name = "user_id")
    private int participantId;

    public Conference getConference() {
        return conference;
    }

    public ConferenceParticipant(int conferenceId, int participantId) {
        this.conferenceId = conferenceId;
        this.participantId = participantId;
    }
}
