package com.chat.chatapp.model;

import com.chat.chatapp.model.Chatter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class blockList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = true)
    private String blockReason;

    @ElementCollection
    private List<Long> blockedChatterId;
    @ElementCollection
    private List<String> permissions;

    @ManyToOne
    @JoinColumn(
            name = "chatter_id",
            referencedColumnName ="id",
            foreignKey =@ForeignKey(name = "fk_blocklist_chatter")
    )
    private Chatter chatter;

    public Long getChatterId(){
        return chatter != null ? chatter.getId() : null;
    }

}
