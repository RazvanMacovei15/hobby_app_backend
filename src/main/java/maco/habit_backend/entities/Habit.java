package maco.habit_backend.entities;

import jakarta.persistence.*;
import lombok.*;
import maco.habit_backend.enums.Occurrence;
import maco.habit_backend.enums.Type;

import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter

@Entity
@Table(name = "habits")
public class Habit {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "habit_id_seq")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "occurrence")
    private Occurrence occurrence;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private Type type;

    @Column(name = "current_streak")
    private int currentStreak;

    @Column(name = "best_streak")
    private int bestStreak;

    @Column(name = "total_count")
    private int totalCount;

    @Column(name = "completed")
    private LocalDateTime dateCreated;

    @Column(name = "last_updated")
    private  LocalDateTime lastUpdated;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
