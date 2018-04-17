package app.urbanist.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String pCloudId;

    @Column
    private String caption;

    @ManyToOne
    @JoinColumn(name = "report_id")
    private Report report;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getpCloudId() {
        return pCloudId;
    }

    public void setpCloudId(String pCloudId) {
        this.pCloudId = pCloudId;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

}
