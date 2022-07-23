package microservice.lesson1.entity;

import javax.persistence.*;

@Entity
@Table(name = "greeting")
public class Greeting {
    @Id
    @Column(name = "greeting_id")
    @GeneratedValue(strategy=GenerationType.AUTO, generator="GREETING_SEQUENCE")
    @SequenceGenerator(name="GREETING_SEQUENCE", sequenceName="GREETING_SEQUENCE", allocationSize=1)
    private Long id;

    @Column(name = "content")
    private String content;

    public Greeting(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    public Greeting(String content) {
        this.content=content;
    }

    public Greeting() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Greeting{ID=" + id + ", content='" + content + "'}";
    }
}
