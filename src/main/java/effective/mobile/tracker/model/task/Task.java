package effective.mobile.tracker.model.task;

import effective.mobile.tracker.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @Enumerated(value = EnumType.STRING)
    private TaskStatus status;

    @Enumerated(value = EnumType.STRING)
    private TaskPriority priority;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
